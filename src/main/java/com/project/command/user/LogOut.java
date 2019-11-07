package com.project.command.user;

import com.project.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogOut implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "main/login.jsp";
    }
}
