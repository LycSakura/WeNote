package org.example.controller;

import org.example.service.NoteService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ChangeCategoryNameServlet", value = "/author/ChangeCategoryNameServlet")
public class ChangeCategoryNameServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oldCategoryName = request.getParameter("oldCategoryName");
        String categoryName = request.getParameter("categoryName");
        List<String> msgs = new ArrayList<>();
        if (categoryName != null) {
            if(categoryName.trim().length() == 0 || categoryName.trim().length() > 50){
                msgs.add("笔记类别不能为空且长度不能超过50位");
                request.getSession().setAttribute("flashMsgs", msgs);
                response.sendRedirect(request.getHeader("referer"));
                return;
            }
        }
        NoteService noteService = new NoteService();
        noteService.changeCategoryName(oldCategoryName, categoryName);
        msgs.add("修改成功");
        request.getSession().setAttribute("flashMsgs", msgs);
        response.sendRedirect(request.getContextPath() + "/author/FetchAllCategoryNameServlet");
    }
}
