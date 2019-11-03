package com.project;

import com.project.dao.DBConnector;
import com.project.dao.impl.UserDaoImpl;
import com.project.domain.user.User;
import com.project.entity.user.Role;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;

public class ApplicationRunner {
    public static void main(String[] args) {
//        DBConnector DBConnector = new DBConnector("database");
//
//        Connection connection = DBConnector.getConnection();
//        UserDaoImpl userDao = new UserDaoImpl(DBConnector);

        User user = User.builder()
                .withId(4L)
                .withName("John")
                .withSurname("Blevis")
                .withEmail("another@gmail.com")
                .withPassword("3311")
                .withRole(Role.USER)
                .build();

        Timestamp stamp = Timestamp.from(Instant.now());

        LocalDateTime time = LocalDateTime.now();

        System.out.println(stamp);
        System.out.println(time);
    }
}
