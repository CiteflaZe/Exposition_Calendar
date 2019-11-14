package com.project.command.user;

import com.project.command.Command;
import com.project.domain.user.User;
import com.project.entity.user.Role;
import com.project.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogInCommand implements Command {

    private final UserService userService;

    public LogInCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        final String email = request.getParameter("email");
        final String password = request.getParameter("password");

        User user = userService.login(email, password);
        Role role = user.getRole();

        request.getSession().setAttribute("user", user);
        if(role == Role.ADMIN){
            return "admin-page.jsp";
        }
        else{
            return "user-page.jsp";
        }
    }
}
