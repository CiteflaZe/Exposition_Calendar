package com.project;

import com.project.dao.DBConnector;
import com.project.dao.impl.UserDaoImpl;
import com.project.domain.ticket.Ticket;
import com.project.entity.user.Role;
import com.project.domain.user.User;

public class ApplicationRunner {
    public static void main(String[] args) {
        DBConnector DBConnector = new DBConnector("database");
//
//        Connection connection = DBConnector.getConnection();
        UserDaoImpl userDao = new UserDaoImpl(DBConnector);

        User user = User.builder()
                .withId(4L)
                .withName("John")
                .withSurname("Blevis")
                .withEmail("another@gmail.com")
                .withPassword("3311")
                .withRole(Role.USER)
                .build();


    }
}
