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
import javax.servlet.http.HttpSession;
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
        final HttpSession session = request.getSession();

        final User user = (User) session.getAttribute("user");
        final Exposition exposition = (Exposition) session.getAttribute("exposition");
        LocalDate date = LocalDate.parse(session.getAttribute("date").toString());
        final int ticketsAmount = Integer.parseInt((String) session.getAttribute("tickets"));

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

        session.removeAttribute("exposition");
        session.removeAttribute("date");
        session.removeAttribute("tickets");

        return "user";
    }
}
