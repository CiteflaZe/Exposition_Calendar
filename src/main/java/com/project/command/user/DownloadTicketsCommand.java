package com.project.command.user;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.project.command.Command;
import com.project.domain.ticket.Ticket;
import com.project.exception.DownloadTicketsException;
import com.project.exception.PDFCreationException;
import com.project.service.TicketService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class DownloadTicketsCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(DownloadTicketsCommand.class);

    private final TicketService ticketService;

    public DownloadTicketsCommand(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        final Long paymentId = Long.parseLong(req.getParameter("paymentId"));
        final List<Ticket> tickets = ticketService.showByPaymentId(paymentId);
        String filePath = createPDF(paymentId, tickets);
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
            LOGGER.error("Unable to download pdf file", e);
            throw new DownloadTicketsException(e);
        }

        return "user";
    }

    private String createPDF(Long paymentId, List<Ticket> tickets) {
        Document document = new Document();
        final String fileName = "ticket_" + paymentId + ".pdf";
        final File file = new File("tickets/" + fileName);
        try {
            PdfWriter.getInstance(document, new FileOutputStream(file));
        } catch (FileNotFoundException | DocumentException e) {
            LOGGER.warn("Such file does not exist", e);
            throw new PDFCreationException(e);
        }

        document.open();
        Font titleFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 24, BaseColor.BLACK);
        Font paragraphFont = FontFactory.getFont(FontFactory.TIMES_BOLDITALIC, 16, BaseColor.BLACK);
        Font separatorFont = FontFactory.getFont(FontFactory.TIMES_BOLD, 16, BaseColor.BLACK);
        Font chunkFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 16, BaseColor.BLACK);

        Paragraph ticketId = new Paragraph();
        Paragraph date = new Paragraph();
        Paragraph exposition = new Paragraph();
        Paragraph hall = new Paragraph();
        ticketId.setSpacingAfter(8);
        date.setSpacingAfter(8);
        exposition.setSpacingAfter(8);
        hall.setSpacingAfter(8);

        Chunk chunk;
        Chunk separator = new Chunk(":", separatorFont);
        Chunk text;

        Paragraph title = new Paragraph("Ticket", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(32);

        for (Ticket ticket :
                tickets) {
            chunk = new Chunk("Ticket ID", paragraphFont).setUnderline(1f, -2f);
            text = new Chunk(" " + ticket.getId(), chunkFont);
            ticketId.add(chunk);
            ticketId.add(separator);
            ticketId.add(text);

            chunk = new Chunk("Date", paragraphFont).setUnderline(1f, -2f);
            text = new Chunk(" " + ticket.getValidDate(), chunkFont);
            date.add(chunk);
            date.add(separator);
            date.add(text);

            chunk = new Chunk("Expositions", paragraphFont).setUnderline(1f, -2f);
            text = new Chunk(" " + ticket.getExposition().getTitle(), chunkFont);
            exposition.add(chunk);
            exposition.add(separator);
            exposition.add(text);

            chunk = new Chunk("Hall", paragraphFont).setUnderline(1f, -2f);
            text = new Chunk(" " + ticket.getHall().getName(), chunkFont);
            hall.add(chunk);
            hall.add(separator);
            hall.add(text);

            try {
                document.add(title);
                document.add(ticketId);
                document.add(date);
                document.add(exposition);
                document.add(hall);
            } catch (DocumentException e) {
                LOGGER.warn("Unable to write into document", e);
                throw new PDFCreationException(e);
            }
            document.newPage();

            ticketId.clear();
            date.clear();
            exposition.clear();
            hall.clear();
        }

        document.close();

        return fileName;
    }

}
