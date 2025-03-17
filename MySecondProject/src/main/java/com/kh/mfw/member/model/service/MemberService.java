package com.kh.mfw.member.model.service;

import org.apache.ibatis.session.SqlSession;

import static com.kh.mfw.common.Template.getSqlSession;

import com.kh.mfw.member.model.dao.MemberDAO;
// static ~ .getSqlSession
// 참조 연산자를 적지 않아도 된다
import com.kh.mfw.member.model.dto.MemberDTO;

public class MemberService {
	public MemberDTO login(MemberDTO member) {
		/*
			JDBC Util 클래스로부터 static Method로 구현해놓은
			getConnection Method를 호출해 Connection 객체를 반환받음
		*/
		SqlSession sqlSession = getSqlSession();
		
		// 유효성 검증 -> 패스 (원래 해야됨)
		MemberDTO loginMember =
				new MemberDAO().login(sqlSession, member);
		// 자원 반납
		sqlSession.close();
		return loginMember;
	}
	
	public int signUp(MemberDTO member) {
		// 중복 검사
		// 3차 유효성 검증 (Java)
		// 4차 데이터 무결성을 위한 제약조건 (DBMS)
		// 아이디 중복 검사
		SqlSession sqlSession = getSqlSession();
		new MemberDAO().checkId(sqlSession, member.getMemberId());
		return 0;
	}
}