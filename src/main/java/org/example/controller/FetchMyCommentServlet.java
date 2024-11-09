package org.example.controller;

import org.example.entity.Comment;
import org.example.entity.User;
import org.example.service.CommentService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "FetchMyCommentServlet", value = "/user/FetchMyCommentServlet")
public class FetchMyCommentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CommentService commentService = new CommentService();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String userName = user.getUserName();
        List<Comment> commentList = commentService.fetchCommentListByUserName(userName);
        request.setAttribute("commentList", commentList);
        request.getRequestDispatcher("/IndexServlet?url=user/myCommentList.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
