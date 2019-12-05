package com.project.command.authentication;

import com.project.domain.User;
import com.project.exception.EmailAlreadyExistException;
import com.project.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RegisterCommandTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private UserService userService;

    @InjectMocks
    private RegisterCommand registerCommand;

    @Test
    public void executeShouldReturnRegisterPageWithUnequalPasswords() {
        when(request.getParameter("password")).thenReturn("pass");

        final String actual = registerCommand.execute(request, response);
        String expected = "register?command=registerForm";

        verify(request, times(5)).getParameter(anyString());
        verify(request).setAttribute("registerMessage", "Passwords should be the same");
        assertThat(actual, is(expected));
    }

    @Test
    public void executeShouldReturnRegisterPageWithExistingEmail() {
        when(request.getParameter(anyString())).thenReturn("pass");
        when(userService.register(any(User.class))).thenThrow(EmailAlreadyExistException.class);

        final String actual = registerCommand.execute(request, response);
        String expected = "register?command=registerForm";

        verify(request, times(5)).getParameter(anyString());
        verify(request).setAttribute("registerMessage", "Email already taken");
        assertThat(actual, is(expected));
    }

    @Test
    public void executeShouldReturnIndexPage() {
        final String actual = registerCommand.execute(request, response);
        String expected = "/";

        verify(request, times(5)).getParameter(anyString());
        assertThat(actual, is(expected));
    }

}