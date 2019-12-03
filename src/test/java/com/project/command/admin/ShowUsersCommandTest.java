package com.project.command.admin;

import com.project.service.UserService;
import com.project.service.util.PaginationUtil;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ShowUsersCommandTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private UserService userService;
    @Mock
    PaginationUtil paginationUtil;

    @InjectMocks
    ShowUsersCommand showUsersCommand;

    @Test
    public void executeShouldReturnShowUsersPage() {
        Integer[] res = new Integer[]{1, 2, 3};
        when(request.getParameter(anyString())).thenReturn("1");
        when(paginationUtil.checkPagination(anyString(), anyString(), anyLong())).thenReturn(res);

        final String actual = showUsersCommand.execute(request, response);
        String expected = "admin-show-users.jsp";

        verify(request, times(2)).getParameter(anyString());
        verify(request, times(5)).setAttribute(anyString(), any());
        verify(userService).showAll(anyInt(), anyInt());

        MatcherAssert.assertThat(actual, Is.is(expected));
    }
}