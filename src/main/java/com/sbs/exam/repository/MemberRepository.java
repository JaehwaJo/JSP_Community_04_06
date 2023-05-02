package com.sbs.exam.repository;

import com.sbs.exam.container.Container;
import com.sbs.exam.dto.Article;
import com.sbs.exam.dto.Member;
import com.sbs.exam.util.DBUtil;
import com.sbs.exam.util.SecSql;

import java.util.Map;

public class MemberRepository {
  public Member getMemberByLoginId(String loginId) {
    SecSql sql = new SecSql();
    sql.append("SELECT *");
    sql.append("FROM member");
    sql.append("WHERE loginId = ?", loginId);

    return new Member(DBUtil.selectRow(Container.conn, sql));
  }

  public int join(String loginId, String loginPw, String name) {
    SecSql sql = SecSql.from("INSERT INTO `member`");
    sql.append("SET regDate = NOW()");
    sql.append(", updateDate = NOW()");
    sql.append(", loginId = ?", loginId);
    sql.append(", loginPw = ?", loginPw);
    sql.append(", name = ?", name);

    int id = DBUtil.insert(Container.conn, sql);

    return id;
  }

  public boolean isJoinDuplicateLoginId(String loginId) {
    SecSql sql = SecSql.from("SELECT COUNT(*) AS cnt");
    sql.append("FROM member");
    sql.append("WHERE loginId = ?", loginId);

    return DBUtil.selectRowIntValue(Container.conn, sql) == 0;
  }

  public Member getMemberByName(String name) {

    SecSql sql = new SecSql();
    sql.append("SELECT *");
    sql.append("FROM member");
    sql.append("WHERE name = ?", name);

    return new Member(DBUtil.selectRow(Container.conn, sql));
  }
}