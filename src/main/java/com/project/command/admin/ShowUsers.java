package com.project.command.admin;

import com.project.command.Command;
import com.project.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class ShowUsers implements Command {
    private final UserService userService;

    public ShowUsers(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        if((request.getParameter("rowCount") == null || request.getParameter("startFrom") == null)
        || (request.getParameter("rowCount").isEmpty() || request.getParameter("startFrom").isEmpty())){
            throw new IllegalArgumentException("rowCount and (or) startFrom can't be null");
        }
        return "show.jsp";
    }
}
