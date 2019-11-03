package com.project.dao.impl;

import com.project.dao.DBConnector;
import com.project.dao.UserDao;
import com.project.entity.user.Role;
import com.project.domain.user.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserDaoImpl extends AbstractDaoImpl<User> implements UserDao {
    private static final String SAVE_QUERY = "INSERT INTO users(email, password, name, surname) VALUES (?,?,?,?)";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM users WHERE id = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM users";
    private static final String UPDATE_QUERY = "UPDATE users SET email = ?, password = ?, name = ?, surname = ? WHERE id = ?";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM users WHERE id = ?";
    private static final String FIND_BY_EMAIL_QUERY = "SELECT * FROM users WHERE email = ?";

    public UserDaoImpl(DBConnector connector) {
        super(connector, SAVE_QUERY, FIND_BY_ID_QUERY, FIND_ALL_QUERY, UPDATE_QUERY, DELETE_BY_ID_QUERY);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return findByStringParam(email, FIND_BY_EMAIL_QUERY);
    }

    @Override
    protected void insertStatementMapper(PreparedStatement preparedStatement, User entity) throws SQLException {
        preparedStatement.setString(1, entity.getEmail());
        preparedStatement.setString(2, entity.getPassword());
        preparedStatement.setString(3, entity.getName());
        preparedStatement.setString(4, entity.getSurname());
    }

    @Override
    protected void updateStatementMapper(PreparedStatement preparedStatement, User entity) throws SQLException {
        insertStatementMapper(preparedStatement, entity);
        preparedStatement.setLong(5, entity.getId());
    }

    @Override
    protected Optional<User> mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return Optional.ofNullable(User.builder()
                .withId(resultSet.getLong("id"))
                .withEmail(resultSet.getString("email"))
                .withPassword(resultSet.getString("password"))
                .withName(resultSet.getString("name"))
                .withSurname(resultSet.getString("surname"))
                .withRole((resultSet.getInt("role_id") == 1) ? Role.ADMIN : Role.USER)
                .build());
    }
}
