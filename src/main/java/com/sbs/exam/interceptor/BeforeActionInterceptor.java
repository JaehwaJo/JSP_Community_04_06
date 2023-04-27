package com.sbs.exam.interceptor;

import com.sbs.exam.Rq;
import com.sbs.exam.dto.Member;
import com.sbs.exam.util.Util;

public class BeforeActionInterceptor extends Interceptor {
  @Override
  public boolean runBeforeAction(Rq rq) {
    // 모든 요청을 들어가기 전에 무조건 해야 하는 일 시작
    String loginedMemberJson = rq.getSessionAttr("loginedMemberJson");

    if (loginedMemberJson != null) {
      rq.setLogined(true);
      rq.setLoginedMember(Util.toObjFromJson(loginedMemberJson, Member.class));
      rq.setLoginedMemberId(rq.getLoginedMember().getId());
      rq.setLoginedMemberName(rq.getLoginedMember().getName());
    }

    rq.setAttr("rq", rq);
    // 모든 요청을 들어가기 전에 무조건 해야 하는 일 끝

    return true;
  }
}