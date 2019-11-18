package com.project.command;

import com.project.domain.user.User;
import com.project.entity.user.Role;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultCommandTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;

    private DefaultCommand defaultCommand = new DefaultCommand();

    @After
    public void resetMocks() {
        reset(request, response, session);
    }

    @Test
    public void executeShouldReturnIndexPage() {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(null);

        final String actual = defaultCommand.execute(request, response);
        String expected = "index.jsp";

        assertThat(actual, is(expected));
    }

    @Test
    public void executeShouldReturnUserPage() {
        when(request.getSession()).thenReturn(session);
        final User user = User.builder()
                .withRole(Role.USER)
                .build();
        when(session.getAttribute("user")).thenReturn(user);

        final String actual = defaultCommand.execute(request, response);
        String expected = "user-page.jsp";

        assertThat(actual, is(expected));
    }

    @Test
    public void executeShouldReturnAdminPage() {
        when(request.getSession()).thenReturn(session);
        final User user = User.builder()
                .withRole(Role.ADMIN)
                .build();
        when(session.getAttribute("user")).thenReturn(user);

        final String actual = defaultCommand.execute(request, response);
        String expected = "admin-page.jsp";

        assertThat(actual, is(expected));
    }

}