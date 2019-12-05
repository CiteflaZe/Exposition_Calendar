package com.project.command.user;

import com.project.MockData;
import com.project.domain.User;
import com.project.service.PaymentService;
import com.project.service.TicketService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collections;

import static com.project.MockData.*;
import static java.util.Collections.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ShowTicketsCommandTest {

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
    private ShowTicketsCommand showTicketsCommand;

    @Test
    public void executeShouldReturnTicketsPage() {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(MOCK_USER);
        when(paymentService.showAllByUserId(anyLong())).thenReturn(singletonList(MOCK_PAYMENT));
        when(ticketService.showAllByUserId(anyLong())).thenReturn(singletonList(MOCK_TICKET));

        final String actual = showTicketsCommand.execute(request, response);
        String expected = "user-show-tickets.jsp";

        verify(request, times(2)).setAttribute(anyString(), anyList());
        assertThat(actual, is(expected));

    }

}