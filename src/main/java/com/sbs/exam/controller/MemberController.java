package com.sbs.exam.controller;

import com.sbs.exam.Rq;
import com.sbs.exam.container.Container;
import com.sbs.exam.dto.Member;
import com.sbs.exam.dto.ResultData;
import com.sbs.exam.service.MemberService;
import com.sbs.exam.util.DBUtil;
import com.sbs.exam.util.SecSql;
import com.sbs.exam.util.Util;

public class MemberController extends Controller {
  private MemberService memberService = Container.memberService;

  @Override
  public void performAction(Rq rq) {
    switch (rq.getActionMethodName()) {
      case "login":
        actionShowLogin(rq);
        break;
      case "doLogin":
        actionDoLogin(rq);
        break;
      case "doLogout":
        actionDoLogout(rq);
        break;
      case "join":
        actionShowJoin(rq);
        break;
      case "doJoin":
        actionDoJoin(rq);
        break;
      case "findLoginId":
        actionShowFindLoginId(rq);
        break;
      case "doFindLoginId":
        actionDoFindLoginId(rq);
        break;
      default:
        rq.println("존재하지 않는 페이지입니다.");
        break;
    }
  }


  private void actionShowFindLoginId(Rq rq) {
    rq.jsp("../member/findLoginId");
  }

  private void actionDoFindLoginId(Rq rq) {
    String name = rq.getParam("name", "");

    if(name.length() == 0) {
      rq.historyBack("name(을)를 입력해주세요.");
      return;
    }

    Member oldMember = memberService.getMemberByName(name);

    if(oldMember == null) {
      rq.historyBack("일치하는 회원이 존재하지 않습니다.");
      return;
    }

    String replaceUri = "../member/login?loginId=" + oldMember.getLoginId();
    rq.replace(Util.f("해당 회원의 로그인 아이디는 `%s` 입니다.", oldMember.getLoginId()), replaceUri);
  }

  private void actionDoJoin(Rq rq) {
    String loginId = rq.getParam("loginId", "");
    String loginPw = rq.getParam("loginPw", "");
    String name = rq.getParam("name", "");

    if(loginId.length() == 0) {
      rq.historyBack("loginId(을)를 입력해주세요");
      return;
    }

    if(loginPw.length() == 0) {
      rq.historyBack("loginPw(을)를 입력해주세요");
      return;
    }

    if(name.length() == 0) {
      rq.historyBack("name(을)를 입력해주세요");
      return;
    }

    ResultData joinRd = memberService.join(loginId, loginPw, name);

    rq.replace(joinRd.getMsg(), "../member/login");
  }

  private void actionShowJoin(Rq rq) {
    rq.jsp("../member/join");
  }

  private void actionDoLogout(Rq rq) {
    rq.removeSessionAttr("loginedMemberJson");
    rq.replace("로그아웃 되었습니다.", "../home/main");
  }

  private void actionDoLogin(Rq rq) {
    String loginId = rq.getParam("loginId", "");
    String loginPw = rq.getParam("loginPw", "");

    if (loginId.length() == 0) {
      rq.historyBack("loginId를 입력해주세요.");
      return;
    }

    if (loginPw.length() == 0) {
      rq.historyBack("loginPw를 입력해주세요.");
      return;
    }

    ResultData loginRd = memberService.login(loginId, loginPw);

    if(loginRd.isFail()) {
      rq.historyBack(loginRd.getMsg());
    }

    Member member = (Member) loginRd.getBody().get("member");

    rq.setSessionAttr("loginedMemberJson", Util.toJson(member, ""));

    rq.replace(loginRd.getMsg(), "../home/main");
  }

  private void actionShowLogin(Rq rq) {
    rq.jsp("../member/login");
  }
}