package com.project.command.admin;

import com.project.domain.exposition.Exposition;
import com.project.service.ExpositionService;
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
public class AddExpositionCommandTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private ExpositionService expositionService;

    @InjectMocks
    private AddExpositionCommand addExpositionCommand;

    @Test
    public void executeShouldReturnString(){
        when(request.getParameter("dateStart")).thenReturn("2019-11-11");
        when(request.getParameter("dateEnd")).thenReturn("2019-11-11");
        when(request.getParameter("ticketPrice")).thenReturn("20.1");
        when(request.getParameter("hallId")).thenReturn("5");

        final String actual = addExpositionCommand.execute(request, response);
        String expected = "admin";

        verify(request, times(7)).getParameter(anyString());
        verify(expositionService).add(any(Exposition.class));
        MatcherAssert.assertThat(actual, Is.is(expected));
    }

}