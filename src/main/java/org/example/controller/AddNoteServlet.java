package org.example.controller;


import org.example.service.NoteService;
import org.example.service.TagService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AddNoteServlet", value = "/author/AddNoteServlet")
public class AddNoteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        NoteService noteService =  new NoteService();
        TagService tagService = new TagService();
        List<String> categoryNameList = null;
        List<String> tagNameList = null;
        try {
            categoryNameList = noteService.fetchCategoryNameList();
            tagNameList = tagService.fetchTagNameList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("categoryNameList", categoryNameList);
        request.setAttribute("tagNameList", tagNameList);
        request.getRequestDispatcher("/IndexServlet?url=/author/addNote.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
