package com.kh.test.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MemberDAO {

public Connection getConnection() throws Exception{

	Class.forName("oracle.jdbc.driver.OracleDriver");
	
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	
	String id = "kh";
	
	String pw = "kh";
	
	return DriverManager.getConnection(url,id,pw);

}

	public boolean isEmailExist(String email) throws Exception{

		String sql = "select email from members where email=?";
		
		try(Connection con = this.getConnection();
		
				PreparedStatement pstat = con.prepareStatement(sql);
		
		){
		
			pstat.setString(1, email);
			
			try(ResultSet rs = pstat.executeQuery();){
			
				//6. isEmailExist 함수는 전달받은 email이 member 테이블 내에 존재하는지 검사하고 그 결과를 boolean 형으로 반환한다.
				
				return rs.next();
			
			}
		
		}

	}

}
