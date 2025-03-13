package com.kh.mfw.member.model.service;

import org.apache.ibatis.session.SqlSession;

import static com.kh.mfw.common.Template.getSqlSession;
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
		return null;
	}
}