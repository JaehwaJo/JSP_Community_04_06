<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="게시물 수정"/>
<%@ include file="../part/head.jspf" %>
<h1>게시물 수정</h1>
<%@ include file="../part/topBar.jspf" %>

<form action="doModify" method="POST">
  <input type="hidden" name="id" value="${param.id}">

  <div>제목 : <input placeholder="제목을 입력해주세요." name="title" type="text" value="${article.title}">
  </div>
  <div>내용 : <textarea placeholder="내용을 입력해주세요." name="body"
                      type="text">${article.body}</textarea></div>
  <div>
    <button type="submit">수정</button>
    <a href="list">리스트로 돌아가기</a>
  </div>
</form>

<%@ include file="../part/foot.jspf" %>