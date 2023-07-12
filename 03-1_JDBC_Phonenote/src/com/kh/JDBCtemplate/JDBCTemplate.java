package com.kh.JDBCtemplate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTemplate {
	
	/**
	 * 1.Connection 객체 생성(DB와 접속) 한 후 해당 Connection 객체를 반환해주는 메서드
	 * @return
	 */
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","ABC","ABC");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}return conn;
	}
	
	/**
	 * 2.Commit 처리해주는 메서드 (Connection 전달받아서)
	 * @param conn
	 */
	public static void commit(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
			conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	} 
	
	/**
	 * 3. rollback 처리해주는 메서드 (Connection 전달받아서)
	 * @param conn
	 */
	public static void rollback(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
			conn.rollback();}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//오버로딩
	//JDBC용 객체들 전달받아서 반납처리해주는 메서드
	/**
	 * 4.Statement 관련 객체 전달 받아서 반납시켜주는 메서드
	 * @param stmt
	 */
	public static void close(Statement stmt) {// 얘가 부모라서 PreparedStatement 받을 수 있음 !! 다형성 적용
		try {
			if(stmt != null && !stmt.isClosed()) {
			stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//오버로딩
	/**
	 *Connection 객체 전달 받아서 반납시켜주는 메서드
	 * @param conn
	 */
	public static void close(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//오버로딩
	/**
	 * ResultSet 객체 전달 받아서 반납시켜주는 메서드
	 * @param rset
	 */
	public static void close(ResultSet rset) {
		try {
			if(rset != null && !rset.isClosed()) {
			rset.close();}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
