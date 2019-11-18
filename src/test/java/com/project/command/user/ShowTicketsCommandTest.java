package com.project.command.user;

import com.project.domain.payment.Payment;
import com.project.domain.user.User;
import com.project.service.PaymentService;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Collections;

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

    @InjectMocks
    private ShowTicketsCommand showTicketsCommand;

    @Test
    public void executeShouldReturnTicketsPage(){
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(User.builder()
        .withId(1L)
        .build());
        when(paymentService.showByUserId(anyLong())).thenReturn(Collections.emptyList());

        final String actual = showTicketsCommand.execute(request, response);
        String expected = "user-show-tickets.jsp";

        verify(session, times(2)).setAttribute(anyString(), any());
        MatcherAssert.assertThat(actual, Is.is(expected));

    }

}