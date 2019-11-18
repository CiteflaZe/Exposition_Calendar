package com.project.command.user;

import com.project.domain.exposition.Exposition;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Collections;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProcessExpositionCommandTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;

    @InjectMocks
    private ProcessExpositionCommand processExpositionCommand;

    @Test
    public void executeShouldReturnChoseDatePage() {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("expositions")).thenReturn(Collections.singletonList(Exposition.builder()
                .withId(4L)
                .build()));
        when(request.getParameter("exposition")).thenReturn("0");


        final String actual = processExpositionCommand.execute(request, response);
        String expected = "user-choose-date.jsp";

        MatcherAssert.assertThat(actual, Is.is(expected));
    }
}