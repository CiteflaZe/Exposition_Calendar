package com.project.dao.impl;

import com.project.dao.DBConnector;
import com.project.dao.UserDao;
import com.project.entity.Role;
import com.project.entity.User;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class UserDaoImpl extends AbstractCrudDaoImpl<User, Long> implements UserDao {
    private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);

    private static final String FIND_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
    private static final String FIND_USER_BY_EMAIL = "SELECT * FROM users WHERE email = ?";

    public UserDaoImpl(DBConnector connector) {
        super(connector);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        throw new UnsupportedOperationException();
    }

    @Override
    public User create(User entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<User> findById(Long id) {
        return findById(id, FIND_USER_BY_ID);
    }

    @Override
    public List<User> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public User update(User entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public User deleteById(Long aLong) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAllByIds(Set<Long> longs) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected Optional<User> mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return resultSet.next() ?
                Optional.ofNullable(User.builder()
                        .withId(resultSet.getLong("id"))
                        .withEmail(resultSet.getString("email"))
                        .withPassword(resultSet.getString("password"))
                        .withName(resultSet.getString("name"))
                        .withSurname(resultSet.getString("surname"))
                        .withRole(Role.USER)
                        .build())
                : Optional.empty();
    }
}
