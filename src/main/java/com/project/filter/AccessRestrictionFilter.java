package com.project.filter;

import com.project.domain.user.User;
import com.project.entity.user.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccessRestrictionFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        String adminUrl = request.getServerName() + ":" + request.getServerPort() + "/admin";
        String userUrl = request.getServerName() + ":" + request.getServerPort() + "/user";
        String homePageUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + "/";
        String indexUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + "/index.jsp";
        String command = request.getParameter("command");
        final User user = (User) request.getSession().getAttribute("user");
//        String page = null;

        if(!"login".equals(command) && !"logout".equals(command) && !"register".equals(command)){
            if(request.getRequestURL().toString().contains(adminUrl) &&
                    (user == null || user.getRole() != Role.ADMIN)){
//                page = "404-error.jsp";
                request.getRequestDispatcher("404-error.jsp").forward(servletRequest, servletResponse);
            }
            if(request.getRequestURL().toString().contains(userUrl) &&
                    (user == null || user.getRole() != Role.USER)){
//                page = "404-error.jsp";
                request.getRequestDispatcher("404-error.jsp").forward(servletRequest, servletResponse);
            }
            if((request.getRequestURL().toString().equals(homePageUrl)||
                    request.getRequestURL().toString().equals(indexUrl)) &&
                    user != null && user.getRole() == Role.USER){
//                page = "/user";
                request.getRequestDispatcher("/user").forward(servletRequest, servletResponse);
            }
            if((request.getRequestURL().toString().equals(homePageUrl)||
                    request.getRequestURL().toString().equals(indexUrl)) &&
                    user != null && user.getRole() == Role.ADMIN){
//                page = "/admin";
                request.getRequestDispatcher("/admin").forward(servletRequest, servletResponse);
            }
        }

//        if(page != null){
//            request.getRequestDispatcher(page).forward(servletRequest, servletResponse);
//        }

        filterChain.doFilter(request, servletResponse);
    }
}
