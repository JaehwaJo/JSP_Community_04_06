<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="로그인"/>
<%@ include file="../part/head.jspf" %>
<script>
    let LoginForm__submitDone = false;
    function LoginForm__submit(form) {
      if(LoginForm__submitDone) {
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
      form.submit();
      LoginForm__submitDone = true;
    }
  </script>

<form action="doLogin" method="POST" onsubmit="LoginForm__submit(this); return false">
  <div class="login-box h-screen flex flex-col items-center justify-center">
    <div class="login-box__input flex flex-col gap-y-[10px]">
      <input type="text" name="loginId" placeholder="로그인 아이디를 입력해주세요." class="input input-bordered w-[300px] max-w-xs" value="${param.loginId}"/>
      <input type="password" name="loginPw" placeholder="로그인 비밀번호를 입력해주세요." class="input input-bordered w-[300px] max-w-xs"/>
    </div>
    <div class="login__box-btns flex flex-col gap-y-[10px] mt-[10px]">
      <button type="submit" class="btn btn-primary w-[300px]">로그인</button>
      <div class="login__box-sub-btns text-center">
        <a href="../member/findLoginId">아이디찾기</a>
        &nbsp;
        <a href="../member/join">회원가입</a>
        &nbsp;
        <a href="../home/main">취소</a>
      </div>
    </div>
  </div>
</form>

<%@ include file="../part/foot.jspf" %>