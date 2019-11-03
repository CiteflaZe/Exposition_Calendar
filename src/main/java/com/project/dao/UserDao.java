package com.project.dao;

import com.project.domain.user.User;

import java.util.Optional;

public interface UserDao extends CrudDao<User, Long> {
    Optional<User> findByEmail(String email);
}
