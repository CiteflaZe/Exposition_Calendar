package com.project.command.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProcessDateCommandTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @InjectMocks
    private ProcessDateCommand processDateCommand;

    @Test
    public void executeShouldReturnPaymentPage() {
        when(request.getSession()).thenReturn(session);

        final String actual = processDateCommand.execute(request, response);
        String expected = "user-payment-page.jsp";

        verify(session, times(2)).setAttribute(anyString(), any());
        assertThat(actual, is(expected));
    }

}