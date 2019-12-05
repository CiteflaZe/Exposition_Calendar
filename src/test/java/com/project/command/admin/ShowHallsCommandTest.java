package com.project.command.admin;

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

import static com.project.MockData.MOCK_HALL;
import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.Is.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ShowHallsCommandTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HallService hallService;

    @InjectMocks
    private ShowHallsCommand showHallsCommand;

    @Test
    public void executeShouldReturnShowHallsPage() {
        when(hallService.showAll()).thenReturn(singletonList(MOCK_HALL));

        final String actual = showHallsCommand.execute(request, response);
        String expected = "admin-show-halls.jsp";

        verify(request).setAttribute(anyString(), anyList());
        verify(request).setAttribute(anyString(), anyString());
        verify(hallService).showAll();

        assertThat(actual, is(expected));
    }

}
