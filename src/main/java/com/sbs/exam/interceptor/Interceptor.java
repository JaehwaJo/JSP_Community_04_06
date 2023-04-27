package com.sbs.exam.interceptor;

import com.sbs.exam.Rq;

public abstract class Interceptor {
  abstract public boolean runBeforeAction(Rq rq);
}
