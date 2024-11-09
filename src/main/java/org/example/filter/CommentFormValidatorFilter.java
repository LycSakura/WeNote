package org.example.filter;


import org.example.entity.User;
import org.example.entity.dto.CommentForm;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "CommentFormValidatorFilter")
public class
CommentFormValidatorFilter implements Filter {
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
        Object o = session.getAttribute("user");
        CommentForm commentForm = new CommentForm();
        String userName = ((User) o).getUserName();
        commentForm.setNoteID(Integer.parseInt(req.getParameter("noteID")));
        commentForm.setUserName(userName);
        commentForm.setCommentTitle(req.getParameter("commentTitle"));
        commentForm.setCommentContent(req.getParameter("commentContent"));
        commentForm.setRemoteIP(req.getRemoteAddr());
        if (commentForm.validate().size() > 0) {
            session.setAttribute("flashMsgs", commentForm.validate());
            session.setAttribute("commentForm", commentForm);
            resp.sendRedirect(req.getHeader("referer"));
        } else {
            req.setAttribute("commentForm", commentForm);
            chain.doFilter(req, resp);
        }
    }
}
