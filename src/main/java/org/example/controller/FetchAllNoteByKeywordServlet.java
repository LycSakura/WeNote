package org.example.controller;


import org.example.entity.Note;
import org.example.service.NoteService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "FetchAllNoteByKeywordServlet", value = "/FetchAllNoteByKeywordServlet")
public class FetchAllNoteByKeywordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String keyword = request.getParameter("keyword");
        NoteService noteService = new NoteService();
        List<Note> noteListByKeyword = noteService.fetchAllNoteByKeyword(request.getParameter("keyword"));
        request.setAttribute("noteList", noteListByKeyword);
        List<String> msgs = new ArrayList<>();
        msgs.add("本页面是模糊查询页面，模糊查询关键字是:" + keyword);
        request.getSession().setAttribute("flashMsgs", msgs);
        request.getRequestDispatcher("/IndexServlet?url=noteList.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
