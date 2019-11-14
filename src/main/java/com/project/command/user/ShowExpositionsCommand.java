package com.project.command.user;

import com.project.command.Command;
import com.project.domain.exposition.Exposition;
import com.project.service.ExpositionService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public class ShowExpositionsCommand implements Command {
    private final ExpositionService expositionService;

    public ShowExpositionsCommand(ExpositionService expositionService){
        this.expositionService = expositionService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        final List<Exposition> expositions = expositionService.showAll();
        System.out.println(expositions);

        request.getSession().setAttribute("expositions", expositions);

        return "show-expositions.jsp";
    }
}
