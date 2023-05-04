package com.sbs.exam.sevlet;

import com.sbs.exam.Config;
import com.sbs.exam.Rq;
import com.sbs.exam.container.Container;
import com.sbs.exam.controller.ArticleController;
import com.sbs.exam.controller.MemberController;
import com.sbs.exam.exception.SQLErrorException;
import com.sbs.exam.interceptor.Interceptor;
import com.sbs.exam.util.DBUtil;
import com.sbs.exam.util.SecSql;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet("/usr/*")
public class DispatcherServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    new Container().init();
    Rq rq = new Rq(req, resp);

    // DB 연결시작
    Connection conn = null;
    String driverName = Config.getDriverClassName();

    try {
      Class.forName(driverName);
    } catch (ClassNotFoundException e) {
      System.out.printf("[ClassNotFoundException 예외, %s]", e.getMessage());
      System.out.println("DB 드라이버 클래스 로딩 실패");
      return;
    }

    try {
      conn = DriverManager.getConnection(Config.getDBUrl(), Config.getDBId(), Config.getDBPw());
      Container.conn = conn;

      if(runInterceptor(rq) == false) {
        return;
      }

      switch (rq.getControllerTypeName()) {
        case "usr" :
          switch (rq.getControllerName()) {
            case "home":
              Container.homeController.performAction(rq);
              break;
            case "article":
              Container.articleController.performAction(rq);
              break;
            case "member":
              Container.memberController.performAction(rq);
              break;
          }
      }

    } catch (SQLException e) {
      e.printStackTrace();
    } catch (SQLErrorException e) {
      e.getOrigin().printStackTrace();
    } finally {
      try {
        if (conn != null && !conn.isClosed()) {
          conn.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    // DB 연결 끝
  }

  private boolean runInterceptor(Rq rq) {
    if(Container.beforeActionInterceptor.runBeforeAction(rq) == false) {
      return false;
    }

    if(Container.needLoginInterceptor.runBeforeAction(rq) == false) {
      return false;
    }

    if(Container.needLogoutInterceptor.runBeforeAction(rq) == false) {
      return false;
    }

    return true;
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    doGet(req, resp);
  }
}