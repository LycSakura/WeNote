package org.example.controller;

import org.example.service.NoteService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "FetchAllCategoryNameServlet", value = "/author/FetchAllCategoryNameServlet")
public class FetchAllCategoryNameServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        NoteService noteService = new NoteService();
        List<String> categoryNameList = null;
        try {
            categoryNameList = noteService.fetchCategoryNameList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("categoryNameList", categoryNameList);
        request.getRequestDispatcher("/IndexServlet?url=/author/categoryNameList.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
