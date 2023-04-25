package com.sbs.exam.repository;

import com.sbs.exam.util.DBUtil;
import com.sbs.exam.util.SecSql;

import com.sbs.exam.dto.Member;
import java.sql.Connection;

public class MemberRepository {
  private Connection conn;
  public MemberRepository(Connection conn) {
    this.conn = conn;
  }

  public Member getMemberByLoginId(String loginId) {
    SecSql sql = SecSql.from("SELECT *");
    sql.append("FROM member");
    sql.append("WHERE loginId = ?", loginId);

    return new Member(DBUtil.selectRow(conn, sql));
  }
}