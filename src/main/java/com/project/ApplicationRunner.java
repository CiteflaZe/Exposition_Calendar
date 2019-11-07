package com.project;

import com.project.dao.DBConnector;
import com.project.dao.impl.UserDaoImpl;
import com.project.domain.user.User;
import com.project.entity.user.Role;
import com.project.service.UserService;
import com.project.service.encoder.PasswordEncoder;
import com.project.service.impl.UserServiceImpl;

import java.sql.Connection;

public class ApplicationRunner {
    public static void main(String[] args) {
        DBConnector DBConnector = new DBConnector("database");

        Connection connection = DBConnector.getConnection();
        UserDaoImpl userDao = new UserDaoImpl(DBConnector);

        User user = User.builder()
                .withId(4L)
                .withName("John")
                .withSurname("Blevis")
                .withEmail("another@gmail.com")
                .withPassword("3311")
                .withRole(Role.USER)
                .build();
//
//        UserService userService = new UserServiceImpl();
//        User login = userService.login("email@gmail.com", "1111");
//
//
//
//        for (int i = 0; i < 50; i++) {
//            userService.register(User.builder()
//                    .withName("Blank")
//                    .withSurname("Surname" + i)
//                    .withEmail("user" + i + "@gmail.com")
//                    .withPassword("pass" + i)
//                    .build());
//        }

    }
}
