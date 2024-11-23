package com.cxzcanying.javaweb;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Authoring Checking--");
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String requestURI = req.getRequestURI();
        boolean isLoginPage = requestURI.endsWith("/index.html");
        boolean isLoginRequest = requestURI.endsWith("/loginServlet") || req.getMethod().equals("POST");

        if (isLoginPage || isLoginRequest) {
            System.out.println("Success,username:"+req.getSession().getAttribute("username"));
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            if (req.getSession().getAttribute("username") == null) {
                System.out.println("Not SignIn");
                resp.sendRedirect("/javaweb/index.html");
            } else {
                System.out.println("Success,username:"+req.getSession().getAttribute("username"));
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }
    }
}