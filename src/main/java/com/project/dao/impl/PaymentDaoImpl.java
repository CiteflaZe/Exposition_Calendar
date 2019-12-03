package com.project.dao.impl;

import com.project.exception.DataBaseRuntimeException;
import com.project.dao.DBConnector;
import com.project.dao.PaymentDao;
import com.project.entity.ExpositionEntity;
import com.project.entity.PaymentEntity;
import com.project.entity.Status;
import com.project.entity.UserEntity;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PaymentDaoImpl extends AbstractDaoImpl<PaymentEntity> implements PaymentDao {
    private static final String SAVE_QUERY = "INSERT INTO payments(payment_time, price, status, tickets_amount, user_id, exposition_id) VALUES (?,?,?,?,?,?)";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM payments WHERE id = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM payments LIMIT ?, ?";
    private static final String UPDATE_QUERY = "UPDATE payments SET payment_time = ?, price = ?, user_id = ?, status = ?, tickets_amount = ?, exposition_id = ? WHERE id = ?";
    private static final String COUNT_QUERY = "SELECT COUNT(*) AS count FROM payments";

    private static final String FIND_BY_USER_ID = "SELECT * FROM payments WHERE user_id = ? ORDER BY id DESC";
    private static final String FIND_LAST_BY_USER_ID = "SELECT * FROM payments WHERE user_id = ? order by ID DESC LIMIT 1 ";

    public PaymentDaoImpl(DBConnector connector) {
        super(connector, SAVE_QUERY, FIND_BY_ID_QUERY, FIND_ALL_QUERY, UPDATE_QUERY, COUNT_QUERY);
    }

    @Override
    public Optional<PaymentEntity> findLastByUserId(Long id) {
        return findByLongParam(id, FIND_LAST_BY_USER_ID);
    }

    @Override
    public List<PaymentEntity> findAllByUserId(Long id) {
        return findListByLongParam(id, FIND_BY_USER_ID);
    }

    @Override
    protected void insertStatementMapper(PreparedStatement preparedStatement, PaymentEntity entity) throws SQLException {
        preparedStatement.setTimestamp(1, Timestamp.valueOf(entity.getPaymentTime()));
        preparedStatement.setBigDecimal(2, entity.getPrice());
        preparedStatement.setString(3, entity.getStatus().getDescription());
        preparedStatement.setInt(4, entity.getTicketAmount());
        preparedStatement.setLong(5, entity.getUser().getId());
        preparedStatement.setLong(6, entity.getExposition().getId());
    }

    @Override
    protected void updateStatementMapper(PreparedStatement preparedStatement, PaymentEntity entity) throws SQLException {
        insertStatementMapper(preparedStatement, entity);
    }

    @Override
    protected Optional<PaymentEntity> mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        UserEntity user = UserEntity.builder()
                .withId(resultSet.getLong("user_id"))
                .build();

        ExpositionEntity exposition = ExpositionEntity.builder()
                .withId(resultSet.getLong("exposition_id"))
                .build();

        Status status = Status.valueOf(resultSet.getString("status").toUpperCase());

        return Optional.ofNullable(PaymentEntity.builder()
                .withId(resultSet.getLong("id"))
                .withPaymentTime(resultSet.getTimestamp("payment_time").toLocalDateTime())
                .withPrice(resultSet.getBigDecimal("price"))
                .withStatus(status)
                .withTicketAmount(resultSet.getInt("tickets_amount"))
                .withUser(user)
                .withExposition(exposition)
                .build());
    }
}
