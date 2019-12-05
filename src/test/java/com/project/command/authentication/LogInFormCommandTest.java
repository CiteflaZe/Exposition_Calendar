package com.project.command.authentication;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(MockitoJUnitRunner.class)
public class LogInFormCommandTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @InjectMocks
    private LogInFormCommand logInFormCommand;

    @Test
    public void executeShouldReturnLogInPagePage() {
        final String actual = logInFormCommand.execute(request, response);
        String expected = "login.jsp";

        assertThat(actual, is(expected));
    }
}
