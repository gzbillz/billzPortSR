package com.billzsoft.read.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.billzsoft.dbHelper.DbConnection;
import com.billzsoft.read.model.SmsServerOut;

/**
 * @description 从内网服务器上获取数据操作
 * @class DbHelper
 * @author YHZ
 * @date 2013-6-24
 */
public class RDbHelper extends DbConnection{

	/**
	 * 查询，待发的短信信息
	 */
	public List<SmsServerOut> findSmsServerOutList(){
		List<SmsServerOut> list = new ArrayList<SmsServerOut>();
		try {
			// U.未发送,Q.排队中,S.已发送,F.失败
			String sql = "select id,recipient,text from smsserver_out where status = 'U' ";
			conn = getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			SmsServerOut out = null;
			while(rs.next()){
				out = new SmsServerOut();
				out.setId(rs.getLong("id"));
				out.setRecipient(rs.getString("recipient"));
				out.setText(rs.getString("text"));
				list.add(out);
				out = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, st, conn);
		}
		return list;
	}
	
	/**
	 * 查询，待发的短信信息
	 * @param 获取待发短信编号
	 */
	public List<SmsServerOut> findSmsServerOutList(List<Long> ids){
		List<SmsServerOut> list = new ArrayList<SmsServerOut>();
		try {
			// U.未发送,Q.排队中,S.已发送,F.失败
			String sql = "select id,recipient,text from smsserver_out where status = 'U' ";
			conn = getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			SmsServerOut out = null;
			while(rs.next()){
				out = new SmsServerOut();
				out.setId(rs.getLong("id"));
				ids.add(out.getId()); // 待发短信编号
				out.setRecipient(rs.getString("recipient"));
				out.setText(rs.getString("text"));
				list.add(out);
				out = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, st, conn);
		}
		return list;
	}
	
	/**
	 * 修改，待发短信状态
	 * @param ids
	 * @param status 状态："U" :未发送, "Q" : 排队中, "S" : 已发送, "F" : 失败
	 */
	public void updateSmsServerOutStatus(List<Long> ids, String status){
		try {
			String sql = "update smsserver_out set status='" +status + "' where id = ";
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
	
	/**
	 * 删除，删除成功发送的短信信息
	 * @param ids
	 */
	public void deleteSmsServerOut(List<Long> ids){
		try {
			String sql = "delete from smsserver_out where id = ";
			conn = getConnection();
			conn.setAutoCommit(false); 
			st = conn.createStatement();
			// 批量删除
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
