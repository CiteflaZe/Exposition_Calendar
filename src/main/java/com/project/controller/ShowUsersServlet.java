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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Integer rowCount = Integer.valueOf((String) req.getSession().getAttribute("rowCount"));
//        Integer startFrom = Integer.valueOf((String) req.getSession().getAttribute("startFrom"));
        if (req.getParameter("rowCount") != null && req.getParameter("startFrom") != null) {
            Integer rowCount = Integer.valueOf(req.getParameter("rowCount"));
            Integer startFrom = Integer.valueOf(req.getParameter("startFrom"));

            String page = req.getParameter("page");

            if (!(rowCount > 0 && startFrom >= 0)) {
                throw new RuntimeException("Invalid values");
            }
            List<User> users = userService.showAll(rowCount, startFrom);
            Long lastId = users.get(users.size() - 1).getId();

            if (page != null) {
                if (page.equals("next")) {
                    if (!(startFrom + rowCount > lastId)) {
                        startFrom += rowCount;
                    }
                } else {
                    if(startFrom - rowCount < 0){
                        startFrom = 0;
                    }else {
                        startFrom -= rowCount;
                    }
                }
            }

            users = userService.showAll(rowCount, startFrom);
            req.setAttribute("users", users);
            req.setAttribute("rowCount", rowCount);
            req.setAttribute("startFrom", startFrom);

            req.getRequestDispatcher("show.jsp").forward(req, resp);
        } else {
            throw new RuntimeException("Something went wrong");
        }
    }
}
