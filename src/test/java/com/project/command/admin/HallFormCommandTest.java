package com.project.command.admin;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RunWith(MockitoJUnitRunner.class)
public class HallFormCommandTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;

    @InjectMocks
    HallFormCommand hallFormCommand;

    @Test
    public void executeShouldReturnAddHallPage() {
        final String actual = hallFormCommand.execute(request, response);
        String expected = "admin-add-hall.jsp";

        MatcherAssert.assertThat(actual, Is.is(expected));
    }

}