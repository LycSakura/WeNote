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

@WebServlet(name = "ChangeNoteServlet", value = "/author/ChangeNoteServlet")
public class ChangeNoteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        NoteForm noteForm = (NoteForm) request.getAttribute("noteForm");
        NoteService noteService = new NoteService();
        TagService tagService = new TagService();
        noteService.changeNote(noteForm);
        tagService.deleteTagByNoteID(noteForm.getNoteID());
        tagService.saveTag(noteForm, noteForm.getNoteID());
        List<String> msgs = new ArrayList<>();
        msgs.add("修改成功");
        request.getSession().setAttribute("flashMsgs", msgs);
        response.sendRedirect(request.getContextPath() + "/ReadNoteServlet?noteID=" + noteForm.getNoteID());
    }
}
