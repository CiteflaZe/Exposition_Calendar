package com.project.dao.impl;

import com.project.exception.DataBaseRuntimeException;
import com.project.dao.DBConnector;
import com.project.dao.PaymentDao;
import com.project.entity.exposition.ExpositionEntity;
import com.project.entity.payment.PaymentEntity;
import com.project.entity.payment.Status;
import com.project.entity.user.UserEntity;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PaymentDaoImpl extends AbstractDaoImpl<PaymentEntity> implements PaymentDao {
    private static final String SAVE_QUERY = "INSERT INTO payments(transaction_time, status, tickets_amount, price, user_id, exposition_id) VALUES (?,?,?,?,?,?)";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM payments WHERE id = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM payments LIMIT ? OFFSET ?";
    private static final String UPDATE_QUERY = "UPDATE payments SET transaction_time = ?, status = ?, tickets_amount = ?, price = ?, user_id = ?, exposition_id = ? WHERE id = ?";
    private static final String COUNT_QUERY = "SELECT COUNT(*) AS count FROM payments";

    private static final String FIND_BY_STATUS = "SELECT * FROM payments WHERE status = ?";
    private static final String FIND_BY_TIME_RANGE = "SELECT * FROM payments WHERE transaction_time > ? AND transaction_time < ?";
    private static final String FIND_BY_USER_ID = "SELECT * FROM payments WHERE user_id = ?";
    private static final String FIND_BY_EXPOSITION_ID = "SELECT * FROM payments WHERE exposition_id = ?";

    public PaymentDaoImpl(DBConnector connector) {
        super(connector, SAVE_QUERY, FIND_BY_ID_QUERY, FIND_ALL_QUERY, UPDATE_QUERY, COUNT_QUERY);
    }

    @Override
    public List<PaymentEntity> findByStatus(Status status) {
        return findListByStringParam(status.getDescription(), FIND_BY_STATUS);
    }

    @Override
    public List<PaymentEntity> findByTimeRange(LocalDateTime from, LocalDateTime to) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_TIME_RANGE)) {

            preparedStatement.setTimestamp(1, Timestamp.valueOf(from));
            preparedStatement.setTimestamp(2, Timestamp.valueOf(to));
            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                List<PaymentEntity> entities = new ArrayList<>();
                while (resultSet.next()) {
                    mapResultSetToEntity(resultSet).ifPresent(entities::add);
                }
                return entities;
            }
        } catch (SQLException e) {
            throw new DataBaseRuntimeException(e);
        }
    }

    @Override
    public List<PaymentEntity> findByUserId(Long id) {
        return findListByLongParam(id, FIND_BY_USER_ID);
    }

    @Override
    public List<PaymentEntity> findByExpositionId(Long id) {
        return findListByLongParam(id, FIND_BY_EXPOSITION_ID);
    }

    @Override
    protected void insertStatementMapper(PreparedStatement preparedStatement, PaymentEntity entity) throws SQLException {
        preparedStatement.setTimestamp(1, Timestamp.valueOf(entity.getPaymentTime()));
        preparedStatement.setString(2, entity.getStatus().getDescription());
        preparedStatement.setInt(3, entity.getTickets().size());
        preparedStatement.setBigDecimal(4, entity.getPrice());
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
//        Status status = resultSet.getString("status").equals("Passed") ? Status.PASSED : Status.FAILED;
        Status status = Status.valueOf(resultSet.getString("status").toUpperCase());
        return Optional.ofNullable(PaymentEntity.builder()
                .withId(resultSet.getLong("id"))
                .withPaymentTime(resultSet.getTimestamp("payment_time").toLocalDateTime())
                .withStatus(status)
                .WithPrice(resultSet.getBigDecimal("price"))
                .withUser(user)
                .withExposition(exposition)
                .build());
    }
}
