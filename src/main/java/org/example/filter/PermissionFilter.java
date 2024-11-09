package org.example.filter;

import org.example.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebFilter(filterName = "PermissionFilter")
public class PermissionFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        String contextPath = req.getContextPath();
        List<String> mags = new ArrayList<>();
        String requestURI = req.getRequestURI();
        req.setCharacterEncoding("UTF-8");
        String url = req.getParameter("url");
        url = (url == null) ? "" : url;
        Object o = session.getAttribute("user");
        if (requestURI.contains(contextPath + "/user/") || url.contains("/user/")){
            if (o == null){
                mags.add("受保护资源,请先登录!");
                req.getSession().setAttribute("flashMsgs", mags);
                res.sendRedirect(contextPath + "/IndexServlet");
                return;
            }
        }
        if (requestURI.contains(contextPath+"/author/") || url.contains("/author/")){
            if(o == null || !((User)o).getIsAuthor().equals("Y")){
                mags.add("受保护资源,请先登录!");
                req.getSession().setAttribute("flashMsgs", mags);
                res.sendRedirect(contextPath + "/IndexServlet");
                return;
            }
        }
        chain.doFilter(req, res);
    }
}
