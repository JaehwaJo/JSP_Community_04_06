<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="게시물 상세보기"/>
<%@ include file="../part/head.jspf" %>

<section class="section-article section-article-detail">
  <div class="con mx-auto">
    <div class="mt-[50px] flex justify-end">
      <a href="list" class="badge badge-primary hover:underline">리스트로 돌아가기</a>&nbsp;
    </div>
    <div class="article-detail__box p-[10px]">
      <div class="detail__id flex items-center h-[50px] border-b">
        <span class="badge">
          번호
        </span>
        <div class="flex-grow text-center">
          <span>${article.id}번</span>
        </div>
      </div>
      <div class="detail__regDate flex items-center h-[50px] border-b">
        <span class="badge">
          작성날짜
        </span>
        <div class="flex-grow text-center">
          <span>${article.regDate}</span>
        </div>
      </div>
      <div class="detail__updateDate flex items-center h-[50px] border-b">
        <span class="badge">
          수정날짜
        </span>
        <div class="flex-grow text-center">
          <span>${article.updateDate}</span>
        </div>
      </div>
      <div class="detail__title flex items-center h-[50px] border-b">
        <span class="badge">
          제목
        </span>
        <div class="flex-grow text-center">
          <span>${article.title}</span>
        </div>
      </div>
      <div class="detail__body flex flex-col h-[300px] mt-[10px]">
        <span class="badge">
          내용
        </span>
        <div class="flex-grow mt-[10px]">
          <span>${article.body}</span>
        </div>
      </div>
    </div>

    <div class="btns flex justify-end gap-x-[5px] mt-3">
      <a class="btn btn-primary" href="doDelete?id=${param.id}">삭제</a>
      <a class="btn btn-secondary" href="modify?id=${param.id}">수정</a>
    </div>
  </div>
</section>
<%@ include file="../part/foot.jspf" %>