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
        final HttpServletResponse response = (HttpServletResponse) servletResponse;

        final String adminUrl = "/admin";
        final String userUrl = "/user";
        final String signInUrl = "/sign-in.jsp";
        final String registerUrl = "/register.jsp";
        final String indexUrl = "/index.jsp";
        final String homePageUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + "/";

        String command = request.getParameter("command");

        final User user = (User) request.getSession().getAttribute("user");
        final String currentUrl = request.getRequestURL().toString();

        if(!"login".equals(command) && !"logout".equals(command) && !"register".equals(command)){
            if(currentUrl.contains(adminUrl) &&
                    (user == null || user.getRole() != Role.ADMIN)){
                request.getRequestDispatcher("404-error.jsp").forward(servletRequest, servletResponse);
                return;
            }
            if(currentUrl.contains(userUrl) &&
                    (user == null || user.getRole() != Role.USER)){
                request.getRequestDispatcher("404-error.jsp").forward(servletRequest, servletResponse);
                return;
            }
            if(currentUrl.equals(homePageUrl) ||
                    currentUrl.contains(indexUrl) ||
                    currentUrl.contains(signInUrl) ||
                    currentUrl.contains(registerUrl)){
                if(user != null && user.getRole() == Role.USER){
                    response.sendRedirect("/user");
                    return;
                }
                if(user != null && user.getRole() == Role.ADMIN){
                    response.sendRedirect("/admin");
                    return;
                }
            }
        }

        filterChain.doFilter(request, servletResponse);
    }
}
