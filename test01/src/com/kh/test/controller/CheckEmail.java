package com.kh.test.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.test.dao.MemberDAO;


@WebServlet("/CheckEmail")
public class CheckEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDAO dao = new MemberDAO();

		response.setContentType("text/html;charset=utf-8");

		PrintWriter resp = response.getWriter();

		// 5. 클라이언트로 부터 넘어오는 email 값을 받고 MemberDAO 클래스의 isEmailExist 함수에 인자값으로 전송받은 email 을 전달한다.

		String email = request.getParameter("email");

		try {

			boolean result = dao.isEmailExist(email);
	
			if(result) {
	
				// 7. DAO 로 부터 반환받은 값이 true 라면 클라이언트에게 "이미 사용중인 email 입니다." 라고 클라이언트에게 반환한다.
		
				resp.println("이미 사용중인 email 입니다.");
	
			}else {
	
				// 8. DAO 로 부터 반환받은 값이 false 라면 클라이언트에게 "사용 가능한 email 입니다." 라고 클라이언트에게 반환한다.
		
				resp.println("사용 가능한 email 입니다.");
	
			}

		}catch(Exception e) {

			e.printStackTrace();
	
			// 9. 만약 true 도 false 도 아닌 예외가 발생했다면, "조회하는 도중 오류가 발생했습니다." 라고 클라이언트에게 반환한다.
	
			resp.println("조회하는 도중 오류가 발생했습니다.");

		}

	}

	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
