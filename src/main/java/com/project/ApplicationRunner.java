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
import java.util.Random;

public class ApplicationRunner {
    public static void main(String[] args) throws IOException, DocumentException {
        DBConnector dbConnector = new DBConnector("database");

        UserService userService = ApplicationContextInjector.getInstance().getUserService();
        ExpositionService expositionService = ApplicationContextInjector.getInstance().getExpositionService();

        ExpositionDao expositionDao = new ExpositionDaoImpl(dbConnector);
        TicketDao ticketDao = new TicketDaoImpl(dbConnector);

        TicketService ticketService = ApplicationContextInjector.getInstance().getTicketService();
        PaymentService paymentService = ApplicationContextInjector.getInstance().getPaymentService();

        Random random = new Random();

        StringBuilder buff = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            buff.append(String.format("%04d", random.ints(0, 9999).findFirst().getAsInt()));
            buff.append("-");
        }

        final String res = buff.substring(0, buff.length()-1);

        System.out.println(res);

        String date = "2019/11/21".replace("/", "-");

        System.out.println(LocalDate.of(1997, 6, 22));
    }
}
