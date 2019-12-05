package com.project.command.user;

import com.project.command.Command;
import com.project.domain.Ticket;
import com.project.domain.User;
import com.project.exception.DownloadTicketsException;
import com.project.service.TicketService;
import com.project.service.helper.PDFCreator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class DownloadTicketsCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(DownloadTicketsCommand.class);
    private final TicketService ticketService;
    private final PDFCreator pdfCreator;

    public DownloadTicketsCommand(TicketService ticketService, PDFCreator pdfCreator) {
        this.ticketService = ticketService;
        this.pdfCreator = pdfCreator;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        final Long userId = ((User) req.getSession().getAttribute("user")).getId();
        final Long paymentId = Long.parseLong(req.getParameter("paymentId"));
        final List<Ticket> tickets = ticketService.showAllByPaymentIdAndUserId(paymentId, userId);
        String filePath = pdfCreator.createPDF(paymentId, tickets);
        File file = new File("tickets/" + filePath);

        try {
            PrintWriter out = resp.getWriter();
            resp.setContentType("APPLICATION/OCTET-STREAM");
            resp.setHeader("Content-Disposition", "attachment; filename=\""
                    + filePath + "\"");

            FileInputStream fileInputStream = new FileInputStream(file);

            int i;
            while ((i = fileInputStream.read()) != -1) {
                out.write(i);
            }
            fileInputStream.close();
            out.close();
            file.delete();
        } catch (IOException e) {
            LOGGER.warn("Unable to download pdf file", e);
            throw new DownloadTicketsException("Unable to download file", e);
        }

        return "user";
    }

}
