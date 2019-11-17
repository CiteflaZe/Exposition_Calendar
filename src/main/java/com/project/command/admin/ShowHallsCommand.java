package com.project.command.admin;

import com.project.command.Command;
import com.project.domain.hall.Hall;
import com.project.service.HallService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowHallsCommand implements Command {

    private final HallService hallService;

    public ShowHallsCommand(HallService hallService) {
        this.hallService = hallService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        final Integer currentPage = Integer.valueOf(request.getParameter("currentPage"));
        final Integer rowCount = Integer.valueOf(request.getParameter("rowCount"));
        final Integer entriesAmount = hallService.showEntriesAmount();
        final List<Hall> halls = hallService.showAll(0, entriesAmount);

        final Integer numberOfPages = ((Double) Math.ceil(entriesAmount*1.0/rowCount)).intValue();

        request.setAttribute("halls", halls);
        request.setAttribute("command", "showHalls");
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("rowCount", rowCount);
        request.getSession().setAttribute("numberOfPages", numberOfPages);

        return "admin-show-halls.jsp";
    }
}
