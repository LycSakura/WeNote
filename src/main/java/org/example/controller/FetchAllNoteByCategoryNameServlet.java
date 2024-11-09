package org.example.controller;


import org.example.entity.Note;
import org.example.service.NoteService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "FetchAllNoteByCategoryNameServlet", value = "/FetchAllNoteByCategoryNameServlet")
public class FetchAllNoteByCategoryNameServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String categoryName = request.getParameter("categoryName");
        categoryName = categoryName == null ? "" : categoryName;
        NoteService noteService = new NoteService();
        List<Note> noteListByCategoryName = noteService.fetchAllNoteByCategoryName(categoryName);
        request.setAttribute("noteList", noteListByCategoryName);
        List<String> msgs = new ArrayList<>();
        msgs.add("按类别名称:" + categoryName + ",检索笔记页面");
        request.getSession().setAttribute("flashMsgs", msgs);
        request.getRequestDispatcher("/IndexServlet?url=noteList.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
