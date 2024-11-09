package org.example.controller;

import org.example.dao.NoteDao;
import org.example.service.NoteService;
import org.example.service.TagService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "IndexServlet", value = "/IndexServlet")
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        NoteService noteService = new NoteService();
        TagService tagService = new TagService();
        try {
            request.setAttribute("categoryNameMap", noteService.fetchCategoryNameAndNoteNum());
            request.setAttribute("tagNameMap", tagService.fetchTagNameAndNoteNum());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        request.getRequestDispatcher("/wenote.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
