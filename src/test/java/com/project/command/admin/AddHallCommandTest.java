package com.project.command.admin;

import com.project.domain.hall.Hall;
import com.project.service.HallService;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AddHallCommandTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    HallService hallService;

    @InjectMocks
    AddHallCommand addHallCommand;

    @Test
    public void executeShouldReturnString(){
        when(request.getParameter("houseNumber")).thenReturn("8");

        final String actual = addHallCommand.execute(request, response);
        String expected = "admin";

        verify(request, times(4)).getParameter(anyString());
        verify(hallService).add(any(Hall.class));
        MatcherAssert.assertThat(actual, Is.is(expected));
    }

}