package com.kh.mfw.member.model.service;

import com.kh.mfw.member.model.dao.MemberDAO;
import com.kh.mfw.member.model.dto.MemberDTO;

public class MemberService {
	public MemberDTO login(MemberDTO member) {
		/*
			로그인
			: DB에 가서 SELECT절에 사용자가 입력한
			아이디/비밀번호값을 조건절에 사용해 조회를 하는 절차
			
			아이디or비밀번호가 틀려서 로그인 실패할 가능성 있음
			
			유효성 검사 (Validation)
		*/
		MemberDTO loginMember = new MemberDAO().login(member);
		return loginMember;
	}
	
	public int signUp(MemberDTO member) {
		int result = new MemberDAO().checkId(member.getMemberId());
		if(result > 0) {
			return result;
		}
		new MemberDAO().signUp(member);
		return result;
	}
}