package com.project;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.project.context.ApplicationContextInjector;
import com.project.dao.DBConnector;
import com.project.dao.ExpositionDao;
import com.project.dao.TicketDao;
import com.project.dao.impl.ExpositionDaoImpl;
import com.project.dao.impl.TicketDaoImpl;
import com.project.domain.exposition.Exposition;
import com.project.domain.hall.Hall;
import com.project.domain.payment.Payment;
import com.project.domain.ticket.Ticket;
import com.project.entity.exposition.ExpositionEntity;
import com.project.entity.ticket.TicketEntity;
import com.project.service.ExpositionService;
import com.project.service.PaymentService;
import com.project.service.TicketService;
import com.project.service.UserService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ApplicationRunner {
    public static void main(String[] args) throws IOException, DocumentException {
        DBConnector dbConnector = new DBConnector("database");

        UserService userService = ApplicationContextInjector.getInstance().getUserService();
        ExpositionService expositionService = ApplicationContextInjector.getInstance().getExpositionService();

        ExpositionDao expositionDao = new ExpositionDaoImpl(dbConnector);
        TicketDao ticketDao = new TicketDaoImpl(dbConnector);

        TicketService ticketService = ApplicationContextInjector.getInstance().getTicketService();
        PaymentService paymentService = ApplicationContextInjector.getInstance().getPaymentService();

        final List<Ticket> list = ticketService.showByPaymentId(6L);
        System.out.println(list);

        final List<Payment> payments = paymentService.showByUserId(5L);
        payments.forEach(System.out::println);


//        Ticket ticket1 = Ticket.builder()
//                .withId(2L)
//                .withValidDate(LocalDate.of(2019, 12, 13))
//                .withPayment(Payment.builder()
//                        .withId(15L)
//                        .withTicketAmount(4)
//                        .build())
//                .withExposition(Exposition.builder()
//                        .withId(1L)
//                        .withTitle("Exposition about some stuff")
//                        .build())
//                .withHall(Hall.builder()
//                        .withName("Big Hall")
//                        .build())
//                .build();
//        Ticket ticket2 = Ticket.builder()
//                .withId(3L)
//                .withValidDate(LocalDate.of(2019, 11, 12))
//                .withPayment(Payment.builder()
//                        .withId(15L)
//                        .withTicketAmount(4)
//                        .build())
//                .withExposition(Exposition.builder()
//                        .withId(1L)
//                        .withTitle("Exposition2")
//                        .build())
//                .withHall(Hall.builder()
//                        .withName("Big Hall2")
//                        .build())
//                .build();
//
//        List<Ticket> tickets = new ArrayList<>();
//        tickets.add(ticket1);
//        tickets.add(ticket2);
//        tickets.add(ticket2);
//
//
//        Document document = new Document();
//        final File file = new File("tickets/ticket.pdf");
//        PdfWriter.getInstance(document, new FileOutputStream(file));
//
//        document.open();
//        Font titleFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 24, BaseColor.BLACK);
//        Font paragraphFont = FontFactory.getFont(FontFactory.TIMES_BOLDITALIC, 16, BaseColor.BLACK);
//        Font separatorFont = FontFactory.getFont(FontFactory.TIMES_BOLD, 16, BaseColor.BLACK);
//        Font chunkFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 16, BaseColor.BLACK);
//
//        Paragraph ticketId = new Paragraph();
//        Paragraph date = new Paragraph();
//        Paragraph exposition = new Paragraph();
//        Paragraph hall = new Paragraph();
//        ticketId.setSpacingAfter(8);
//        date.setSpacingAfter(8);
//        exposition.setSpacingAfter(8);
//        hall.setSpacingAfter(8);
//
//        Chunk chunk;
//        Chunk separator = new Chunk(":", separatorFont);
//        Chunk text;
//
//
//        Paragraph title = new Paragraph("Ticket", titleFont);
//        title.setAlignment(Element.ALIGN_CENTER);
//        title.setSpacingAfter(32);
//
//        for (Ticket ticket:
//             tickets) {
//            chunk = new Chunk("Ticket ID", paragraphFont).setUnderline(1f, -2f);
//            text = new Chunk(" " + ticket.getId(), chunkFont);
//            ticketId.add(chunk);
//            ticketId.add(separator);
//            ticketId.add(text);
//
//            chunk = new Chunk("Date", paragraphFont).setUnderline(1f, -2f);
//            text = new Chunk(" " + ticket.getValidDate(), chunkFont);
//            date.add(chunk);
//            date.add(separator);
//            date.add(text);
//
//            chunk =new Chunk("Expositions", paragraphFont).setUnderline(1f, -2f);
//            text = new Chunk(" " + ticket.getExposition().getTitle(), chunkFont);
//            exposition.add(chunk);
//            exposition.add(separator);
//            exposition.add(text);
//
//            chunk =new Chunk("Hall", paragraphFont).setUnderline(1f, -2f);
//            text = new Chunk(" " + ticket.getHall().getName(), chunkFont);
//            hall.add(chunk);
//            hall.add(separator);
//            hall.add(text);
//
//            document.add(title);
//            document.add(ticketId);
//            document.add(date);
//            document.add(exposition);
//            document.add(hall);
//            document.newPage();
//
//            ticketId.clear();
//            date.clear();
//            exposition.clear();
//            hall.clear();
//        }
//
//
//        document.close();
    }
}
