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

@WebServlet(name = "ChangePasswordServlet", value = "/user/ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserForm userForm = (UserForm) request.getAttribute("userForm");
        UserService userService = new UserService();
        User user = (User) request.getSession().getAttribute("user");
        String userName = user.getUserName();
        List<String> msgs = new ArrayList<>();
        String contextPath = request.getContextPath();
        int count = 0;
        count = userService.changePassword(userName, userForm.getTwiceMD5PasswordOfOldPassword(), userForm.getTwiceMD5Password());
        if (count > 0) {
            request.getSession().invalidate();
            msgs.add("密码修改成功，请重新登录！");
            Cookie cookiePassword = new Cookie("password", "");
            cookiePassword.setMaxAge(0);
            response.addCookie(cookiePassword);
            request.getSession().setAttribute("flashMsgs", msgs);
            response.sendRedirect(contextPath + "/IndexServlet");
        } else {
            msgs.add("密码修改失败，请重新尝试！");
            request.getSession().setAttribute("flashMsgs", msgs);
            response.sendRedirect(request.getHeader("referer"));
        }
    }
}
