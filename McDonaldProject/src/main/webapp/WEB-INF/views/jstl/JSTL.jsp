<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>JSTL이란</h1>
	<p>
	: Java server pages Standard Tag Library <br>
	: 공통적으로 사용해야 하는 코드들을 보다 쉽게 사용할 수 있도록 태그화해서 표준으로 제공하는 라이브러리
	</p>
	<hr>
	<p>
	JSTL을 사용하기 위해선 먼저 라이브러리를 추가해준 후 <br>
	JSTL을 사용하고자 하는 JSP페이지 상단에 선언을 해주어야한다 <br>
	</p>
	<hr>
	
	<h2>Core 라이브러리 실전압축핵심요약</h2>
	<h3>if</h3>
	<p>
	: if라는 태그를 작성하여 조건문을 만들어낼 수 있음 <br>
	: Java에서의 단일 if문과 똑같은 역할을 할 수 있는 태그 <br>
	: 조건식은 test 속성에 EL구문으로 작성 <br>
	</p>
	<c:if test="${ 1 lt 2 }">
		<strong>1이 2보다 작습니다</strong>
	</c:if>
	<br>
	
	<c:if test="${ 1 gt 2 }">
		<strong>1이 2보다 큽니다</strong>
	</c:if>
	
	<c:if test="${ '안녕하세요' ne '반갑습니다' }">
		<mark>안녕하세요와 반갑습니다는 같지 않습니다</mark>
	</c:if>
	<hr>
	
	<h3>choose, when, otherwise</h3>
	<!--
	포인트 사용량이
	~100 : 일반회원
	100~300 : 우수회원
	300~ : 최우수회원
	-->
	<c:choose>
		<c:when test="${point le 100}">
			일반회원 <br>
		</c:when>
		<c:when test="${point le 300}">
			<bold>우수회원</bold> <br>
		</c:when>
		<c:otherwise>
			<mark style="color:red; background:pink">최우수회원</mark> <br>
		</c:otherwise>
	</c:choose>
	<hr>
	
	<h3>forEach</h3>
	<pre>
	[표현법]
	for loop문
	1)
		&lt;c:forEach var="속성명" begin="초기값" end="끝값" step="증가시킬값(생략가능)" &gt;
			반복적으로 실행할 내용
		&lt;c:/forEach&gt;
	2)
		&lt;c:forEach var="속성명" items="순차적으로 접근할 배열/컬렉션" &gt;
			반복적으로 실행할 내용
		&lt;c:/forEach&gt;
	</pre>
	
	<c:forEach var="i" begin="1" end="9">
		i
	</c:forEach>
	<br>
	
	<c:forEach var="i" begin="1" end="9">
		${i}
	</c:forEach>
	<br>
	
	<c:forEach var="i" begin="1" end="6">
		<h${i}>이런것도 가능</h${i}>
	</c:forEach>
	<br>
	<hr>
	
	<ul>
		<c:forEach var="c" items="${colors}">
			<li style="color:${c}">${c}</li>
		</c:forEach>
	</ul>
	<hr>
	
	<h3>회원 목록</h3>
	<!--
		caption,
		thead, tbody, tfoot,
		tr, th, td
	-->
	<table>
		<thead>
			<tr>
				<th>번호</th>
				<th>이름</th>
				<th>아이디</th>
				<th>가입일</th>
			</tr>
		</thead>
		
		<tbody>
			<c:choose>
				<c:when test="${empty requestScope.users}">
					<tr>
						<td colspan="4">조회 결과가 존재하지 않습니다.</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach items="${requestScope.users}" var="user">
						<tr>
							<td>${user.userNo}</td>
							<td>${user.userName}</td>
							<td>${user.userId}</td>
							<td>${user.enrollDate}</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
</body>
</html>