package com.project.command.authentication;

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

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LogOutCommandTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;

    @InjectMocks
    LogOutCommand logOutCommand;

    @Test
    public void executeShouldReturnIndexPage(){
        when(request.getSession()).thenReturn(session);

        final String actual = logOutCommand.execute(request, response);
        String expected = "/";

        verify(session).invalidate();
        MatcherAssert.assertThat(actual, Is.is(expected));
    }

}