package com.project.command.user;

import com.project.domain.Payment;
import com.project.domain.Ticket;
import com.project.service.PaymentService;
import com.project.service.TicketService;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.project.MockData.*;
import static org.hamcrest.MatcherAssert.assertThat;
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
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("date")).thenReturn("2019-07-13");
        when(session.getAttribute("tickets")).thenReturn(MOCK_PAYMENT.getTicketAmount().toString());
        when(session.getAttribute("user")).thenReturn(MOCK_USER);
        when(session.getAttribute("exposition")).thenReturn(MOCK_EXPOSITION);
        when(paymentService.showLastByUserId(anyLong())).thenReturn(MOCK_PAYMENT);

        final String actual = makePayment.execute(request, response);
        String expected = "user";

        verify(session, times(4)).getAttribute(anyString());
        verify(paymentService).add(any(Payment.class));
        verify(ticketService, times(MOCK_PAYMENT.getTicketAmount())).add(any(Ticket.class));
        verify(session, times(3)).removeAttribute(anyString());
        assertThat(actual, Is.is(expected));
    }

}