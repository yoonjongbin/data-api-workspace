package com.kh.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MemberDAO {
	
	public Connection getConnection() throws Exception {

		Class.forName("oracle.jdbc.driver.OracleDriver");
			
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "kh";
		String pw = "kh";
			
		return DriverManager.getConnection(url, id, pw);
		
	}
	
	public boolean login(String id, String pwd) throws Exception{
		String sql = "select * from members where id = ? and pwd = ?";
		
		Connection conn = this.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, id);
		pstmt.setString(2, pwd);
		
		ResultSet rs = pstmt.executeQuery();
		
		return rs.next();
	}
	
}
