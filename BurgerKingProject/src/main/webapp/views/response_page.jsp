<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kh.burgerking.model.dto.UserDTO" %>

<%-- <%	// Java 코드 쓸 수 있음
	String message = (String)request.getAttribute("message");
	UserDTO user = (UserDTO)request.getAttribute("user");
	
	String userId = user.getUserId();
	String userName = user.getUserName();
%> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>응답 페이지</title>
</head>
<body>
	<!--
		"사용자가 입력한 userName"님!
		회원가입에 성공하였습니다
		가입한 아이디 : userId
	-->
	<%-- <h1><%= userName %>님!</h1> --%>
	<h1>${ user.userId }님!</h1>
	<p>
		<%-- <%= message %><br> --%>
		${message}<br>
		회원가입에 성공하였습니다<br>
		<%-- 가입한 아이디 : <%= userId %> --%>
		가입한 아이디 : ${user.userId}
	</p>
</body>
</html>