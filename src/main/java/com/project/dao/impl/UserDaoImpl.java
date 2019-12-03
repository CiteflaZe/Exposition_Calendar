package com.project.dao.impl;

import com.project.dao.DBConnector;
import com.project.dao.UserDao;
import com.project.entity.Role;
import com.project.entity.UserEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserDaoImpl extends AbstractDaoImpl<UserEntity> implements UserDao {
    private static final String SAVE_QUERY = "INSERT INTO users(name, surname, email, password) VALUES (?,?,?,?)";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM users INNER JOIN roles ON role_id = roles.id WHERE users.id = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM users INNER JOIN roles ON role_id = roles.id ORDER BY users.id DESC LIMIT ?, ?";
    private static final String UPDATE_QUERY = "UPDATE users SET name = ?, surname = ?, email = ?, password = ? WHERE id = ?";
    private static final String COUNT_QUERY = "SELECT COUNT(*) AS count FROM users";

    private static final String FIND_BY_EMAIL_QUERY = "SELECT * FROM users INNER JOIN roles ON role_id = roles.id WHERE email = ?";

    public UserDaoImpl(DBConnector connector) {
        super(connector, SAVE_QUERY, FIND_BY_ID_QUERY, FIND_ALL_QUERY, UPDATE_QUERY, COUNT_QUERY);
    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        return findByStringParam(email, FIND_BY_EMAIL_QUERY);
    }

    @Override
    protected void insertStatementMapper(PreparedStatement preparedStatement, UserEntity entity) throws SQLException {
        preparedStatement.setString(1, entity.getName());
        preparedStatement.setString(2, entity.getSurname());
        preparedStatement.setString(3, entity.getEmail());
        preparedStatement.setString(4, entity.getPassword());
    }

    @Override
    protected void updateStatementMapper(PreparedStatement preparedStatement, UserEntity entity) throws SQLException {
        insertStatementMapper(preparedStatement, entity);
        preparedStatement.setLong(5, entity.getId());
    }

    @Override
    protected Optional<UserEntity> mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return Optional.ofNullable(UserEntity.builder()
                .withId(resultSet.getLong("id"))
                .withName(resultSet.getString("name"))
                .withSurname(resultSet.getString("surname"))
                .withEmail(resultSet.getString("email"))
                .withPassword(resultSet.getString("password"))
                .withRole(Role.valueOf(resultSet.getString("role_name").toUpperCase()))
                .build());
    }
}
