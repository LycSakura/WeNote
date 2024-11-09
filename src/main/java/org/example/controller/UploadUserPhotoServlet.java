package org.example.controller;

import org.example.entity.User;
import org.example.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@MultipartConfig
@WebServlet(name = "UploadUserPhotoServlet", value = "/user/UploadUserPhotoServlet")
public class UploadUserPhotoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String pictureDIR = "/resources/photo/";
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String userName = user.getUserName();
        String picturePath = request.getServletContext().getRealPath(pictureDIR);
        Part part = request.getPart("upload");
        response.setContentType("text/html;charset=UTF-8");
        if (part != null) {
            String fileName = part.getSubmittedFileName();
            if (fileName != null && !"".equals(fileName)) {
                String suffix = fileName.substring(fileName.lastIndexOf("."));
                long time = System.currentTimeMillis();
                fileName = userName + suffix;
                System.out.println("文件上传到:" + picturePath +  fileName);
                        part.write(picturePath + fileName);
                part.delete();
                String contextPath = request.getContextPath();
                String url = contextPath + "/ShowUserPhotoServlet?fileName=" + fileName + "&" + time;
                // 返回json数据,目的是兼容CKEditor中的图片显示
                String json = "{\"uploaded\":1,\"url\":\"" + url + "\"}";
                user.setPhoto(fileName);
                session.setAttribute("user", user);
                UserService userService = new UserService();
                userService.changeUserPhoto(userName, fileName);
                response.getWriter().append(json);
                System.out.println("返回的JSON数据是" + json);
            }
        }
    }
}
