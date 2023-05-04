package com.sbs.exam.interceptor;

import com.sbs.exam.Rq;

public class NeedLoginInterceptor extends Interceptor {
  @Override
  public boolean runBeforeAction(Rq rq) {

    if (rq.getControllerTypeName().equals("usr") == false) {
      return true;
    }

    switch (rq.getActionPath()) {
      case "/usr/article/write" :
      case "/usr/article/modify" :
      case "/usr/article/doModify" :
      case "/usr/article/delete" :
      case "/usr/article/doDelete" :
      case "/usr/article/doLogout" :
      case "/usr/article/myPage" :
        if (rq.isNotLogined()) {
          rq.historyBack("로그인 후 이용해주세요.");
          return false;
        }
    }

    return true;
  }
}