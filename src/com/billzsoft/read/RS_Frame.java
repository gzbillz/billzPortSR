package com.billzsoft.read;

/******************************************
 *　程序文件名称：读取内网信息，发送到串口
 *  功能：从串行口COM1中发送数据
 ******************************************/
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.comm.CommPortIdentifier;
import javax.comm.PortInUseException;
import javax.comm.SerialPort;
import javax.comm.UnsupportedCommOperationException;

import org.apache.commons.lang3.StringUtils;

import com.billzsoft.read.dao.RDbHelper;
import com.billzsoft.read.model.SmsServerOut;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * @description 读取内网信息，发送到串口
 * @class RS_Frame
 * @author YHZ
 * @date 2013-6-24
 */
public class RS_Frame implements Runnable {
	
	/* 检测系统中可用的通讯端口类 */
	static CommPortIdentifier portId;
	/* Enumeration 为枚举型类,在util中 */
	@SuppressWarnings("rawtypes")
	static Enumeration portList;
	OutputStream outputStream;
	/* RS-232的串行口 */
	SerialPort serialPort;
	Thread readThread;
	/* 设置判断要是否关闭串口的标志 */
	boolean mark;

	/**
	 * 打开串口,并调用线程发送数据
	 */
	public void start() {
		/* 获取系统中所有的通讯端口 */
		portList = CommPortIdentifier.getPortIdentifiers();
		/* 用循环结构找出串口 */
		while (portList.hasMoreElements()) {
			/* 强制转换为通讯端口类型 */
			portId = (CommPortIdentifier) portList.nextElement();
			if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
				if (portId.getName().equals("COM1")) {
					/* 打开串口 */
					try {
						serialPort = serialPort != null ? serialPort : (SerialPort) portId.open("ReadComm", 2000);
					} catch (PortInUseException e) {
						e.printStackTrace();
					}
					/* 设置串口输出流 */
					try {
						outputStream = serialPort.getOutputStream();
					} catch (IOException e) {
						e.printStackTrace();
					}
				} // if end
			} // if end
		} // while end
		/* 调用线程发送数据 */
		try {
			readThread = new Thread(this);
			// 线程负责每发送一次数据，休眠2秒钟
			readThread.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // start() end

	/**
	 * 发送数据,休眠2秒钟后重发
	 */
	public void run() {
		/* 设置串口通讯参数 */
		try{
			serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
		}catch(UnsupportedCommOperationException e) {
			e.printStackTrace();
		}
		/* 发送数据流(将数组data[]中的数据发送出去) */
		try {
			String tmp = getReadMsg();
			if(StringUtils.isNotEmpty(tmp)){
				outputStream.write(tmp.getBytes("gbk"));
				System.out.println("发送数据:" + tmp);				
			} else {
				System.out.println("没有要发送的数据!");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		/* 发送数据后休眠2秒钟,然后再重发 */
		try {
			Thread.sleep(10000); // 1000 * 1
			if (mark) {
				return; // 结束run方法,导致线程死亡
			}
			start();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 读取代发短信
	 * @return
	 */
	private String getReadMsg(){
		String msg = "";
		List<Long> ids = new ArrayList<Long>();
		RDbHelper rdb = new RDbHelper();
		List<SmsServerOut> list = rdb.findSmsServerOutList(ids);
		if(!list.isEmpty()){
			// 修改短信的状态
			rdb.updateSmsServerOutStatus(ids, "S");
			// end 
			Gson gson = new Gson();
			Type type = new TypeToken<List<SmsServerOut>>() {
			}.getType();
			msg = gson.toJson(list, type);			
		} else {
			msg = null;
		}
		return msg;
	}
	
	/**
	 * 测试
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("串口数据传输，获取内网数据，进程启动...");
		RS_Frame sf = new RS_Frame();
		sf.start();
	}
}
