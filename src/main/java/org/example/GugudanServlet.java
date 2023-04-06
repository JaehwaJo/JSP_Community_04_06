package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/gugudan")
public class GugudanServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");
    resp.setCharacterEncoding("UTF-8");
    resp.setContentType("text/html; charset-utf-8");

    int dan = Integer.parseInt(req.getParameter("dan"));
    int limit = Integer.parseInt(req.getParameter("limit"));


    resp.getWriter().append("<h1>%d단</h1>".formatted(dan));

    for (int i = 1; i <= limit; i++) {
      resp.getWriter().append("<div>%d * %d = %d</div>".formatted(dan, i, i * dan));
    }
  }
}
