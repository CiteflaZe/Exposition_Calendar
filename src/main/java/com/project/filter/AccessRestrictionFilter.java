package com.project.filter;

import com.project.domain.user.User;
import com.project.entity.user.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AccessRestrictionFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        String adminUrl = request.getServerName() + ":" + request.getServerPort() + "/admin";
        String userUrl = request.getServerName() + ":" + request.getServerPort() + "/user";
        final User user = (User) request.getSession().getAttribute("user");

        if(request.getRequestURL().toString().contains(adminUrl) &&
                (user == null || user.getRole() != Role.ADMIN)){
            request.getRequestDispatcher("404-error.jsp").forward(request, servletResponse);
        }
        if(request.getRequestURL().toString().contains(userUrl) &&
                (user == null || user.getRole() != Role.USER)){
            request.getRequestDispatcher("404-error.jsp").forward(request, servletResponse);
        }
        if(user != null && user.getRole() == Role.USER){
            request.getRequestDispatcher("/user").forward(request, servletResponse);
        }
        if(user != null && user.getRole() == Role.ADMIN){
            request.getRequestDispatcher("/admin").forward(request, servletResponse);
        }

        filterChain.doFilter(request, servletResponse);
    }
}
