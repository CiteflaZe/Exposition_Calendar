package com.project.command.admin;

import com.project.exception.IllegalPaginationValuesException;
import com.project.service.ExpositionService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.project.MockData.MOCK_EXPOSITION;
import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ShowExpositionsAdminCommandTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private ExpositionService expositionService;

    @InjectMocks
    private ShowExpositionsAdminCommand showExpositionsAdminCommand;

    @Test
    public void executeShouldThrowIllegalPaginationValuesException() {
        expectedException.expect(IllegalPaginationValuesException.class);
        expectedException.expectMessage("Illegal pagination values");

        when(request.getParameter("currentPage")).thenReturn("a");
        when(request.getParameter("rowCount")).thenReturn("a");

        showExpositionsAdminCommand.execute(request, response);
    }

    @Test
    public void executeShouldReturnExpositions() {
        when(request.getParameter("currentPage")).thenReturn("1");
        when(request.getParameter("rowCount")).thenReturn("5");
        when(expositionService.showAll(anyInt(), anyInt())).thenReturn(singletonList(MOCK_EXPOSITION));

        final String actual = showExpositionsAdminCommand.execute(request, response);
        String expected = "admin-show-expositions.jsp";

        verify(request).setAttribute(anyString(), anyList());

        assertThat(actual, is(expected));
    }
}
