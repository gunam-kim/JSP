package com.kh.burgerking.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/order")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public OrderServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
			데이터를 받아 가공해 Service로 넘겨주는 역할
			VIEW에서 GET방식으로 요청 시 doGet()가 호출됨
			첫번째 매개변수 HttpServletRequest
				: 요청 시 전달된 내용들이 담김
			두번째 매개변수 HttpServletResponse
				: 요청 처리 후 응답할 때 사용
		*/
		int num = Integer.parseInt(request.getParameter("num"));
		System.out.println(num);
		String productName = request.getParameter("product-name");
		System.out.println(productName);
		/*
			자주 보는 문제상황
			404
				: 파일이나 요청을 받아주는 서블릿을 찾지 못했을 때 발생
				: 오타일 확률이 높음
			500
				: Java 소스 코드상의 오류 (예외 발생)
		*/
		
		/*
			요청 처리 (Service의 메서드를 호출해서 DB와의 상호작용까지 끝난 상태)
		*/
		int totalPrice = num * 500;
		
		/*
			xxx(사용자가 입력한 제품명)의 총 가격은 xxx(총 결제해야하는 금액)원입니다
		*/
		// 1단계) 응답데이터 형식 지정 -> 문서형태의 HTML / 인코딩 방식 UTF-8
		response.setContentType("text/html; charset=UTF-8");
		// 2단계) 출력 스트림 생성
		PrintWriter writer = response.getWriter();
		// 3단계) 스트림을 통해 HTML 출력
		writer.println("<html>");
		writer.println("<head>");
		writer.println("<title>GET 방식 응답</title>");
		writer.println("</head>");
		writer.println("<script>");
		writer.println("alert('성공')");
		writer.println("</script>");
		writer.printf("%s의 총 가격은 %d원 입니다", productName, totalPrice);
		writer.println("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("/bk/order doPost 메소드 호출됨");
	}

}
