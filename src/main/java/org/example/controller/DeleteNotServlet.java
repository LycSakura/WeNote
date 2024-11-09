package org.example.controller;

import org.example.service.CommentService;
import org.example.service.NoteService;
import org.example.service.TagService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "DeleteNotServlet", value = "/author/DeleteNoteServlet")
public class DeleteNotServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String noteIDString = request.getParameter("noteID");
        noteIDString = noteIDString == null ? "0" : noteIDString;
        int noteID = Integer.parseInt(noteIDString);
        TagService tagService = new TagService();
        NoteService noteService = new NoteService();
        CommentService commentService = new CommentService();
        commentService.deleteCommentByNoteID(noteID);
        tagService.deleteTagByNoteID(noteID);
        noteService.deleteNoteByNoteID(noteID);
        List<String> msgs = new ArrayList<>();
        msgs.add("删除成功");
        request.getSession().setAttribute("flashMsgs", msgs);
        response.sendRedirect(request.getContextPath() + "/FetchAllNoteServlet");
    }
}
