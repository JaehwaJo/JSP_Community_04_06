<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="java.util.Map" %>

<%
  Map<String, Object> articleRow = (Map<String, Object>) request.getAttribute("articleRow");
%>

<!doctype html>
<html lang="ko">
<head>
  <title>게시물 상세보기</title>
</head>
<body>
  <h1>게시물 상세보기</h1>

  <div>번호 : <%= (int) articleRow.get("id") %></div>
  <div>작성 날짜 : <%= (String) articleRow.get("regDate") %></div>
  <div>수정 날짜 : <%= (String) articleRow.get("updateDate") %></div>
  <div>제목 : <%= (String) articleRow.get("title") %></div>
  <div>내용 : <%= (String) articleRow.get("body") %></div>

  <div>
    <a href="list">리스트로 돌아가기</a>
  </div>

</body>
</html>