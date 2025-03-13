package com.kh.mfw.member.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kh.mfw.member.model.dto.MemberDTO;

public class MemberDAO {
	// DB와의 영속성 연결
	
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public MemberDTO login(MemberDTO member) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = """
					SELECT
						MEMBER_ID,
						MEMBER_PW,
						MEMBER_NAME,
						EMAIL,
						ENROLL_DATE
					FROM
						KH_MEMBER
					WHERE
						MEMBER_ID = ?
					AND
						MEMBER_PW = ?
					""";
		
		MemberDTO loginMember = null;
		
		try {
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@112.221.156.34:12345:XE",
					"KH03_KGN",
					"KH1234"
					);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPw());
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				loginMember = new MemberDTO(
						rset.getString("MEMBER_ID"),
						rset.getString("MEMBER_PW"),
						rset.getString("MEMBER_NAME"),
						rset.getString("EMAIL"),
						rset.getDate("ENROLL_DATE")
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rset != null) rset.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return loginMember;
	}
	
	public int checkId(String memberId) {
		String sql = """
						SELECT
							COUNT(*)
						FROM
							KH_MEMBER
						WHERE
							MEMBER_ID = ?
					""";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		int result = 0;
		try {
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@112.221.156.34:12345:XE",
					"KH03_KGN",
					"KH1234");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			rset = pstmt. executeQuery();
			/*
				case 1) count(*) 그룹함수를 사용했을 때
					: 무조건 ResultSet이 1행 존재함
					: 컬럼값이 0 or 1인 것으로 조회결과 판별
			*/
			rset.next();
			result = rset.getInt("COUNT(*)");
			/*
				case 2) MEMBER_ID 컬럼을 조회한 경우
					rset.getString("MEMBER_ID")
			*/
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rset != null) rset.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;		
	}
	
	public void signUp(MemberDTO member) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = """
						INSERT INTO
							KH_MEMBER
						VALUES
							(
							?,
							?,
							?,
							?,
							DEFAULT
							)
					""";
		try {
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@112.221.156.34:12345:XE",
					"KH03_KGN",
					"KH1234");
			pstmt = conn.prepareStatement(sql);
			System.out.println(member);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPw());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getEmail());
			pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
