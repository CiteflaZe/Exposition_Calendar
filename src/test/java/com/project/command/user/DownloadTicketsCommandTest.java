package com.project.command.user;

import com.project.MockData;
import com.project.exception.DownloadTicketsException;
import com.project.service.TicketService;
import com.project.service.util.PDFCreator;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.Is;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.OngoingStubbing;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DownloadTicketsCommandTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;
    @Mock
    private TicketService ticketService;
    @Mock
    private PDFCreator pdfCreator;

    @InjectMocks
    private DownloadTicketsCommand downloadTicketsCommand;

    @Test
    public void executeShouldThrowDownloadTicketsException(){
        expectedException.expect(DownloadTicketsException.class);
        expectedException.expectMessage("Unable to download file");

        when(request.getParameter("paymentId")).thenReturn("2");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(MockData.MOCK_USER);

        downloadTicketsCommand.execute(request, response);
    }

}