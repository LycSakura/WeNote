package org.example.controller;


import org.example.service.TagService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "FetchAllTagNameServlet", value = "/author/FetchAllTagNameServlet")
public class FetchAllTagNameServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TagService tagService = new TagService();
        List<String> tagNameList = null;
        try {
            tagNameList = tagService.fetchTagNameList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("tagNameList", tagNameList);
        request.getRequestDispatcher("/IndexServlet?url=/author/tagNameList.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
