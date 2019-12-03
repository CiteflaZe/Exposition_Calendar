package com.project.command.user;

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
        final String ticketsAmount = "6";

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("date")).thenReturn("2019/7/13");
        when(session.getAttribute("tickets")).thenReturn(ticketsAmount);
        when(session.getAttribute("user")).thenReturn(MOCK_USER);
        when(session.getAttribute("exposition")).thenReturn(MOCK_EXPOSITION);
        when(paymentService.showLastByUserId(anyLong())).thenReturn(MOCK_PAYMENT);

        final String actual = makePayment.execute(request, response);
        String expected = "user";

        verify(session, times(4)).getAttribute(anyString());
        verify(ticketService, times(Integer.parseInt(ticketsAmount))).add(any(Ticket.class));
        assertThat(actual, Is.is(expected));
    }

}