package com.project.command.admin;

import com.project.service.HallService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

import static com.project.MockData.MOCK_HALL;
import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
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
        when(hallService.showAll()).thenReturn(singletonList(MOCK_HALL));

        final String actual = expositionFormCommand.execute(request, response);
        String expected = "admin-add-exposition.jsp";

        verify(hallService).showAll();
        verify(session).setAttribute(anyString(), any(List.class));

        assertThat(actual, is(expected));
    }

}
