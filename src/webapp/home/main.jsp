<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
  boolean isLogined = (boolean) request.getAttribute("isLogined");
  int loginedMemberId = (int) request.getAttribute("loginedMemberId");
%>

<!doctype html>
<html lang="ko">
<head>
  <title>메인</title>
</head>
<body>
<h1>메인 페이지</h1>

<div>
  <a href="../article/list">게시물 리스트</a>

  <% if( !isLogined ) { %>
    &nbsp;
    <a href="../member/join">회원가입</a>
    &nbsp;
    <a href="../member/login">로그인</a>
  <% } %>

  <% if( isLogined ) { %>
    &nbsp;
    <%=loginedMemberId%>번 회원님 환영합니다.
    <a href="../member/doLogout">로그아웃</a>
  <% } %>

</div>

</body>
</html>