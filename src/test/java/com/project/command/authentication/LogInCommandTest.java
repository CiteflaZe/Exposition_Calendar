package com.project.command.authentication;

import com.project.domain.user.User;
import com.project.entity.user.Role;
import com.project.exception.InvalidLoginException;
import com.project.service.UserService;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.Is;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.Is.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LogInCommandTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;
    @Mock
    private UserService userService;

    @InjectMocks
    LogInCommand logInCommand;

    @After
    public void resetMocks() {
        reset(request, response, userService);
    }

    @Test
    public void executeShouldReturnLoginPage() {
        when(request.getParameter(anyString())).thenReturn("S");
        when(userService.login(anyString(), anyString())).thenThrow(InvalidLoginException.class);

        final String actual = logInCommand.execute(request, response);
        String expected = "login?command=loginForm";

        assertThat(actual, is(expected));
    }

    @Test
    public void executeShouldReturnAdminPage() {
        when(request.getParameter(anyString())).thenReturn("S");
        when(request.getSession()).thenReturn(session);
        when(userService.login(anyString(), anyString())).thenReturn(User.builder()
                .withRole(Role.ADMIN)
                .build());

        final String actual = logInCommand.execute(request, response);
        String expected = "admin";

        assertThat(actual, is(expected));
    }

    @Test
    public void executeShouldReturnUserPage(){
        when(request.getParameter(anyString())).thenReturn("S");
        when(request.getSession()).thenReturn(session);
        when(userService.login(anyString(), anyString())).thenReturn(User.builder()
                .withRole(Role.USER)
                .build());

        final String actual = logInCommand.execute(request, response);
        String expected = "user";

        assertThat(actual, is(expected));
    }

}