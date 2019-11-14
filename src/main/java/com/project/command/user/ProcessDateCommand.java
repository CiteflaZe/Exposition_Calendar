package com.project.command.user;

import com.project.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProcessDateCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setAttribute("date", request.getParameter("date"));
        request.getSession().setAttribute("tickets", request.getParameter("tickets"));
        System.out.println(request.getParameter("tickets"));
        System.out.println(request.getParameter("date"));
        return "payment-page.jsp";
    }
}
