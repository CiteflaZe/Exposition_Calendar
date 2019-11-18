package com.project.command.user;

import com.project.domain.exposition.Exposition;
import com.project.domain.hall.Hall;
import com.project.domain.payment.Payment;
import com.project.domain.ticket.Ticket;
import com.project.domain.user.User;
import com.project.service.PaymentService;
import com.project.service.TicketService;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MakePaymentTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;
    @Mock
    private PaymentService paymentService;
    @Mock
    private TicketService ticketService;

    @InjectMocks
    private MakePayment makePayment;

    @Test
    public void executeShouldReturnUserPage() {
        final String ticketsAmount = "3";

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("date")).thenReturn("2019/7/13");
        when(session.getAttribute("tickets")).thenReturn(ticketsAmount);
        when(session.getAttribute("user")).thenReturn(User.builder()
                .withId(4L)
                .build());
        when(session.getAttribute("exposition")).thenReturn(Exposition.builder()
                .withTicketPrice(BigDecimal.valueOf(12.4))
                .withHall(Hall.builder()
                        .withId(2L)
                        .build())
                .build());
        when(paymentService.showLastPaymentByUserId(anyLong())).thenReturn(Optional.of(Payment.builder()
                .withId(6L)
                .build()));

        final String actual = makePayment.execute(request, response);
        String expected = "user";

        verify(session, times(4)).getAttribute(anyString());
        verify(ticketService, times(Integer.parseInt(ticketsAmount))).add(any(Ticket.class));
        MatcherAssert.assertThat(actual, Is.is(expected));
    }

}