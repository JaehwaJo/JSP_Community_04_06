package com.sbs.exam.interceptor;

import com.sbs.exam.Rq;

public class NeedLoginInterceptor extends Interceptor {
  @Override
  public boolean runBeforeAction(Rq rq) {
    return true;
  }
}