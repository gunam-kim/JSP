package com.kh.mfw.common;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Template {
	/*
		JDBC Util
	*/
	// MyBatis Framework Version
	public static SqlSession getSqlSession() {
		SqlSession sqlSession = null;
		try {
			InputStream stream = Resources.getResourceAsStream("/mybatis-config.xml");
			// 1. SqlSessionFactoryBuilder 객체 생성
			// 2. SqlSessionFactory 객체 생성
			// build스트림으로부터 환경설정 파일의 값을 읽어오면서 객체 생성
			// 3. SqlSession 객체 생성
			sqlSession =
				new SqlSessionFactoryBuilder().build(stream).openSession();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sqlSession;
	}
}
