package com.kh.mcdonald.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.mcdonald.model.dto.Hamburger;

@WebServlet("/sc")
public class SettingController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SettingController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("뚜겟");
		/*
			Servlet에서 응답 데이터가 있는데?
			JSP에게 request에 담아서 보내줘야 한다
			
			Application / Session / Request / Page
			: 저장소 역할
			
			1) ServletContext (Application Scope)
				: 하나의 애플리케이션 당 딱 한개 존재하는 객체
				: 이 영역에 데이터를 담으면 애플리케이션 전역에서 사용 가능
			2) HttpSession (Session Scope)
				: 하나의 브라우저 당 한개 존재하는 객체
				: 이 영역에 데이터를 담으면 JSP/Servlet단에서 사용 가능
				: 값이 한번 담기면, 세션을 비우거나(로그아웃) 브라우저가 닫히거나 서버를 중지하기 전까지는
				  계속해서 사용 가능
			3) HttpServletRequest (Request Scope)
				: 요청 시 매번 생성되는 객체
				: 이 영역에 데이터를 담으면 해당 request 객체를 포워딩받는 응답 JSP에서만 사용 가능 (1회용)
			4) PageContext (Page Scope)
				: JSP페이지 내에서만 사용 가능
				
			=> 위 객체들에
			   값을 담을 때는 .setAttribute("키", "밸류")
			   값을 담을 때는 .getAttribute("키")
			   값을 담을 때는 .removeAttribute("키")
		*/
		
		// requestScope
		request.setAttribute("brand", "KFC");
		request.setAttribute("bestSeller", new Hamburger("징거버거", 6200, "KFC"));
		
		// sessionScope
		HttpSession session = request.getSession();
		session.setAttribute("brand", "McDonald");
		session.setAttribute("bestSeller", new Hamburger("빅맥", 6500, "McDonald"));
		
		// 숫자
		request.setAttribute("big", 10);
		request.setAttribute("small", 3);
		// 문자
		request.setAttribute("str", "carpe diem");
		request.setAttribute("bool", true);
		// 리스트
		request.setAttribute("list", new ArrayList());
		
		// 응답 뷰 위임
		request.getRequestDispatcher("/WEB-INF/views/print.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
