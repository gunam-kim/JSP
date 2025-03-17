package com.kh.mfw.member.model.dao;

import org.apache.ibatis.session.SqlSession;

import com.kh.mfw.member.model.dto.MemberDTO;

public class MemberDAO {
	public MemberDTO login(SqlSession sqlSession, MemberDTO member) {
		/*
			SqlSession이 제공하는 메소드를 통해
			SQL문을 찾아서 실행하고 결과를 받을 수 있음
		*/
// sqlSession.SQL문종류에맞는 메소드("mapper파일의 namespace.SQL문의id속성값")
//		MemberDTO loginMember =
//				sqlSession.selectOne(
//						"memberMapper.login", member);
//		System.out.println(loginMember);
		return sqlSession.selectOne(
				"memberMapper.login", member);
	}

	public boolean checkId(SqlSession sqlSession, String memberId) {
		return (Integer)sqlSession.selectOne("memberMapper.checkId", memberId) > 0 ? true : false;
	}

	public int signUp(SqlSession sqlSession, MemberDTO member) {
		return sqlSession.insert("memberMapper.signUp", member);
	}
}