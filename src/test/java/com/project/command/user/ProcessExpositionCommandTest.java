package com.project.command.user;

import com.project.domain.Exposition;
import com.project.service.ExpositionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;

import static com.project.MockData.MOCK_EXPOSITION;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProcessExpositionCommandTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;
    @Mock
    private ExpositionService expositionService;

    @InjectMocks
    private ProcessExpositionCommand processExpositionCommand;

    @Test
    public void executeShouldReturnChoseDatePage() {
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("expositionId")).thenReturn("1");
        when(expositionService.showById(anyLong())).thenReturn(MOCK_EXPOSITION);

        final String actual = processExpositionCommand.execute(request, response);
        String expected = "user-choose-date.jsp";

        ArgumentCaptor<LocalDate> dateCaptor = ArgumentCaptor.forClass(LocalDate.class);

        verify(session).setAttribute(anyString(), any(Exposition.class));
        verify(request).setAttribute(anyString(), dateCaptor.capture());
        assertThat(dateCaptor.getValue(), not(MOCK_EXPOSITION.getStartDate()));
        assertThat(actual, is(expected));
    }

    @Test
    public void executeShouldReturnChoseDatePageWithExpositionStartDate(){
        Exposition exposition = Exposition.builder()
                .withId(MOCK_EXPOSITION.getId())
                .withTitle(MOCK_EXPOSITION.getTitle())
                .withTheme(MOCK_EXPOSITION.getTheme())
                .withStartDate(LocalDate.now().plusDays(2))
                .withEndDate(MOCK_EXPOSITION.getEndDate())
                .withTicketPrice(MOCK_EXPOSITION.getTicketPrice())
                .withDescription(MOCK_EXPOSITION.getDescription())
                .withHall(MOCK_EXPOSITION.getHall())
                .build();
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("expositionId")).thenReturn("1");
        when(expositionService.showById(anyLong())).thenReturn(exposition);

        final String actual = processExpositionCommand.execute(request, response);
        String expected = "user-choose-date.jsp";

        ArgumentCaptor<LocalDate> dateCaptor = ArgumentCaptor.forClass(LocalDate.class);

        verify(session).setAttribute(anyString(), any(Exposition.class));
        verify(request).setAttribute(anyString(), dateCaptor.capture());
        assertThat(dateCaptor.getValue(), is(exposition.getStartDate()));
        assertThat(actual, is(expected));
    }
}