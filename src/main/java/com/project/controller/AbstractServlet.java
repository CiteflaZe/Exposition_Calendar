package com.project.controller;

import com.project.command.Command;
import com.project.context.ApplicationContextInjector;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class AbstractServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(AbstractServlet.class);

    private final Map<String, Command> commandNameToCommand;
    private final Command defaultCommand;
    private final List<String> redirectCommands;
    private final List<String> redirectPages;

    public AbstractServlet(String commands) {
        final ApplicationContextInjector injector = ApplicationContextInjector.getInstance();

        switch (commands) {
            case "admin": {
                commandNameToCommand = injector.getAdminCommands();
                break;
            }
            case "user": {
                commandNameToCommand = injector.getUserCommands();
                break;
            }
            case "authentication": {
                commandNameToCommand = injector.getAuthenticationCommands();
                break;
            }
            default: {
                LOGGER.warn("No commands found for this name");
                throw new IllegalArgumentException("No commands found for this name");
            }
        }

        defaultCommand = injector.getDefaultCommand();
        redirectCommands = Arrays.asList("logout", "addExposition", "addHall", "makePayment");
        redirectPages = Arrays.asList("user", "admin", "/");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String command = req.getParameter("command");

        final String page = commandNameToCommand.getOrDefault(command, defaultCommand).execute(req, resp);

        if (redirectCommands.contains(command) || redirectPages.contains(page)) {
            resp.sendRedirect(page);
        } else {
            req.getRequestDispatcher(page).forward(req, resp);
        }
    }
}
