package org.example.controller;


import org.example.entity.dto.CommentForm;
import org.example.service.CommentService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SaveCommentServlet", value = "/user/SaveCommentServlet")
public class SaveCommentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CommentForm commentForm = (CommentForm) request.getAttribute("commentForm");
        CommentService commentService = new CommentService();
        commentService.saveComment(commentForm);
        List<String> msgs = new ArrayList<>();
        msgs.add("评论成功");
        request.getSession().setAttribute("flashMsgs", msgs);
        response.sendRedirect(request.getContextPath() + "/ReadNoteServlet?noteID=" + commentForm.getNoteID());
    }
}
