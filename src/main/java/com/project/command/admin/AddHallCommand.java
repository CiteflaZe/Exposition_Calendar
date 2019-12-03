package com.project.command.admin;

import com.project.command.Command;
import com.project.domain.Hall;
import com.project.service.HallService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddHallCommand implements Command {

    private final HallService hallService;

    public AddHallCommand(HallService hallService) {
        this.hallService = hallService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        final String name = request.getParameter("name");
        final String city = request.getParameter("city");
        final String street = request.getParameter("street");
        final Integer houseNumber = Integer.parseInt(request.getParameter("houseNumber"));

        Hall hall = Hall.builder()
                .withName(name)
                .withCity(city)
                .withStreet(street)
                .withHouseNumber(houseNumber)
                .build();

        hallService.add(hall);

        return "admin";
    }
}
