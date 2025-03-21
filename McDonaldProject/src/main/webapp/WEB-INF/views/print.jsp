<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// String brand = (String)request.getAttribute("brand");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Print</title>
</head>
<body>
	<br>
	${ brand }<br>
	${ bestSeller }<br>
	<ul>
		<li>브랜드 : ${ bestSeller.brand }</li>
		<li>버거 : ${ bestSeller.name }</li>
		<li>가격 : ${ bestSeller.price }원</li>
	</ul>
	
	두개 이상의 Scope에 같은 키 값으로 값을 담은 경우 <br>
		: page -> request -> session -> application 순으로 키 값을 검색 <br>
	<br>
	# Scope에 직접 접근하는 방법 <br>
	requestScope : ${requestScope.brand} <br>
	sessionScope : ${sessionScope.brand} <br>
	<br>
	만약 없는 키값 EL구문으로 출력하려고 하면 어떻게 될까? <br>
	없는 값 : ${sessionScope.abc} <br>
	<hr>
	# 연산은 어디서 하는게 제일 좋을까? <br>
	1) SQL문 DB단 <br>
	2) Java Service단 (validation / transaction) <br>
	3) View <br>
	<hr>
	<h3>1. 산술연산</h3>
	<p>
	* EL구문을 이용한 산술연산 <br>
	big + small = ${big + small} <br>
	big - small = ${big - small} <br>
	big x small = ${big * small} <br>
	big ÷ small = ${big / small} 또는 ${big div small} <br>
	big ÷ small = ${big % small} 또는 ${big mod small} <br>
	</p>
	<h3>2. 논리연산</h3>
	<p>
	AND : ${true && true} 또는 ${true and true} <br>
	OR : ${false || false} 또는 ${false or false} <br>
	</p>
	<h4>숫자끼리 비교</h4>
	<p>
	big이 small보다 작나? : ${big < small} 또는 ${big lt small} <br>
	big이 small보다 크나? : ${big > small} 또는 ${big gt small} <br>
	big이 small보다 작거나 같나? : ${big le small} <br>
	big이 small보다 크거나 같나? : ${big ge small} <br>
	</p>
	<h4>동등비교</h4>
	<p>
	big이 small과 같나? : ${big == small} 또는 ${big eq small} <br>
	big이 10과 같나? : ${big eq 10} <br>
	str이 문구와 같나? : ${str eq 'carpe diem'} <br>
	big이 10과 같지 않나? : ${big ne 10} <br>
	</p>
	<h4>객체가 비어있는지 체크</h4>
	<p>
	bestSeller가 null과 일치하나? <br>
	${bestSeller == null} 또는 ${bestSeller eq null} 또는 ${empty bestSeller} <br>
	<br>
	list가 비어있지 않나? <br>
	${!empty list} <br>
	</p>
</body>
</html>