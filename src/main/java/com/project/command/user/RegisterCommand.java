package com.project.command.user;

import com.project.command.Command;
import com.project.domain.user.User;
import com.project.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class RegisterCommand implements Command {

    private final UserService userService;

    public RegisterCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        final String name = request.getParameter("name");
        final String surname = request.getParameter("surname");
        final String email = request.getParameter("email");
        final String password = request.getParameter("password");
        final String passwordConfirmation = request.getParameter("passwordConfirmation");

        if (!Objects.equals(password, passwordConfirmation)){
            return "register.jsp";
        }

        User user = User.builder()
                .withName(name)
                .withSurname(surname)
                .withEmail(email)
                .withPassword(password)
                .build();

        userService.register(user);

        return "index.jsp";
    }
}
