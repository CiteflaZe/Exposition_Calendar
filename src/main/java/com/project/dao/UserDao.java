package com.project.dao;

import com.project.entity.user.UserEntity;

import java.util.Optional;

public interface UserDao extends CrudDao<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
}
