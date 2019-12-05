package com.project.command.user;

import com.project.command.Command;
import com.project.domain.Payment;
import com.project.domain.Ticket;
import com.project.domain.User;
import com.project.entity.Status;
import com.project.service.PaymentService;
import com.project.service.TicketService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class ShowTicketsCommand implements Command {

    private final PaymentService paymentService;
    private final TicketService ticketService;

    public ShowTicketsCommand(PaymentService paymentService, TicketService ticketService) {
        this.paymentService = paymentService;
        this.ticketService = ticketService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        final User user = (User) request.getSession().getAttribute("user");

        final List<Payment> payments = paymentService.showAllByUserId(user.getId());
        final List<Ticket> tickets = ticketService.showAllByUserId(user.getId());
        final List<Integer> ticketAmount = new ArrayList<>();

        for (Payment payment : payments) {
            if (payment.getStatus() != Status.FAILED) {
                ticketAmount.add(payment.getTicketAmount());
            }
        }

        request.setAttribute("tickets", tickets);
        request.setAttribute("ticketAmount", ticketAmount);

        return "user-show-tickets.jsp";
    }
}
