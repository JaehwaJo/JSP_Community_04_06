<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="java.util.Map" %>

<%
  Map<String, Object> articleRow = (Map<String, Object>) request.getAttribute("articleRow");
%>

<!doctype html>
<html lang="ko">
<head>
  <title>게시물 수정</title>
</head>
<body>
  <h1>게시물 수정</h1>

  <form action="doModify" method="POST">
    <input type="hidden" name="id" value="${param.id}">

    <div>제목 : <input placeholder="제목을 입력해주세요." name="title" type="text" value="<%= (String) articleRow.get("title") %>"></div>
    <div>내용 : <textarea placeholder="내용을 입력해주세요." name="body" type="text"><%= (String) articleRow.get("body") %></textarea></div>
    <div>
      <button type="submit">수정</button>
      <a href="list">리스트로 돌아가기</a>
    </div>
  </form>

</body>
</html>