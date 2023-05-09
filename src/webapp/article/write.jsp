<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="게시물 작성"/>
<%@ include file="../part/head.jspf" %>
<script>
    let ArticleWrite__submitDone = false;

    function ArticleWrite__submit(form) {
      if(ArticleWrite__submitDone) {
        alert('처리 중입니다.');
        return;
      }

      if(form.title.value.length == 0) {
        alert('제목을 입력해주세요.');
        form.title.focus();
        return;
      }

      if(form.body.value.length == 0) {
        alert('내용을 입력해주세요.');
        form.body.focus();
        return;
      }

      form.submit();
      ArticleWrite__submitDone = true;
    }



</script>

<form action="doWrite" method="POST" onsubmit="ArticleWrite__submit(this); return false;">
  <input type="hidden" name="redirectUri" value="../article/detail?id=[NEW_ID]">

  <section class="section-article section-article-write">
    <div class="con mx-auto">
      <div class="mt-[50px] flex justify-end">
        <a href="list" class="badge badge-primary hover:underline">리스트로 돌아가기</a>&nbsp;
      </div>
      <div class="article-write__box p-[10px]">
        <div class="write__title flex items-center h-[50px] pb-[10px] border-b">
          <span class="badge w-[100px]">
            제목
          </span>
          <input class="input input-bordered w-full ml-[10px]" autocomplete="off" placeholder="제목을 입력해주세요." name="title" type="text">
        </div>
        <div class="write__body flex flex-col min-h-[400px] mt-[10px]">
          <span class="badge w-[100px]">
            내용
          </span>
          <textarea class="textarea textarea-bordered flex-grow mt-[10px]" autocomplete="off" placeholder="내용을 입력해주세요." name="body" type="text"></textarea>
        </div>
      </div>
      <div class="btns flex justify-end gap-x-[10px]">
        <button class="btn btn-primary" type="submit">작성</button>
        <a class="btn btn-secondary" href="list">리스트</a>
      </div>
    </div>
  </section>

</form>

<%@ include file="../part/foot.jspf" %>