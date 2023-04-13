<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>

<%
  List<Map<String, Object>> articleRows = (List<Map<String, Object>>) request.getAttribute("articleRows");
  int cPage = (int) request.getAttribute("page");
  int totalPage = (int) request.getAttribute("totalPage");
%>

<!doctype html>
<html lang="ko">
<head>
  <title>게시물리스트</title>
</head>
<body>
  <h1>게시물 리스트</h1>

  <div>
    <a href="../home/main">홈으로 돌아가기</a>
    &nbsp;
    <a href="write">게시물 작성</a>
  </div>

  <table border="1" style="text-align:center;">
    <colgroup>
      <col width="100">
      <col>
      <col>
      <col width="100">
    </colgroup>
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
          <td>
            <a href="detail?id=<%= (int) articleRow.get("id")%>">
              <%= articleRow.get("title")%>
            </a>
          </td>
        </tr>
        <% } %>
    </tbody>
  </table>

  <style>
    .page > a.red {
      color: red;
    }
  </style>

  <div class="page">
    <% if( cPage > 1 ) { %>
          <a href="list?page=1">◀</a>
        <% } %>

        <%
        int pageMenuSize = 5;
        int from = cPage - pageMenuSize;
        int end = cPage + pageMenuSize;

        if(from < 1) {
          from = 1;
        }
        if(end > totalPage) {
          end = totalPage;
        }

        for(int i = from; i <= end; i++) {
        %>
          <a class="<%= cPage == i ? "red" : "" %>" href="list?page=<%=i%>"><%=i%></a>
        <% } %>

        <% if( cPage < totalPage ) { %>
        <a href="list?page=<%= totalPage %>">▶</a>
        <% } %>
  </div>

</body>
</html>