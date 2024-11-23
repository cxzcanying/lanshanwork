package com.cxzcanying.javaweb;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
@WebServlet("/successPage")
public class SuccessServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        HttpSession session = req.getSession();//得到存储的Session
        Object username = session.getAttribute("username");//将Session中存储的username拿出来
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.write("<h1>welcome,"+(username != null ? username : "Guest") + "</h1>");
        if (username == null) {
            // 未登录，显示登录链接:
            writer.write("<p><a href=\"/index.html\">Sign In</a></p>");
        } else {
            // 已登录，显示登出链接:
            writer.write("<p><a href=\"/index.html\">Sign Out</a></p>");
        }
        writer.flush();
    }
}
