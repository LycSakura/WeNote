package org.example.controller;


import javax.imageio.ImageIO;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
/**
 * @description: 生成二维码
 */
@WebServlet(name = "CheckCodeServlet", value = "/CheckCodeServlet")
public class CheckCodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        response.setContentType("image/png");
        Random random = new Random();
        int checkCode = random.nextInt(9000) + 1000;
        session.setAttribute("checkCodeSession", checkCode + "");
        int width = 80,height = 25;
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics pen = image.getGraphics();
        pen.fillRect(0,0,width,height);
        pen.setColor(Color.BLACK);
        pen.setFont(new Font("楷体",Font.BOLD,height-5));
        pen.drawString(checkCode+"",width/5,height-5);
        ImageIO.write(image,"jpeg",response.getOutputStream());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
