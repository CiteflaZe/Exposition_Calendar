package com.project.service;

import com.project.domain.user.User;

import java.util.List;

public interface UserService {
    User login(String email, String password);

    boolean register(User user);

    List<User> showAll();

}
