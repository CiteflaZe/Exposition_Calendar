package com.project.controller;

import com.project.context.ApplicationContextInjector;
import com.project.domain.user.User;
import com.project.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowUsersServlet extends HttpServlet {

    private final UserService userService;

    public ShowUsersServlet(){
        ApplicationContextInjector injector = ApplicationContextInjector.getInstance();
        userService = injector.getUserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = userService.showAll();
        req.setAttribute("users", users);
        req.setAttribute("listSize", users.size());


        req.getRequestDispatcher("show.jsp").forward(req, resp);
    }
}
