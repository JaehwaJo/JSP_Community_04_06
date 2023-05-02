package com.sbs.exam.repository;

import com.sbs.exam.container.Container;
import com.sbs.exam.util.DBUtil;
import com.sbs.exam.util.SecSql;

import com.sbs.exam.dto.Member;
import java.sql.Connection;

public class MemberRepository {
  public Member getMemberByLoginId(String loginId) {
    SecSql sql = SecSql.from("SELECT *");
    sql.append("FROM member");
    sql.append("WHERE loginId = ?", loginId);

    return new Member(DBUtil.selectRow(Container.conn, sql));
  }

  public int join(String loginId, String loginPw, String name) {
    SecSql sql = SecSql.from("INSERT INTO member");
    sql.append("SET regDate = NOW()");
    sql.append(", updateDate = NOW()");
    sql.append(", loginId = ?", loginId);
    sql.append(", loginPw = ?", loginPw);
    sql.append(", name = ?", name);

    int id = DBUtil.insert(Container.conn, sql);

    return id;
  }
}