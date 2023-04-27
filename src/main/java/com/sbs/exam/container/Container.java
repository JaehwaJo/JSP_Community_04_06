package com.sbs.exam.container;

import com.sbs.exam.controller.ArticleController;
import com.sbs.exam.controller.MemberController;
import com.sbs.exam.repository.ArticleRepository;
import com.sbs.exam.repository.MemberRepository;
import com.sbs.exam.service.ArticleService;
import com.sbs.exam.service.MemberService;

import java.sql.Connection;

public class Container {
  public static ArticleRepository articleRepository;
  public static MemberRepository memberRepository;

  public static ArticleService articleService;
  public static MemberService memberService;

  public static ArticleController articleController;
  public static MemberController memberController;

  public static Connection conn;

  public static void init() {
    articleRepository = new ArticleRepository();
    memberRepository = new MemberRepository();

    articleService = new ArticleService();
    memberService = new MemberService();

    articleController = new ArticleController();
    memberController = new MemberController();
  }
}
