package org.example.controller;


import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet(name = "ShowUserPhotoServlet", value = "/ShowUserPhotoServlet")
public class ShowUserPhotoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fileName = request.getParameter("fileName");
        String path = this.getServletContext().getRealPath("resources/photo/" + fileName);
        File file = new File(path);
        FileInputStream fileInputStream = new FileInputStream(file);
        String mime = request.getServletContext().getMimeType(fileName);
        if (mime == null) {
            mime = "application/octet-stream";
        }
        response.setContentType(mime);
        response.setContentLength((int) file.length());
        ServletOutputStream outputStream = response.getOutputStream();
        byte[] bytes = new byte[1024 * 4];
        int length = 0;
        while ((length = fileInputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, length);
        }
        outputStream.close();
        fileInputStream.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
