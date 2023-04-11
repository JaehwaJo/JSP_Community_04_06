<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>

<%
  List<Map<String, Object>> articleRows = (List<Map<String, Object>>) request.getAttribute("articleRows");
%>

<!doctype html>
<html lang="ko">
<head>
  <title>게시물리스트</title>
</head>
<body>
  <h1>게시물 리스트</h1>

  <table border="1">
    <thead>
      <tr>
        <th>번호</th>
        <th>작성날짜</th>
        <th>수정날짜</th>
        <th>제목</th>
      </tr>
    </thead>
    <tbody>
        <%
        for( Map<String, Object> articleRow : articleRows) {
        %>
        <tr>
          <td><%= articleRow.get("id")%>번</td>
          <td><%= articleRow.get("regDate")%></td>
          <td><%= articleRow.get("updateDate")%></td>
          <td><%= articleRow.get("title")%></td>
        </tr>
        <% } %>
    </tbody>
  </table>



</body>
</html>