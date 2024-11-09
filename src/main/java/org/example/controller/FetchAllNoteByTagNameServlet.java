package org.example.controller;


import org.example.dao.TagDao;
import org.example.entity.Note;
import org.example.service.NoteService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "FetchAllNoteByTagNameServlet", value = "/FetchAllNoteByTagNameServlet")
public class FetchAllNoteByTagNameServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String tagName = request.getParameter("tagName");
        tagName = tagName == null ? "" : tagName;
        NoteService noteService = new NoteService();
        List<Note> noteListByTagName = noteService.fetchAllNoteByTagName(tagName);
        request.setAttribute("noteList",noteListByTagName );
        List<String> msgs = new ArrayList<>();
        msgs.add("按笔记标签名称:" + tagName + ",检索笔记页面");
        request.getSession().setAttribute("flashMsgs", msgs);
        request.getRequestDispatcher("/IndexServlet?url=noteList.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
