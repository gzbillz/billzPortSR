package com.billzsoft.dbHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @description 数据库连接，操作
 * @class DbConnection
 * @author YHZ
 * @date 2013-6-24
 */
public class DbConnection {

	protected Connection conn = null;
	protected PreparedStatement pst = null;
	protected Statement st = null;
	protected ResultSet rs = null;
	private String username = "root";
	private String pwd = "yhz";
	private String jdbcDriver = "com.mysql.jdbc.Driver";
	private String dbUrl = "jdbc:mysql://localhost:3306/jloa7?useUnicode=true&amp;characterEncoding=utf-8";

	/**
	 * 创建Connection
	 * 
	 * @return Connection
	 */
	public Connection getConnection() {
		try {
			Class.forName(jdbcDriver);
			// myDB为数据库名
			conn = DriverManager.getConnection(dbUrl, username, pwd);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * 关闭所有连接
	 * 
	 * @param rs
	 *            ResultSet
	 * @param pst
	 *            PreparedStatement
	 * @param conn
	 *            Connection
	 */
	public void closeAll(ResultSet rs, PreparedStatement pst, Connection conn) {
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (pst != null) {
				pst.close();
				pst = null;
			}
			if (conn != null && !conn.isClosed()) {
				conn.close();
				conn = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 关闭所有连接
	 * 
	 * @param rs
	 *            ResultSet
	 * @param st
	 *            Statement
	 * @param conn
	 *            Connection
	 */
	public void closeAll(ResultSet rs, Statement st, Connection conn) {
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (st != null) {
				st.close();
				st = null;
			}
			if (conn != null && !conn.isClosed()) {
				conn.close();
				conn = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
