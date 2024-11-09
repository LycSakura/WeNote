package org.example.controller;


import org.example.entity.User;
import org.example.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "RegisterUserServlet", value = "/RegisterUserServlet")
public class RegisterUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserService();
        User user = (User) request.getAttribute("userForm");
        String contextPath = request.getContextPath();
        String userName = user.getUserName();
        List<String> msgs = new ArrayList<>();
        try {
            if (userService.isExists(userName)) {
                msgs.add("用户名已存在");
            } else {
                userService.register(user);
                msgs.add("注册成功");
            }
        } catch (Exception e) {
            msgs.add("注册失败" + e.getMessage());
            throw new RuntimeException(e);
        }
        request.getSession().setAttribute("flashMsgs", msgs);
        response.sendRedirect(contextPath + "/IndexServlet");
    }
}
