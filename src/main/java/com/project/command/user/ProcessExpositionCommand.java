package com.project.command.user;

import com.project.command.Command;
import com.project.domain.exposition.Exposition;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ProcessExpositionCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        final List<Exposition> expositions = (List<Exposition>) request.getSession().getAttribute("expositions");
        final Integer expositionId = Integer.parseInt(request.getParameter("exposition"));
        request.getSession().setAttribute("exposition", expositions.get(expositionId - 1));
        return "choose-date.jsp";
    }
}
