package org.example.controller; /***
 *@title $NAME
 *@CreateTime 2024/11/9 13:53
 *@description
 **/

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
@MultipartConfig
@WebServlet(name = "UploadNotePhotoServlet", value = "/author/UploadNotePhotoServlet")
public class UploadNotePhotoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String pictureDIR = "/resources/note/";
        String picturePath= request.getServletContext().getRealPath(pictureDIR);
        Part part = request.getPart("upload");
        response.setContentType("text/html;charset=UTF-8");
        if (part != null) {
            String fileContentType = part.getContentType();
            if(fileContentType.contains("image/")){
                String fileName = part.getSubmittedFileName();
                if (fileName != null && !"".equals(fileName)) {
                    String suffix = fileName.substring(fileName.lastIndexOf("."));
                    long time = System.currentTimeMillis();
                    fileName = time + suffix;
                    System.out.println("文件上传到:" + picturePath +  fileName);
                    part.write(picturePath + fileName);
                    part.delete();
                    String contextPath = request.getContextPath();
                    String url = contextPath + "/ShowNotePhotoServlet?fileName=" + fileName + "&" + time;
                    String json = "{\"uploaded\":1,\"url\":\"" + url + "\"}";
                    response.getWriter().append(json);
                }
            }
        }
    }
}
