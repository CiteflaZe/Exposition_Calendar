package com.project.command.authentication;

import com.project.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterFormCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return "register.jsp";
    }
}
