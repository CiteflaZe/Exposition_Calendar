package com.project.controller;

import com.project.command.Command;
import com.project.context.ApplicationContextInjector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class AdminServlet extends HttpServlet {

    private final Map<String, Command> commandNameToCommand;
    private final Command defaultCommand;

    public AdminServlet() {
        final ApplicationContextInjector injector = ApplicationContextInjector.getInstance();
        this.commandNameToCommand = injector.getAdminCommands();
        this.defaultCommand = injector.getDefaultCommand();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String command = req.getParameter("command");

        final String page = commandNameToCommand.getOrDefault(command, defaultCommand).execute(req, resp);

        if ("logout".equals(command) || "addExposition".equals(command) || "addHall".equals(command)) {
            resp.sendRedirect(page);
        }else {
            req.getRequestDispatcher(page).forward(req, resp);
        }
    }
}
