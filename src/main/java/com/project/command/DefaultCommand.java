package com.project.command;

import com.project.domain.user.User;
import com.project.entity.user.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DefaultCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        final User user = (User) request.getSession().getAttribute("user");

        if(user != null){
            if(user.getRole() == Role.USER){
                return "user-page.jsp";
            }else if(user.getRole() == Role.ADMIN){
                return "admin-page.jsp";
            }
        }
        return "index.jsp";
    }
}
