package com.project.command.user;

import com.project.command.Command;
import com.project.domain.payment.Payment;
import com.project.domain.ticket.Ticket;
import com.project.domain.user.User;
import com.project.entity.payment.Status;
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

        final List<Payment> payments = paymentService.showByUserId(user.getId());

        final List<Ticket> tickets = new ArrayList<>();
        final List<Integer> ticketAmount = new ArrayList<>();

        for (Payment payment:
             payments) {
            if(payment.getStatus() != Status.FAILED){
                final Ticket ticket = ticketService.showByPaymentId(payment.getId()).get(0);
                tickets.add(ticket);
                ticketAmount.add(payment.getTicketAmount());
            }
        }

        request.getSession().setAttribute("tickets", tickets);
        request.getSession().setAttribute("ticketAmount", ticketAmount);

        return "user-show-tickets.jsp";
    }
}
