package com.project.command.user;

import com.project.command.Command;
import com.project.domain.Exposition;
import com.project.domain.Payment;
import com.project.domain.Ticket;
import com.project.domain.User;
import com.project.entity.Status;
import com.project.service.PaymentService;
import com.project.service.TicketService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

public class MakePayment implements Command {
    private final PaymentService paymentService;
    private final TicketService ticketService;

    public MakePayment(PaymentService paymentService, TicketService ticketService) {
        this.paymentService = paymentService;
        this.ticketService = ticketService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        final User user = (User) request.getSession().getAttribute("user");
        final Exposition exposition = (Exposition) request.getSession().getAttribute("exposition");

        String[] stringDate = ((String) request.getSession().getAttribute("date")).split("/");
        int[] intDate = Arrays.stream(stringDate)
                .mapToInt(Integer::parseInt)
                .toArray();
        LocalDate date = LocalDate.of(intDate[0], intDate[1], intDate[2]);

        final Integer ticketsAmount = Integer.valueOf((String) request.getSession().getAttribute("tickets"));

        Payment payment = Payment.builder()
                .withPaymentTime(LocalDateTime.now())
                .withStatus(Status.PASSED)
                .withTicketAmount(ticketsAmount)
                .withPrice(exposition.getTicketPrice().multiply(BigDecimal.valueOf(ticketsAmount)))
                .withUser(user)
                .withExposition(exposition)
                .build();

        paymentService.add(payment);

        final Payment lastPayment = paymentService.showLastByUserId(user.getId());

        for (int i = 0; i < lastPayment.getTicketAmount(); i++) {
            Ticket ticket = Ticket.builder()
                    .withValidDate(date)
                    .withUser(user)
                    .withPayment(lastPayment)
                    .withExposition(exposition)
                    .build();
            ticketService.add(ticket);
        }

        return "user";
    }
}
