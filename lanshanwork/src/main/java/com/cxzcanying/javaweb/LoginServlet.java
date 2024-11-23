package com.cxzcanying.javaweb;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import static java.lang.System.out;
import static java.lang.Thread.sleep;
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //解决中文乱码问题
        req.setCharacterEncoding("utf-8");//设置请求的编码方式，必须在读取请求参数或者输入之前，否则无效；
        resp.setContentType("text/html");//设置响应的内容类型，这个一定要设置否则，页面中文现实乱码！！！
        resp.setCharacterEncoding("utf-8");//设置响应的编码方式，告诉浏览器我发送的内容编码类型为utf-8;
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        req.setAttribute("login", "login");

        if(username.equals("admin")&&password.equals("password")){
            HttpSession session = req.getSession();
            session.setAttribute("username", username);
            resp.sendRedirect("/javaweb/success.html");
        } else  {
            resp.setContentType("text/html;charset=UTF-8");
            PrintWriter out = resp.getWriter();
            out.println("<html>");
            out.println("<head><title>Login Failed</title></head>");
            out.println("<body>");
            out.println("<h1>登录失败</h1>");
            out.println("<p>用户名或密码错误。</p>");
            out.println("<p><a href='index.html'>返回登录页面</a></p>");
            out.println("</body>");
            out.println("</html>");
//            resp.sendError(HttpServletResponse.SC_NOT_FOUND,"Error");
        }
    }
}
