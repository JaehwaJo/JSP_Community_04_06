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

  <table border="1" style="text-align: center">
    <colgroup>
      <col width="100">
      <col>
      <col>
      <col width="100">
      <col width="100">
    </colgroup>
    <thead>
    <tr>
      <th>번호</th>
      <th>작성날짜</th>
      <th>수정날짜</th>
      <th>제목</th>
      <th>내용</th>
      <th>비고</th>
    </tr>
    </thead>
    <tbody>
    <tr>
      <td><%= articleRow.get("id")%>번</td>
      <td><%= articleRow.get("regDate")%></td>
      <td><%= articleRow.get("updateDate")%></td>
      <td><%= articleRow.get("title") %></td>
      <td><%= articleRow.get("body") %></td>
      <td>
        <a href="doDelete?id=<%= articleRow.get("id")%>">삭제</a>
        <a href="modify?id=<%= articleRow.get("id")%>">수정</a>
      </td>
    </tr>
    </tbody>
  </table>

  <div>
    <a href="list">리스트로 돌아가기</a>
  </div>

</body>
</html>