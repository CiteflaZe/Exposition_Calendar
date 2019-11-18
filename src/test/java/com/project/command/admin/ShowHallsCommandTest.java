package com.project.command.admin;

import com.project.service.HallService;
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

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ShowHallsCommandTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HallService hallService;
    @Mock
    PaginationUtil paginationUtil;

    @InjectMocks
    ShowHallsCommand showHallsCommand;

    @Test
    public void executeShouldReturnShowHallsPage() {
        Integer[] res = new Integer[]{1, 2, 3};
        when(request.getParameter(anyString())).thenReturn("1");
        when(paginationUtil.checkPagination(anyString(), anyString(), anyInt())).thenReturn(res);

        final String actual = showHallsCommand.execute(request, response);
        String expected = "admin-show-halls.jsp";

        verify(request, times(2)).getParameter(anyString());
        verify(request, times(5)).setAttribute(anyString(), any());
        verify(hallService).showAll(anyInt(), anyInt());

        MatcherAssert.assertThat(actual, Is.is(expected));
    }

}