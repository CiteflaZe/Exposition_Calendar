package com.project.controller;

import com.project.command.admin.ShowUsers;
import com.project.context.ApplicationContextInjector;
import com.project.service.UserService;
import com.project.service.validator.PaginationValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowUsersServlet extends HttpServlet {

    private final UserService userService;
    private final PaginationValidator paginationValidator;

    public ShowUsersServlet() {
        ApplicationContextInjector injector = ApplicationContextInjector.getInstance();
        userService = injector.getUserService();
        this.paginationValidator = injector.getPaginationValidator();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ShowUsers showUsers = new ShowUsers(userService, paginationValidator);
        String page = null;

        if("show".equals(req.getParameter("command"))){
            page = showUsers.execute(req);
        }


        req.getRequestDispatcher(page).forward(req, resp);

    }
}
