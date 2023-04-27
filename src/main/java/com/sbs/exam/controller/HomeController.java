package com.sbs.exam.controller;

import com.sbs.exam.Rq;

public class HomeController extends Controller{
  @Override
  public void performAction(Rq rq) {
    switch (rq.getActionMethodName()) {
      case "main":
        actionShowHome(rq);
        break;
    }
  }

  private void actionShowHome(Rq rq) {
    rq.jsp("../home/main");
  }
}
