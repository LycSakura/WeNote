package org.example.controller;


import org.example.entity.dto.NoteForm;
import org.example.service.NoteService;
import org.example.service.TagService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SaveNoteServlet", value = "/author/SaveNoteServlet")
public class SaveNoteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        NoteForm noteForm = (NoteForm) request.getAttribute("noteForm");
        NoteService noteService = new NoteService();
        int noteID = noteService.saveNote(noteForm);
        TagService tagService = new TagService();
        tagService.saveTag(noteForm, noteID);
        List<String> msgs = new ArrayList<>();
        msgs.add("笔记及笔记标签添加成功");
        request.getSession().setAttribute("flashMsgs", msgs);
        response.sendRedirect(request.getContextPath() + "/FetchAllNoteServlet");
    }
}
