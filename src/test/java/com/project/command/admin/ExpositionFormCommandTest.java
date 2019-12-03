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
import javax.servlet.http.HttpSession;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ExpositionFormCommandTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;
    @Mock
    private HallService hallService;

    @InjectMocks
    private ExpositionFormCommand expositionFormCommand;

    @Test
    public void executeShouldReturnExpositionFormPage() {
        when(request.getSession()).thenReturn(session);

        final String actual = expositionFormCommand.execute(request, response);
        String expected = "admin-add-exposition.jsp";

        verify(hallService).showAll();
        verify(session).setAttribute(anyString(), any(List.class));

        MatcherAssert.assertThat(actual, Is.is(expected));
    }

}