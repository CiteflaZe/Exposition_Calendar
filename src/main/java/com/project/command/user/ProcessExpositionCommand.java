package com.project.command.user;

import com.project.command.Command;
import com.project.domain.Exposition;
import com.project.service.ExpositionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.time.LocalDate.now;

public class ProcessExpositionCommand implements Command {

    private final ExpositionService expositionService;

    public ProcessExpositionCommand(ExpositionService expositionService) {
        this.expositionService = expositionService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        final long expositionId = Long.parseLong(request.getParameter("expositionId"));

        final Exposition exposition = expositionService.showById(expositionId);

        request.getSession().setAttribute("exposition", exposition);
        request.setAttribute("startDate", now().compareTo(exposition.getStartDate()) >= 0 ?
                now() :
                exposition.getStartDate());

        return "user-choose-date.jsp";
    }
}
