package com.project.command.admin;

import com.project.command.Command;
import com.project.domain.exposition.Exposition;
import com.project.domain.hall.Hall;
import com.project.service.ExpositionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.time.LocalDate;

public class AddExpositionCommand implements Command {

    private final ExpositionService expositionService;

    public AddExpositionCommand(ExpositionService expositionService) {
        this.expositionService = expositionService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        final String title = request.getParameter("title");
        final String theme = request.getParameter("theme");
        final LocalDate dateStart = LocalDate.parse(request.getParameter("dateStart"));
        final LocalDate dateEnd = LocalDate.parse(request.getParameter("dateEnd"));
        final Double ticketPrice = Double.valueOf(request.getParameter("ticketPrice"));
        final String description = request.getParameter("description");
        final Long hallId = Long.parseLong(request.getParameter("hallId"));

        Exposition exposition = Exposition.builder()
                .withTitle(title)
                .withTheme(theme)
                .withStartTime(dateStart)
                .withFinishTime(dateEnd)
                .withTicketPrice(BigDecimal.valueOf(ticketPrice))
                .withDescription(description)
                .withHall(Hall.builder()
                        .withId(hallId)
                        .build())
                .build();

        expositionService.add(exposition);

        return "admin";

    }
}
