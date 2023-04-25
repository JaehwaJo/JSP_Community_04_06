<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="게시물 리스트"/>
<%@ include file="../part/head.jspf" %>

<div>
  <a href="../home/main">홈으로 돌아가기</a>&nbsp;
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
  <c:forEach items="${articles}" var="article">
    <tr>
      <td>${article.id} 번</td>
      <td>${article.regDate}</td>
      <td>${article.updateDate}</td>
      <td>
        <a href="detail?id=${article.id}">
          ${article.title}
        </a>
      </td>
    </tr>
  </c:forEach>
  </tbody>
</table>

<style>
    .page > a.red {
      color: red;
    }
</style>

<div class="page">
  <c:set var="cPage" value="${page}"/>
  <c:set var="totalPage" value="${totalPage}"/>
  <c:set var="pageMenuSize" value="5"/>
  <c:set var="from" value="${cPage - pageMenuSize}"/>

  <c:if test="${cPage > 1}">
    <a href="list?page=1">◀</a>
  </c:if>

  <c:set var="start" value="${from < 1 ? 1 : from}"/>

  <c:set var="end" value="${cPage + 10}"/>

  <c:if test="${end > totalPage}">
    <c:set var="end" value="${totalPage}"/>
  </c:if>

  <c:forEach var="i" begin="${start}" end="${end}" step="1">
    <c:set var="aClassRed" value="${cPage == i ? 'red' : ''}"/>
    <a class="${aClassRed}" href="list?page=${i}">${i}</a>
  </c:forEach>

  <c:if test="${cPage < totalPage}">
    <a href="list?page=${totalPage}">▶</a>
  </c:if>

</div>

<%@ include file="../part/foot.jspf" %>