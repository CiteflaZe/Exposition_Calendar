package com.project.command.admin;

import com.project.command.Command;
import com.project.domain.hall.Hall;
import com.project.service.HallService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ExpositionFormCommand implements Command {

   private final HallService hallService;

    public ExpositionFormCommand(HallService hallService) {
        this.hallService = hallService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        final List<Hall> halls = hallService.showAll(0, hallService.showEntriesAmount());
        halls.sort(Comparator.comparing(Hall::getId));

        request.getSession().setAttribute("halls", halls);

        return "admin-add-exposition.jsp";
    }
}
