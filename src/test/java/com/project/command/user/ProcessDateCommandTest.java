package com.project.command.user;

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
        MatcherAssert.assertThat(actual, Is.is(expected));
    }


}