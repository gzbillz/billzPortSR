package com.billzsoft.send.dao;

import java.sql.SQLException;
import java.util.List;

import com.billzsoft.dbHelper.DbConnection;

/**
 * @description 从串口获取数据，然后提交到外网mas数据库服务器
 * @class WDbHelper
 * @author YHZ
 * @date 2013-6-24
 */
public class WDbHelper extends DbConnection{
	
	/**
	 * 发送短信到外网
	 */
	public void insertSmsServerOutStatus(List<Long> ids, String status){
		try {
			String sql = "insert into SMS_OUTBOX(SISMSID,DESTADDR,MESSAGECONTENT,REQDELIVERYREPORT,MSGFMT,SENDMETHOD,REQUESTTIME,APPLICATIONID) values() ";
			conn = getConnection();
			conn.setAutoCommit(false); 
			st = conn.createStatement();
			// 批量修改
			for(Long id : ids){
				st.addBatch(sql + id);
			}
			st.executeBatch();
			conn.commit(); 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(null, st, conn);
		}
	}
}
