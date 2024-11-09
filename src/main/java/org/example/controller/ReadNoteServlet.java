package org.example.controller;

import org.example.entity.Comment;
import org.example.entity.Note;
import org.example.entity.User;
import org.example.service.CommentService;
import org.example.service.NoteService;
import org.example.service.TagService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ReadNoteServlet", value = "/ReadNoteServlet")
public class ReadNoteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String noteIDString = request.getParameter("noteID");
        noteIDString = noteIDString == null ? "0" : noteIDString;
        int noteID = Integer.parseInt(noteIDString);
        NoteService noteService = new NoteService();
        TagService tagService = new TagService();
        CommentService commentService = new CommentService();
        Note note = noteService.fetchNoteByNoteID(noteID);
        Note nextNote = noteService.fetchNextOrLastNoteByNoteID(noteID, ">");
        Note lastNote = noteService.fetchNextOrLastNoteByNoteID(noteID, "<");
        List<String> tagNameList = tagService.fetchTagNameListByNoteID(noteID);
        List<Comment> commentList = commentService.fetchCommentListByNoteID(noteID);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String role = user == null ? "N" : user.getIsAuthor();
        if ("N".equals(role)){
            noteService.increaseVisit(noteID);
        }
        request.setAttribute("note", note);
        request.setAttribute("nextNote", nextNote);
        request.setAttribute("lastNote", lastNote);
        request.setAttribute("tagNameList", tagNameList);
        request.setAttribute("commentList", commentList);
        request.getRequestDispatcher("/IndexServlet?url=/readNote.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
