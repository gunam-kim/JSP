package com.kh.mfw.board.model.service;

import static com.kh.mfw.common.Template.getSqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.kh.mfw.board.model.dao.BoardDAO;
import com.kh.mfw.board.model.dto.BoardDTO;

public class BoardService {
	private BoardDAO boardDAO = new BoardDAO();
	public void insertBoard(BoardDTO board) {
		SqlSession sqlSession = getSqlSession();
		
		boardDAO.insertBoard(sqlSession, board);
		
		sqlSession.commit();
		sqlSession.close();
	}
	
	public Map<String, Object> selectBoards(int page){
		SqlSession sqlSession = getSqlSession();
		// Table에서 조회해온 게시글 총 개수
		int boardCount = boardDAO.selectBoardCount(sqlSession);
			// page = 앞단에서 넘어온 요청 페이지
		/*
			마지막 페이지를 구해야한다 (한 페이지에 보여줄 게시글 개수 : 10개)
			
			boardCount	한 페이지 개수	마지막 페이지
				100			10			10
				103			10			11
				112			10			12
			
			=> '총 게시글 개수' / '한 페이지 개수'를 올림처리
		*/
		int boardLimit = 10;	// 한 페이지에 보여줄 게시글 개수
		int maxPage = (int)Math.ceil(boardCount / (double)boardLimit);
		/*
			startBtn : 페이지 하단에 보여질 버튼 중 시작 값
			
			한 페이지에 보여질 개수 : 5개
			start : 1, 6, 11, 16, ...
			한 페이지에 보여질 개수 : 3개
			start : 1, 4, 7, 10, ...
			
			startBtn = n * 한 페이지 개수 + 1
			
			(page - 1) / 5 == n
			
			startBtn = (page - 1) / 5 * 5 + 1
		*/
		
		int btnLimit = 5;	// 한 페이지에 보여줄 버튼 개수
		int startBtn = (page - 1) / btnLimit * btnLimit + 1;
		// endBtn = startBtn + btnLimit - 1
		int endBtn = startBtn + btnLimit - 1;
		if(endBtn > maxPage) endBtn = maxPage;
		/*
			# MyBatis에서 제공하는 RowBounds
			
			: offset과 limit을 생성자 매개변수로 전달해주어야 한다
		*/
		RowBounds rowBounds = new RowBounds((page - 1) * boardLimit, boardLimit);
		
		List<BoardDTO> boards = new BoardDAO().selectBoards(sqlSession, rowBounds);
		System.out.println(boards);
		sqlSession.close();
		
		Map<String, Object> map = new HashMap();
		map.put("boards", boards);
		map.put("boardCount", boardCount);
		map.put("page", page);
		map.put("boardLimit", boardLimit);
		map.put("btnLimit", btnLimit);
		map.put("maxPage", maxPage);
		map.put("startBtn", startBtn);
		map.put("endBtn", endBtn);
		
		return map;
	}
	
	public BoardDTO findByBoardNo(int boardNo) {
		SqlSession sqlSession = getSqlSession();
		/*
			최대 2번 가야함
			조회수 증가 로직 1번		-> UPDATE + COMMIT
			게시글 정보조회 로직 1번	-> SELECT
		*/
		int updateResult = boardDAO.increaseCount(sqlSession, boardNo);
		if(updateResult == 0) {
			sqlSession.close();
			return null;
		}
		BoardDTO board = boardDAO.findByBoardNo(sqlSession, boardNo);
		
		if(board != null) {
			sqlSession.commit();
		}
		sqlSession.close();
		return board;
	}
}
