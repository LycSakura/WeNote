package org.example.controller;


import org.example.entity.User;
import org.example.entity.dto.UserForm;
import org.example.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserForm userForm = (UserForm) request.getAttribute("userForm");
        UserService userService = new UserService();
        String userName = userForm.getUserName();
        Cookie[] cookies = request.getCookies();
        String onceMD5Password = "";
        String twiceMD5Password = "";
        boolean autoLogin = false;
        String userNameCookie = "";
        String passwordCookie = "";
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("userName")) {
                userNameCookie = cookie.getValue();
            }
            if (cookie.getName().equals("password")) {
                passwordCookie = cookie.getValue();
            }
            if (userNameCookie.equals(userForm.getUserName()) && passwordCookie.equals(userForm.getPassword())) {
                autoLogin = true;
            }
        }
        if (autoLogin) {
            onceMD5Password = userForm.getPassword();
            twiceMD5Password = userForm.getOnceMD5Password();
        } else {
            onceMD5Password = userForm.getOnceMD5Password();
            twiceMD5Password = userForm.getTwiceMD5Password();
        }
        List<String> msgs = new ArrayList<>();
        User user = userService.login(userName, twiceMD5Password);
        if (user != null) {
            Cookie cookieUserName = new Cookie("userName", userName);
            Cookie cookiePassword = new Cookie("password", onceMD5Password);
            if ("on".equals(userForm.getAutoLogin())) {
                cookieUserName.setPath(request.getContextPath());
                cookiePassword.setPath(request.getContextPath());
                cookieUserName.setMaxAge(30 * 24 * 3600);
                cookiePassword.setMaxAge(30 * 24 * 3600);
            } else {
                cookieUserName.setPath(request.getContextPath());
                cookiePassword.setPath(request.getContextPath());
                cookieUserName.setMaxAge(0);
                cookiePassword.setMaxAge(0);
            }
            response.addCookie(cookieUserName);
            response.addCookie(cookiePassword);
            request.getSession().setAttribute("user", user);
            msgs.add("登录成功!");
        } else {
            Cookie cookiePassword = new Cookie("password", "");
            cookiePassword.setMaxAge(0);
            response.addCookie(cookiePassword);
            Cookie cookieUserName = new Cookie("userName", "");
            cookieUserName.setMaxAge(0);
            response.addCookie(cookieUserName);
            msgs.add("登录失败!");
        }
        request.getSession().setAttribute("flashMsgs", msgs);
        response.sendRedirect(request.getContextPath() + "/IndexServlet");

    }
}
