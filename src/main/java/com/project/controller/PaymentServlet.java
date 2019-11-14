package com.project.controller;

import com.project.command.Command;
import com.project.command.DefaultCommand;
import com.project.command.user.MakePayment;
import com.project.command.user.ProcessDateCommand;
import com.project.command.user.ProcessExpositionCommand;
import com.project.context.ApplicationContextInjector;
import com.project.domain.exposition.Exposition;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentServlet extends HttpServlet {

    private final Map<String, Command> comamnds;
    private final DefaultCommand defaultCommand = new DefaultCommand();

    public PaymentServlet(){
        final ApplicationContextInjector injector = ApplicationContextInjector.getInstance();
        Map<String, Command> coms = new HashMap<>();
        coms.put("processExposition", new ProcessExpositionCommand());
        coms.put("processDate", new ProcessDateCommand());
        coms.put("makePayment", new MakePayment(injector.getPaymentService(), injector.getTicketService()));
        coms.put("default", defaultCommand);

        comamnds = coms;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        final Exposition pay = (Exposition) req.getSession().getAttribute("pay");
//        req.setAttribute("exposition", pay);
        final String command = req.getParameter("command");

        final String page = comamnds.getOrDefault(command, comamnds.get("default")).execute(req);

        req.getRequestDispatcher(page).forward(req, resp);
    }
}
