package com.project.command.admin;

import com.project.command.Command;
import com.project.domain.hall.Hall;
import com.project.service.HallService;
import com.project.service.util.PaginationUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowHallsCommand implements Command {

    private final HallService hallService;
    private final PaginationUtil paginationUtil;

    public ShowHallsCommand(HallService hallService, PaginationUtil paginationUtil) {
        this.hallService = hallService;
        this.paginationUtil = paginationUtil;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        final String currentPageString = request.getParameter("currentPage");
        final String rowCountString = request.getParameter("rowCount");
        final Integer entriesAmount = hallService.showEntriesAmount();
        final Integer[] validPagination = paginationUtil.checkPagination(currentPageString, rowCountString, entriesAmount);

        final Integer currentPage = validPagination[0];
        final Integer rowCount = validPagination[1];
        final Integer numberOfPages = validPagination[2];
        final Integer startFrom = currentPage*rowCount - rowCount;

        final List<Hall> halls = hallService.showAll(startFrom, rowCount);

        request.setAttribute("halls", halls);
        request.setAttribute("command", "showHalls");
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("rowCount", rowCount);
        request.setAttribute("numberOfPages", numberOfPages);

        return "admin-show-halls.jsp";
    }
}
