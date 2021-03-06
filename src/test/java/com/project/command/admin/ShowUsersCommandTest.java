package com.project.command.admin;

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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ShowUsersCommandTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private UserService userService;

    @InjectMocks
    private ShowUsersCommand showUsersCommand;

    @Test
    public void executeShouldReturnShowUsersPage() {
        when(request.getParameter("currentPage")).thenReturn("1");
        when(request.getParameter("rowCount")).thenReturn("15");

        final String actual = showUsersCommand.execute(request, response);
        String expected = "admin-show-users.jsp";

        verify(request, times(2)).getParameter(anyString());
        verify(request, times(3)).setAttribute(anyString(), anyInt());
        verify(request).setAttribute(anyString(), anyList());
        verify(request).setAttribute(anyString(), anyString());
        verify(userService).showAll(anyInt(), anyInt());

        assertThat(actual, is(expected));
    }
}
