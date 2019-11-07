package com.project.dao.impl;

import com.project.exception.DataBaseRuntimeException;
import com.project.dao.DBConnector;
import com.project.dao.TicketDao;
import com.project.entity.exposition.ExpositionEntity;
import com.project.entity.hall.HallEntity;
import com.project.entity.payment.PaymentEntity;
import com.project.entity.ticket.TicketEntity;
import com.project.entity.user.UserEntity;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TicketDaoImpl extends AbstractDaoImpl<TicketEntity> implements TicketDao {
    private static final String SAVE_QUERY = "INSERT INTO tickets(expiration_date, exposition_id, exposition_hall_id, user_id, payment_id) VALUES (?,?,?,?,?)";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM tickets WHERE id = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM tickets";
    private static final String UPDATE_QUERY = "UPDATE tickets SET expiration_date = ?, exposition_id = ?, exposition_hall_id = ?, user_id = ? WHERE id = ?";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM tickets WHERE id = ?";

    private static final String FIND_BY_EXPIRATION_DATE_RANGE = "SELECT * FROM tickets WHERE expiration_date > ? AND expiration_date < ?";
    private static final String FIND_BY_USER_ID = "SELECT * FROM tickets WHERE user_id = ?";
    private static final String FIND_BY_EXPOSITION_ID = "SELECT * FROM tickets WHERE exposition_id = ?";
    private static final String FIND_BY_HALL_ID = "SELECT * FROM tickets WHERE exposition_hall_id = ?";
    private static final String FIND_BY_PAYMENT_ID = "SELECT * FROM tickets WHERE payment_id = ?";

    public TicketDaoImpl(DBConnector connector) {
        super(connector, SAVE_QUERY, FIND_BY_ID_QUERY, FIND_ALL_QUERY, UPDATE_QUERY, DELETE_BY_ID_QUERY);
    }

    @Override
    public List<TicketEntity> findByExpirationDateRange(LocalDate from, LocalDate to) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_EXPIRATION_DATE_RANGE)) {

            preparedStatement.setDate(1, Date.valueOf(from));
            preparedStatement.setDate(2, Date.valueOf(to));
            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                List<TicketEntity> entities = new ArrayList<>();
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
    public List<TicketEntity> findByUserId(Long id) {
        return findListByLongParam(id, FIND_BY_USER_ID);
    }

    @Override
    public List<TicketEntity> findByExpositionId(Long id) {
        return findListByLongParam(id, FIND_BY_EXPOSITION_ID);
    }

    @Override
    public List<TicketEntity> findByHallId(Long id) {
        return findListByLongParam(id, FIND_BY_HALL_ID);
    }

    @Override
    public List<TicketEntity> findByPaymentId(Long id) {
        return findListByLongParam(id, FIND_BY_PAYMENT_ID);
    }

    @Override
    protected void insertStatementMapper(PreparedStatement preparedStatement, TicketEntity entity) throws SQLException {
        preparedStatement.setDate(1, Date.valueOf(entity.getExpirationDate()));
        preparedStatement.setLong(2, entity.getExposition().getId());
        preparedStatement.setLong(3, entity.getHall().getId());
        preparedStatement.setLong(4, entity.getUser().getId());
        preparedStatement.setLong(5, entity.getPayment().getId());
    }

    @Override
    protected void updateStatementMapper(PreparedStatement preparedStatement, TicketEntity entity) throws SQLException {
        insertStatementMapper(preparedStatement, entity);
        preparedStatement.setLong(6, entity.getId());
    }

    @Override
    protected Optional<TicketEntity> mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        ExpositionEntity exposition = ExpositionEntity.builder()
                .withId(resultSet.getLong("exposition_id"))
                .build();
        HallEntity hall = HallEntity.builder()
                .withId(resultSet.getLong("exposition_hall_id"))
                .build();
        UserEntity user = UserEntity.builder()
                .withId(resultSet.getLong("user_id"))
                .build();
        PaymentEntity payment = PaymentEntity.builder()
                .withId(resultSet.getLong("payment_id"))
                .build();

        return Optional.ofNullable(TicketEntity.builder()
                .withId(resultSet.getLong("id"))
                .withExpirationDate(resultSet.getDate("expiration_date").toLocalDate())
                .withExposition(exposition)
                .withHall(hall)
                .withUser(user)
                .withPayment(payment)
                .build());
    }
}
