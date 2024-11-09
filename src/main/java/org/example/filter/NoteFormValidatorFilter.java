package org.example.filter;


import org.example.entity.User;
import org.example.entity.dto.NoteForm;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "NoteFormValidatorFilter")
public class NoteFormValidatorFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        req.setCharacterEncoding("UTF-8");
        Object o = session.getAttribute("user");
        NoteForm noteForm = new NoteForm();
        String userName = ((User) o).getUserName();
        String noteIDString = req.getParameter("noteID");
        noteIDString = (noteIDString == null) ? "0" : noteIDString;
        int noteID = Integer.parseInt(noteIDString);
        noteForm.setNoteID(noteID);
        noteForm.setAuthor(userName);
        noteForm.setNoteTitle(req.getParameter("noteTitle"));
        noteForm.setNoteContent(req.getParameter("noteContent"));
        noteForm.setCategoryName(req.getParameter("categoryName"));
        noteForm.setTagNames(req.getParameter("tagNames"));
        if (noteForm.validate().size() > 0) {
            session.setAttribute("flashMsgs", noteForm.validate());
            session.setAttribute("noteForm", noteForm);
            resp.sendRedirect(req.getHeader("referer"));
        } else {
            req.setAttribute("noteForm", noteForm);
            chain.doFilter(req, resp);
        }
    }
}
