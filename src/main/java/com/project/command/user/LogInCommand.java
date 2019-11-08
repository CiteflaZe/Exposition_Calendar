package com.project.command.user;

import com.project.command.Command;
import com.project.domain.user.User;
import com.project.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class LogInCommand implements Command {

    private final UserService userService;

    public LogInCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        final String email = request.getParameter("email");
        final String password = request.getParameter("password");

        User user = userService.login(email, password);

        request.getSession().setAttribute("user", user);
        return "user.jsp";
    }
}
