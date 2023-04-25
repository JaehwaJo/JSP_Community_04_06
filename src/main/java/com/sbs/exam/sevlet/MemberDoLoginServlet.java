package com.sbs.exam.sevlet;

import com.sbs.exam.Config;
import com.sbs.exam.Rq;
import com.sbs.exam.exception.SQLErrorException;
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
import java.util.Map;

@WebServlet("/member/doLogin")
public class MemberDoLoginServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

      String loginId = rq.getParam("loginId", "");
      String loginPw = rq.getParam("loginPw", "");

      SecSql sql = SecSql.from("SELECT *");
      sql.append("FROM member");
      sql.append("WHERE loginId = ?", loginId);

      Map<String, Object> memberRow = DBUtil.selectRow(conn, sql);

      if(memberRow.isEmpty()) {
        rq.print("<script>alert('%s(은)는 없는 아이디입니다.'); history.back();</script>".formatted(loginId));
        return;
      }

      if(((String)memberRow.get("loginPw")).equals(loginPw) == false ) {
        rq.print("<script>alert('로그인 비번이 일치하지 않습니다.'); history.back();</script>");
        return;
      }

      HttpSession session = req.getSession();
      session.setAttribute("loginedMemberId", memberRow.get("id"));

      rq.print("<script>alert('로그인 되었습니다.'); location.replace('../home/main');</script>");


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

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    doGet(req, resp);
  }
}