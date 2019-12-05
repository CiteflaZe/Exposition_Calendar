package com.project.command.user;

import com.project.service.ExpositionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.project.MockData.MOCK_EXPOSITION;
import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ShowExpositionsAdminCommandUserCommandTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    ExpositionService expositionService;

    @InjectMocks
    private ShowExpositionsUserCommand showExpositionsUserCommand;

    @Test
    public void executeShouldReturnExpositionsPage() {
        when(request.getParameter("currentPage")).thenReturn("1");
        when(request.getParameter("rowCount")).thenReturn("15");
        when(request.getSession()).thenReturn(session);
        when(expositionService.showAllNotFinished(anyInt(), anyInt())).thenReturn(singletonList(MOCK_EXPOSITION));

        final String actual = showExpositionsUserCommand.execute(request, response);
        String expected = "user-show-expositions.jsp";

        verify(request, times(2)).getParameter(anyString());
        verify(request, times(3)).setAttribute(anyString(), anyInt());
        verify(request).setAttribute(anyString(), anyString());
        verify(session).setAttribute(anyString(), anyList());
        verify(expositionService).showAllNotFinished(anyInt(), anyInt());

        assertThat(actual, is(expected));
    }

}