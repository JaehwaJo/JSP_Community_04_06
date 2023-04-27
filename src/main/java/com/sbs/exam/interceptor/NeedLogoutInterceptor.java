package com.sbs.exam.interceptor;

import com.sbs.exam.Rq;

public class NeedLogoutInterceptor extends Interceptor {
  @Override
  public boolean runBeforeAction(Rq rq) {
    return true;
  }
}