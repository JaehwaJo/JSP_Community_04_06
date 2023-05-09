<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="회원가입"/>
<%@ include file="../part/head.jspf" %>
<script>
    let JoinForm__submitDone = false;

    function JoinForm__submit(form) {
      if(JoinForm__submitDone) {
        alert('처리 중입니다.');
        return;
      }

      form.loginId.value = form.loginId.value.trim();

      if(form.loginId.value.length == 0) {
        alert('로그인 아이디를 입력해주세요.');
        form.loginId.focus();
        return;
      }

      form.loginPw.value = form.loginPw.value.trim();

      if(form.loginPw.value.length == 0) {
        alert('로그인 비번을 입력해주세요.');
        form.loginPw.focus();
        return;
      }

      form.loginPwConfirm.value = form.loginPwConfirm.value.trim();

      if(form.loginPwConfirm.value.length == 0) {
        alert('로그인 비번 확인을 입력해주세요.');
        form.loginPwConfirm.focus();
        return;
      }

      if(form.loginPw.value != form.loginPwConfirm.value) {
        alert('로그인 비번이 일치하지 않습니다.');
        form.loginPwConfirm.focus();
        return;
      }

      form.name.value = form.name.value.trim();

      if(form.name.value.length == 0) {
        alert('이름을 입력해주세요.');
        form.name.focus();
        return;
      }

      form.submit();
      JoinForm__submitDone = true;
    }
  </script>



<form action="doJoin" method="POST" onsubmit="JoinForm__submit(this); return false">
  <section class="join-wrap min-h-screen flex items-center justify-center">
    <div class="form-control w-full max-w-xs">
      <h1 class="text-center text-[2rem] font-bold mb-[20px]">회원 가입</h1>
      <label class="label">
        <span class="label-text">로그인 아이디</span>
      </label>
      <input type="text" name="loginId" placeholder="로그인 아이디를 입력해주세요." class="input input-bordered w-full max-w-xs" />
      <label class="label">
        <span class="label-text">비밀번호</span>
      </label>
      <input type="password" name="loginPw" placeholder="로그인 비밀번호를 입력해주세요." class="input input-bordered w-full max-w-xs" />
      <label class="label">
        <span class="label-text">로그인 비밀번호 확인</span>
      </label>
      <input type="password" name="loginPwConfirm" placeholder="로그인 비밀번호 확인 입력해주세요." class="input input-bordered w-full max-w-xs" />
      <label class="label">
        <span class="label-text">이름</span>
      </label>
      <input type="text" name="name" placeholder="이름을 입력해주세요." class="input input-bordered w-full max-w-xs" />
      <div class="btns mt-[10px] ml-auto">
        <button type="submit" class="btn btn-primary ">가입</button>
        <button type="button" class="btn btn-secondary">
          <a href="../home/main">취소</a>
        </button>
      </div>
    </div>
  </section>
</form>

<%@ include file="../part/foot.jspf" %>