<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
int age = (int) request.getAttribute("age");
boolean isLogined = (boolean) request.getAttribute("isLogined");
%>

<!doctype html>
<html lang="ko">
<head>
  <title>메인</title>
</head>
<body>
<h1>메인 페이지</h1>

<% if( isLogined ) { %>
<div>나이는 <%=age%>살 입니다.</div>
<% } %>

<div>
  <a href="../article/list">게시물 리스트</a>
  &nbsp;
  <a href="../member/join">회원가입</a>
  &nbsp;
  <a href="../member/login">로그인</a>
</div>

</body>
</html>