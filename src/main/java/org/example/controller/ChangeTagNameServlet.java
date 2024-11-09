package org.example.controller;


import org.example.service.TagService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ChangeTagNameServlet", value = "/author/ChangeTagNameServlet")
public class ChangeTagNameServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oldTagName = request.getParameter("oldTagName");
        String tagName = request.getParameter("tagName");
        List<String> msgs = new ArrayList<>();
        if (tagName != null) {
            if(tagName.trim().length() == 0 || tagName.trim().length() > 50){
                msgs.add("笔记标签不能为空且长度不能超过50位");
                request.getSession().setAttribute("flashMsgs", msgs);
                response.sendRedirect(request.getHeader("referer"));
                return;
            }
        }
        TagService tagService = new TagService();
        tagService.changeTagName(oldTagName, tagName);
        msgs.add("修改成功");
        request.getSession().setAttribute("flashMsgs", msgs);
        response.sendRedirect(request.getContextPath() + "/author/FetchAllTagNameServlet");
    }
}
