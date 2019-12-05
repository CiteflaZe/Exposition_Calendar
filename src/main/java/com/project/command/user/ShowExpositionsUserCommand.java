package com.project.command.user;

import com.project.command.Command;
import com.project.domain.Exposition;
import com.project.service.ExpositionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowExpositionsUserCommand implements Command {
    private final ExpositionService expositionService;

    public ShowExpositionsUserCommand(ExpositionService expositionService){
        this.expositionService = expositionService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        final String stringPage = request.getParameter("currentPage");
        final String stringRowCount = request.getParameter("rowCount");

        validatePagination(stringPage, stringRowCount);

        final int page = Integer.parseInt(stringPage);
        final int rowCount = Integer.parseInt(stringRowCount);
        final List<Exposition> expositions = expositionService.showAllNotFinished(page, rowCount);

        paginate(page, rowCount, expositionService.showNotFinishedEntriesAmount(), request, "showExpositions");

        request.getSession().setAttribute("expositions", expositions);

        return "user-show-expositions.jsp";
    }
}
