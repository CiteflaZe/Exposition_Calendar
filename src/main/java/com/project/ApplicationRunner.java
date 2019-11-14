package com.project;

import com.project.context.ApplicationContextInjector;
import com.project.dao.DBConnector;
import com.project.domain.user.User;
import com.project.service.ExpositionService;
import com.project.service.UserService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

public class ApplicationRunner {
    public static void main(String[] args) {
        DBConnector dbConnector = new DBConnector("database");

        UserService userService = ApplicationContextInjector.getInstance().getUserService();
        ExpositionService expositionService = ApplicationContextInjector.getInstance().getExpositionService();


//        for (int i = 3; i < 150; i++) {
//            userService.register(User.builder()
//                    .withName("Blank")
//                    .withSurname("Surname" + i)
//                    .withEmail("user" + i + "@gmail.com")
//                    .withPassword("user" + i)
//                    .build());
//        }

//        LocalDate date = LocalDate.of(2019, 7, 23);
//        LocalDateTime timeStart = date.atTime(LocalTime.MIN);
//        LocalDateTime timeEnd = date.atTime(LocalTime.MAX);

//        userService.register(User.builder()
//                    .withName("Blank")
//                    .withSurname("Другая фамилия")
//                    .withEmail("userblank12@gmail.com")
//                    .withPassword("user")
//                    .build());

//        System.out.println(date.format("dd/mm/yyyy"));
    }
}
