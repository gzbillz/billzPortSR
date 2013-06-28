package com.billzsoft.send.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @description 待发短信
 * @class SmsserverOut
 * @author YHZ
 * 
 */
@SuppressWarnings("serial")
public class SmsOutBox implements Serializable {

	protected String siSmsId; // SISMSID[UUID/GUID( 由应用侧产生，用来唯一标记短信]
	protected String extCode; // EXTCODE[扩展号码]
	protected String destAddr; // DESTADDR[接受手机MSISDN,多个用“;”分割,最大不超过50 个]
	protected String messageContent; // MESSAGECONTENT[短信内容当MSGFMT 为4
										// 时,消息内容为十六进制字符串]
	protected Integer reqDeliveryReport; // REQDELIVERYREPORT[是否需要状态报告0:不需要,1:需要]
	protected Integer msgFmt; // MSGFMT[消息类型,0- ASCII,4- Binary,8- usc2,15-
								// gb2312,16-gb18030]
	protected Integer sendMethod; // SENDMETHOD[0-普通短信,1-普通短信立即显示,2-长短信组包,3-带结构短信]
	protected java.util.Date requestTime; // REQUESTTIME[入库时间( 短信发送请求时间)]
	protected String applicationId; // APPLICATIONID[EC/SI
									// 应用的ID（接口管理中增加的接入信息中应用插件编号）]

	public SmsOutBox() {
		super();
	}

	public String getSiSmsId() {
		return siSmsId;
	}

	public void setSiSmsId(String siSmsId) {
		this.siSmsId = siSmsId;
	}

	public String getExtCode() {
		return extCode;
	}

	public void setExtCode(String extCode) {
		this.extCode = extCode;
	}

	public String getDestAddr() {
		return destAddr;
	}

	public void setDestAddr(String destAddr) {
		this.destAddr = destAddr;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public Integer getReqDeliveryReport() {
		return reqDeliveryReport;
	}

	public void setReqDeliveryReport(Integer reqDeliveryReport) {
		this.reqDeliveryReport = reqDeliveryReport;
	}

	public Integer getMsgFmt() {
		return msgFmt;
	}

	public void setMsgFmt(Integer msgFmt) {
		this.msgFmt = msgFmt;
	}

	public Integer getSendMethod() {
		return sendMethod;
	}

	public void setSendMethod(Integer sendMethod) {
		this.sendMethod = sendMethod;
	}

	public java.util.Date getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(java.util.Date requestTime) {
		this.requestTime = requestTime;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String toString() {
		return new ToStringBuilder(this).append("siSmsId", this.siSmsId)
				.append("extCode", this.extCode)
				.append("destAddr", this.destAddr)
				.append("messageContent", this.messageContent)
				.append("reqDeliveryReport", this.reqDeliveryReport)
				.append("msgFmt", this.msgFmt)
				.append("sendMethod", this.sendMethod)
				.append("requestTime", this.requestTime)
				.append("applicationId", this.applicationId).toString();
	}

}
