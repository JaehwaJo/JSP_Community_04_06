<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="게시물 리스트"/>
<%@ include file="../part/head.jspf" %>

<section class="section-article-list">
  <div class="con mx-auto">
    <div class="mt-[50px] flex justify-end">
      <a href="../home/main" class="badge badge-primary hover:underline">홈으로 돌아가기</a>&nbsp;
      <a href="write" class="badge badge-secondary hover:underline">게시물 작성</a>
    </div>

    <div class="overflow-x-auto mt-[10px]">
      <table class="table w-full">
        <colgroup>
          <col width="30px">
          <col width="20%">
          <col width="20%">
          <col>
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
    </div>
    <style>
    .page > a.red {
      color: red;
    }
    </style>

    <div class="page btn-group flex justify-center">
      <c:set var="cPage" value="${page}"/>
      <c:set var="totalPage" value="${totalPage}"/>
      <c:set var="pageMenuSize" value="5"/>
      <c:set var="from" value="${cPage - pageMenuSize}"/>

      <c:if test="${cPage > 1}">
        <a class="btn" href="list?page=1">◀</a>
      </c:if>

      <c:set var="start" value="${from < 1 ? 1 : from}"/>

      <c:set var="end" value="${cPage + 7}"/>

      <c:if test="${end > totalPage}">
        <c:set var="end" value="${totalPage}"/>
      </c:if>

      <c:forEach var="i" begin="${start}" end="${end}" step="1">
        <c:set var="active" value="${cPage == i ? 'btn-active' : ''}"/>
        <a class="btn ${active}" href="list?page=${i}">${i}</a>
      </c:forEach>

      <c:if test="${cPage < totalPage}">
        <a class="btn" href="list?page=${totalPage}">▶</a>
      </c:if>
    </div>
  </div>
</section>

<%@ include file="../part/foot.jspf" %>