package com.project.command.authentication;

import com.project.command.Command;
import com.project.domain.User;
import com.project.entity.Role;
import com.project.exception.InvalidLoginException;
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

        User user;
        try {
            user = userService.login(email, password);
        } catch (InvalidLoginException e) {
            request.setAttribute("loginMessage", "Invalid Email or Password");
            return "login?command=loginForm";
        }

        Role role = user.getRole();

        request.getSession().setAttribute("user", user);
        if (role == Role.ADMIN) {
            return "admin";
        } else {
            return "user";
        }
    }
}
