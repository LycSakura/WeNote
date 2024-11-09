package org.example.filter;

import org.example.entity.dto.UserForm;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@WebFilter(filterName = "UserFormValidatorFilter")
public class UserFormValidatorFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        req.setCharacterEncoding("UTF-8");
        UserForm userForm = new UserForm();
        userForm.setUserName(req.getParameter("userName"));
        userForm.setOldPassword(req.getParameter("oldPassword"));
        userForm.setNickName(req.getParameter("nickName"));
        userForm.setPassword(req.getParameter("password"));
        userForm.setConfirmPassword(req.getParameter("confirmPassword"));
        userForm.setTelephone(req.getParameter("telephone"));
        userForm.setCheckCodeInput(req.getParameter("checkCodeInput"));
        userForm.setCheckCodeSession((String) session.getAttribute("checkCodeSession"));
        userForm.setBrief(req.getParameter("brief"));
        if (Objects.equals(req.getParameter("autoLogin"), "on")) {
            userForm.setAutoLogin("on");
        } else {
            userForm.setAutoLogin("off");
        }
        if (userForm.validate().size() > 0) {
            session.setAttribute("flashMsgs", userForm.validate());
            session.setAttribute("userForm", userForm);
            resp.sendRedirect(req.getHeader("referer"));
        } else {
            req.setAttribute("userForm", userForm);
            chain.doFilter(req, resp);
        }
    }
}
