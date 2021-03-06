package com.project.command.admin;

import com.project.domain.Hall;
import com.project.service.HallService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AddHallCommandTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HallService hallService;

    @InjectMocks
    private AddHallCommand addHallCommand;

    @Test
    public void executeShouldReturnString() {
        when(request.getParameter("name")).thenReturn("Some cool hall");
        when(request.getParameter("city")).thenReturn("Kiev");
        when(request.getParameter("street")).thenReturn("Some street");
        when(request.getParameter("houseNumber")).thenReturn("8");

        final String actual = addHallCommand.execute(request, response);
        String expected = "admin";

        verify(request, times(4)).getParameter(anyString());
        verify(hallService).add(any(Hall.class));
        assertThat(actual, is(expected));
    }

}
