package kr.or.ddit.board.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JDBC 드라이버를 로딩하고 Connection 객체를 생성하는 기능 제공
 */

public class JDBCUtil {
	
	static {
		
		try {
			// Annotation T01 참조
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패 !!!");
			e.printStackTrace();
		} 
		
	}
	
	public static Connection getConnection() {
		
		try {
			return DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", 
					"WDH94", 
					"java");
		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;
		}

	}
	
	public static void close(Connection conn, Statement stmt, PreparedStatement pstmt, ResultSet rs) {
		
		if(rs != null) try {rs.close();} catch(SQLException ex) {}
		if(stmt != null) try {stmt.close();} catch(SQLException ex) {}
		if(pstmt != null) try {pstmt.close();} catch(SQLException ex) {}
		if(conn != null) try {conn.close();} catch(SQLException ex) {}
		
	}
}
