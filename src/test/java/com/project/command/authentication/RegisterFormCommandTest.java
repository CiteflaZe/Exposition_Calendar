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
public class RegisterFormCommandTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @InjectMocks
    private RegisterFormCommand registerFormCommand;

    @Test
    public void executeShouldReturnRegisterFormPage() {
        final String actual = registerFormCommand.execute(request, response);
        String expected = "register.jsp";

        assertThat(actual, is(expected));
    }

}