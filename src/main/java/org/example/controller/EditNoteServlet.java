package org.example.controller;


import org.example.entity.Note;
import org.example.service.NoteService;
import org.example.service.TagService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "EditNoteServlet", value = "/author/EditNoteServlet")
public class EditNoteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String noteIDString = request.getParameter("noteID");
        noteIDString = noteIDString == null ? "0" : noteIDString;
        int noteID = Integer.parseInt(noteIDString);
        NoteService noteService = new NoteService();
        TagService tagService = new TagService();
        Note noteForm = noteService.fetchNoteByNoteID(noteID);
        try {
            List<String> allCategoryNameList = noteService.fetchCategoryNameList();
            List<String> tagNameList = tagService.fetchTagNameListByNoteID(noteID);
            List<String> allTagNameList = tagService.fetchTagNameList();
            request.setAttribute("noteForm", noteForm);
            request.setAttribute("allCategoryNameList", allCategoryNameList);
            request.setAttribute("tagNameList", tagNameList);
            request.setAttribute("allTagNameList", allTagNameList);
            request.getRequestDispatcher("/IndexServlet?url=/author/editNote.jsp").forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
