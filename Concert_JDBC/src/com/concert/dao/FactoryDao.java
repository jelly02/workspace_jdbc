package com.concert.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 모든 DAO class에서 사용하기 위한 Connection 반환, 자원해제 close() 하는 기능으로만 분리 설계
 * Singleton Pattern
 * @author Administrator
 *
 */
public class FactoryDao {
	
	//JDBC resource property
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private String user = "hr";
	private String password = "tiger";

	//singleton Pattern 적용
	//(1) private static CLASS instance 
	private static FactoryDao instance = new FactoryDao();
	
	//(2) private 생성자
	private FactoryDao() {
		
		try {
			Class.forName(driver);
			System.out.println("[성공] 드라이버 로딩 성공");
		} catch (ClassNotFoundException e) {
			System.out.println("[오류] 드라이버 로딩 오류");
			e.printStackTrace();
		}
		
	}

	/**
	 * instance 반환 메서드 
	 * @return instance
	 */
	public static FactoryDao getInstance() {
			return instance;
	}
	
	
	/**
	 * 
	 * DB 서버연결 
	 * 
	 * @return Connection
	 */
	public Connection getConnection() {
		
		Connection conn = null; 
		
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}
	
	/**
	 *  Select  자원 해제 메서드
	 * @param conn
	 * @param stmt
	 *  @param rs
	 */
	public void close(Connection conn, Statement stmt, ResultSet rs ) {
		try {
			if(rs != null) {
			rs.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt.close();
			
			if(stmt != null) {
				stmt.close();
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if(conn != null) {
				conn.close();
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 자원 해제 : CUD 작업
	 * @param conn
	 * @param stmt
	 */
	public void close(Connection conn, Statement stmt) {

		//(1) 
		close(conn, stmt, null);
		
		//(2)
//		try {
//			stmt.close();
//			
//			if(stmt != null) {
//				stmt.close();
//				}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//			if(conn != null) {
//				conn.close();
//				}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
		
}
