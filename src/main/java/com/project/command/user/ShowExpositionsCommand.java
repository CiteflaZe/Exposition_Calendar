package com.project.command.user;

import com.project.command.Command;
import com.project.domain.exposition.Exposition;
import com.project.service.ExpositionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


public class ShowExpositionsCommand implements Command {
    private final ExpositionService expositionService;

    public ShowExpositionsCommand(ExpositionService expositionService){
        this.expositionService = expositionService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        final List<Exposition> expositions = expositionService.showAll();

        request.getSession().setAttribute("expositions", expositions);

        return "show-expositions.jsp";
    }
}
