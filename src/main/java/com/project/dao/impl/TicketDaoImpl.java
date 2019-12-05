package com.project.dao.impl;

import com.project.dao.DBConnector;
import com.project.dao.TicketDao;
import com.project.entity.ExpositionEntity;
import com.project.entity.HallEntity;
import com.project.entity.PaymentEntity;
import com.project.entity.TicketEntity;
import com.project.entity.UserEntity;
import com.project.exception.DataBaseRuntimeException;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TicketDaoImpl extends AbstractDaoImpl<TicketEntity> implements TicketDao {
    private static final String SAVE_QUERY = "INSERT INTO tickets(valid_date, user_id, payment_id, exposition_id) VALUES (?,?,?,?)";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM tickets INNER JOIN expositions ON exposition_id =" +
            " expositions.id INNER JOIN halls ON hall_id = halls.id WHERE tickets.id = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM tickets LIMIT ?, ?";
    private static final String UPDATE_QUERY = "UPDATE tickets SET valid_date = ?, user_id = ?, payment_id = ? exposition_id = ?, WHERE id = ?";
    private static final String COUNT_QUERY = "SELECT COUNT(*) AS count FROM tickets";

    private static final String FIND_FIRST_BY_PAYMENT_ID = "SELECT * FROM tickets INNER JOIN expositions ON exposition_id = expositions.id INNER JOIN halls ON hall_id = halls.id WHERE payment_id = ? LIMIT 1";
    private static final String FIND_BY_PAYMENT_ID_AND_USER_ID = "SELECT * FROM tickets INNER JOIN expositions ON exposition_id = expositions.id INNER JOIN halls ON hall_id = halls.id WHERE payment_id = ? AND user_id = ?";
    private static final String FIND_ALL_BY_USER_ID = "SELECT * FROM tickets INNER JOIN expositions ON exposition_id = expositions.id INNER JOIN halls ON hall_id = halls.id WHERE user_id = ? GROUP BY payment_id ORDER BY payment_id DESC";

    public TicketDaoImpl(DBConnector connector) {
        super(connector, SAVE_QUERY, FIND_BY_ID_QUERY, FIND_ALL_QUERY, UPDATE_QUERY, COUNT_QUERY);
    }

    @Override
    public List<TicketEntity> findByPaymentIdAndUserId(Long paymentId, Long userId) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_PAYMENT_ID_AND_USER_ID)) {

            preparedStatement.setLong(1, paymentId);
            preparedStatement.setLong(2, userId);
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
    public Optional<TicketEntity> findFirstByPaymentId(Long id) {
        return findByLongParam(id, FIND_FIRST_BY_PAYMENT_ID);
    }

    @Override
    public List<TicketEntity> findAllByUserId(Long id) {
        return findListByLongParam(id, FIND_ALL_BY_USER_ID);
    }

    @Override
    protected void insertStatementMapper(PreparedStatement preparedStatement, TicketEntity entity) throws SQLException {
        preparedStatement.setDate(1, Date.valueOf(entity.getValidDate()));
        preparedStatement.setLong(2, entity.getUser().getId());
        preparedStatement.setLong(3, entity.getPayment().getId());
        preparedStatement.setLong(4, entity.getExposition().getId());
    }

    @Override
    protected void updateStatementMapper(PreparedStatement preparedStatement, TicketEntity entity) throws SQLException {
        insertStatementMapper(preparedStatement, entity);
        preparedStatement.setLong(5, entity.getId());
    }

    @Override
    protected Optional<TicketEntity> mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        final HallEntity hall = HallEntity.builder()
                .withId(resultSet.getLong("hall_id"))
                .withName(resultSet.getString("halls.name"))
                .build();

        ExpositionEntity exposition = ExpositionEntity.builder()
                .withId(resultSet.getLong("exposition_id"))
                .withTitle(resultSet.getString("expositions.title"))
                .withHall(hall)
                .build();

        UserEntity user = UserEntity.builder()
                .withId(resultSet.getLong("user_id"))
                .build();

        PaymentEntity payment = PaymentEntity.builder()
                .withId(resultSet.getLong("payment_id"))
                .build();

        return Optional.ofNullable(TicketEntity.builder()
                .withId(resultSet.getLong("id"))
                .withValidDate(resultSet.getDate("valid_date").toLocalDate())
                .withUser(user)
                .withPayment(payment)
                .withExposition(exposition)
                .build());
    }
}
