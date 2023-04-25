package com.sbs.exam.repository;

import com.sbs.exam.dto.Article;
import com.sbs.exam.util.DBUtil;
import com.sbs.exam.util.SecSql;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ArticleRepository {
  private Connection conn;
  public ArticleRepository(Connection conn) {
    this.conn = conn;
  }

  public int getTotalCount() {
    SecSql sql = SecSql.from("SELECT COUNT(*) AS cnt");
    sql.append("FROM article");
    int totalCount = DBUtil.selectRowIntValue(conn, sql);

    return totalCount;
  }

  public List<Article> getArticles(int limitFrom, int itemInAPage) {
    SecSql sql = new SecSql();
    sql.append("SELECT *");
    sql.append("FROM article");
    sql.append("ORDER BY id DESC");
    sql.append("LIMIT ?, ?", limitFrom, itemInAPage);

    List<Map<String, Object>> articleRows = DBUtil.selectRows(conn, sql);

    List<Article> articles = new ArrayList<>();

    for(Map<String, Object> articleRow : articleRows) {
      articles.add(new Article(articleRow));
    }

    return articles;
  }

  public int write(String title, String body, int loginedMemberId) {
    SecSql sql = SecSql.from("INSERT INTO article");
    sql.append("SET regDate = NOW()");
    sql.append(", updateDate = NOW()");
    sql.append(", title = ?", title);
    sql.append(", body = ?", body);
    sql.append(", memberId = ?", loginedMemberId);

    int id = DBUtil.insert(conn, sql);

    return id;
  }

  public Article getForPrintArticleById(int id) {
    SecSql sql = new SecSql();
    sql.append("SELECT *");
    sql.append("FROM article");
    sql.append("WHERE id = ?", id);

    return new Article(DBUtil.selectRow(conn, sql));
  }

  public void delete(int id) {
    SecSql sql = new SecSql();
    sql.append("DELETE");
    sql.append("FROM article");
    sql.append("WHERE id = ?", id);

    DBUtil.delete(conn, sql);
  }
}