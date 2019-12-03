package com.project.command.user;

import com.project.command.Command;
import com.project.domain.Exposition;
import com.project.service.ExpositionService;
import com.project.service.util.PaginationUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


public class ShowExpositionsCommand implements Command {
    private final ExpositionService expositionService;
    private final PaginationUtil paginationUtil;

    public ShowExpositionsCommand(ExpositionService expositionService, PaginationUtil paginationUtil){
        this.expositionService = expositionService;
        this.paginationUtil = paginationUtil;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        final String currentPageString = request.getParameter("currentPage");
        final String rowCountString = request.getParameter("rowCount");
        final Long entriesAmount = expositionService.showEntriesAmount();
        final Integer[] validPagination = paginationUtil.checkPagination(currentPageString, rowCountString, entriesAmount);

        final Integer currentPage = validPagination[0];
        final Integer rowCount = validPagination[1];
        final Integer numberOfPages = validPagination[2];
        final Integer startFrom = currentPage*rowCount - rowCount;

        final List<Exposition> expositions = expositionService.showAll(startFrom, rowCount);

        request.getSession().setAttribute("expositions", expositions);
        request.setAttribute("command", "showExpositions");
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("rowCount", rowCount);
        request.setAttribute("numberOfPages", numberOfPages);

        return "user-show-expositions.jsp";
    }
}
