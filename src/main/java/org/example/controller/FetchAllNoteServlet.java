package org.example.controller;


import org.example.service.NoteService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "FetchAllNoteServlet", value = "/FetchAllNoteServlet")
public class FetchAllNoteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        NoteService noteService = new NoteService();
        try {
            request.setAttribute("noteList", noteService.fetchAllNote());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        request.getRequestDispatcher("/IndexServlet?url=noteList.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
