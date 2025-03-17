package com.kh.mfw.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.mfw.board.model.dto.BoardDTO;
import com.kh.mfw.board.model.service.BoardService;
import com.kh.mfw.member.model.dto.MemberDTO;

@WebServlet("/insert.board")
public class BoardInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BoardInsertController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// 게시글번호/게시글카테고리/작성자/제목/작성일/조회수
		// 시퀀스		앞단		  ?	  앞단  DEFAULT
		BoardDTO board = new BoardDTO();
		board.setBoardCategory(request.getParameter("category"));
		board.setBoardTitle(request.getParameter("title"));
		board.setBoardContent(request.getParameter("content"));
		// 작성자에 대한 정보도 담아서 보내야하는데
		// 로그인하지 않은 사용자는 게시글 작성요청을 보낼 수 없게끔 처리
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) {
			session.setAttribute("message", "로그인해주세요!");
			response.sendRedirect(request.getContextPath());
		}
		String memberId = ((MemberDTO)session.getAttribute("loginMember")).getMemberId();
		board.setBoardWriter(memberId);
		// 요청처리
		new BoardService().insertBoard(board);
		// sendRedirect
		session.setAttribute("message", "게시글 작성에 성공했습니다!");
		response.sendRedirect(request.getContextPath() + "/boards?page=1");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
