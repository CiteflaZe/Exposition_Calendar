package com.project.command.admin;

import com.project.command.Command;
import com.project.domain.Hall;
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
        final List<Hall> halls = hallService.showAll();

        request.setAttribute("halls", halls);
        request.setAttribute("command", "showHalls");

        return "admin-show-halls.jsp";
    }
}
