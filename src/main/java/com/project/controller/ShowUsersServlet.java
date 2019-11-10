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

    public ShowUsersServlet() {
        ApplicationContextInjector injector = ApplicationContextInjector.getInstance();
        userService = injector.getUserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("rowCount") != null && req.getParameter("startFrom") != null) {
            Integer rowCount = Integer.valueOf(req.getParameter("rowCount"));
            Integer startFrom = Integer.valueOf(req.getParameter("startFrom"));

            String page = req.getParameter("page");

            if (page != null) {
                if (page.equals("next")) {
                    startFrom += rowCount;
                } else {
                    startFrom -= rowCount;
                }
            }

            List<User> users = userService.showAll(rowCount, startFrom);
            req.setAttribute("users", users);
            req.setAttribute("rowCount", rowCount);
            req.setAttribute("startFrom", startFrom);

            req.getRequestDispatcher("show.jsp").forward(req, resp);
        } else {
            throw new RuntimeException("Something went wrong");
        }

    }
}
