package com.sbs.exam.controller;

import com.sbs.exam.Rq;
import com.sbs.exam.dto.Article;
import com.sbs.exam.dto.ResultData;
import com.sbs.exam.service.ArticleService;
import com.sbs.exam.util.Util;
import jakarta.servlet.http.HttpSession;

import java.sql.Connection;
import java.util.List;

public class ArticleController extends Controller {
  private ArticleService articleService;

  public ArticleController(Connection conn) {
    articleService = new ArticleService(conn);
  }

  @Override
  public void performAction(Rq rq) {
    switch (rq.getActionMethodName()) {
      case "list":
        actionShowList(rq);
        break;
      case "write":
        actionShowWrite(rq);
        break;
      case "doWrite":
        actionDoWrite(rq);
        break;
      case "detail":
        actionDetailList(rq);
        break;
      case "doDelete":
        actionDoDelete(rq);
        break;
      case "modify":
        actionShowModify(rq);
        break;
      case "doModify":
        actionDoModify(rq);
        break;
      default:
        rq.println("존재하지 않는 페이지입니다.");
        break;
    }
  }

  private void actionDoModify(Rq rq) {
    int id = rq.getIntParam("id", 0);
    String title = rq.getParam("title", "");
    String body = rq.getParam("body", "");
    String redirectUri = rq.getParam("redirectUri", Util.f("../article/detail?id=%d", id));

    if (id == 0) {
      rq.historyBack("id를 입력해주세요.");
      return;
    }

    if (title.length() == 0) {
      rq.historyBack("title을 입력해주세요.");
      return;
    }
    if (body.length() == 0) {
      rq.historyBack("body를 입력해주세요.");
      return;
    }

    ResultData modifyRd = articleService.modify(id, title, body);
    rq.replace(modifyRd.getMsg(), redirectUri);
  }

  private void actionShowModify(Rq rq) {
    int id = rq.getIntParam("id", 0);

    if (id == 0) {
      System.out.println("id를 입력해주세요.");
      return;
    }

    Article article = articleService.getForPrintArticleById(id);

    if (article == null) {
      rq.historyBack(Util.f("%d번 게시물이 존재하지 않습니다.", id));
      return;
    }

    rq.setAttr("article", article);
    rq.jsp("../article/modify");
  }

  private void actionDoDelete(Rq rq) {
    int id = rq.getIntParam("id", 0);
    String redirectUri = rq.getParam("redirectUri", "../article/list");

    if(id == 0) {
      rq.historyBack("id를 입력해주세요.");
      return;
    }

    Article article = articleService.getForPrintArticleById(id);

    if(article == null) {
      rq.historyBack(Util.f("%d번 게시물이 존재하지 않습니다.", id));
      return;
    }

    articleService.delete(id);
    rq.replace(Util.f("%d번 게시물을 삭제하였습니다.", id), redirectUri);
  }

  private void actionDetailList(Rq rq) {
    int id = rq.getIntParam("id", 0);

    if (id == 0) {
      rq.historyBack("id를 입력해주세요.");
      return;
    }

    Article article = articleService.getForPrintArticleById(id);

    rq.setAttr("article", article);
    rq.jsp("../article/detail");
  }

  private void actionShowWrite(Rq rq) {
    rq.jsp("../article/write");
  }

  private void actionDoWrite(Rq rq) {
    HttpSession session = rq.getReq().getSession();

    if (session.getAttribute("loginedMemberId") == null) {
      rq.print("<script>alert('로그인 후 이용해주세요.'); location.replace('../member/login');</script>");
      return;
    }

    String title = rq.getParam("title", "");
    String body = rq.getParam("body", "");
    String redirectUri = rq.getParam("redirectUri", "../article/list");

    int loginedMemberId = (int) session.getAttribute("loginedMemberId");

    if (title.length() == 0) {
      rq.historyBack("title을 입력해주세요.");
      return;
    }

    if (body.length() == 0) {
      rq.historyBack("body를 입력해주세요.");
      return;
    }

    ResultData writeRd = articleService.write(title, body, loginedMemberId);
    int id = (int) writeRd.getBody().get("id");
    redirectUri = redirectUri.replace("[NEW_ID]", id + "");

    // rq.printf(writeRd.getMsg());
    rq.replace(writeRd.getMsg(), redirectUri);
  }

  public void actionShowList(Rq rq) {
    int page = rq.getIntParam("page", 1);
    int totalPage = articleService.getForPrintListTotalPage();

    List<Article> articles = articleService.getForPrintArticles(page);

    rq.getReq().setAttribute("articles", articles);
    rq.getReq().setAttribute("page", page);
    rq.getReq().setAttribute("totalPage", totalPage);

    rq.jsp("../article/list");
  }
}
