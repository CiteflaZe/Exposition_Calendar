package com.project.command.user;

import com.project.service.ExpositionService;
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
import java.util.Collections;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ShowExpositionsCommandTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    ExpositionService expositionService;
    @Mock
    private PaginationUtil paginationUtil;

    @InjectMocks
    ShowExpositionsCommand showExpositionsCommand;

    @Test
    public void executeShouldReturnExpositionsPage() {
        Integer[] validPagination = new Integer[]{1, 2, 3};
        when(paginationUtil.checkPagination(any(), any(), any())).thenReturn(validPagination);
        when(expositionService.showAll(anyInt(), anyInt())).thenReturn(Collections.emptyList());

        final String actual = showExpositionsCommand.execute(request, response);
        String expected = "user-show-expositions.jsp";

        verify(request, times(5)).setAttribute(anyString(), any());
        MatcherAssert.assertThat(actual, Is.is(expected));
    }

}