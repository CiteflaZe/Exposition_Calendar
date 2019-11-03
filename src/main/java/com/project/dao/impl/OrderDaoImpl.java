package com.project.dao.impl;

import com.project.Exception.DataBaseRuntimeException;
import com.project.dao.DBConnector;
import com.project.dao.OrderDao;
import com.project.domain.payment.Payment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDaoImpl extends AbstractDaoImpl<Payment> implements OrderDao {
    private static final String SAVE_QUERY = "INSERT INTO orders(transaction_time, status, user_id, ticket_id, exposition_id) VALUES (?,?,?,?,?)";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM orders WHERE id = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM orders";
    private static final String UPDATE_QUERY = "UPDATE orders SET transaction_time = ?, status = ?, user_id = ?, ticket_id = ?, exposition_id = ? WHERE id = ?";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM orders WHERE id = ?";

    private static final String FIND_BY_STATUS = "SELECT * FROM orders WHERE status = ?";
    private static final String FIND_BY_TIME_RANGE = "SELECT * FROM orders WHERE transaction_time > ? AND transaction_time < ?";
    private static final String FIND_BY_USER_ID = "SELECT * FROM orders WHERE user_id = ?";
    private static final String FIND_BY_TICKET_ID = "SELECT * FROM orders WHERE ticket_id = ?";
    private static final String FIND_BY_EXPOSITION_ID = "SELECT * FROM orders WHERE exposition_id = ?";

    public OrderDaoImpl(DBConnector connector) {
        super(connector, SAVE_QUERY, FIND_BY_ID_QUERY, FIND_ALL_QUERY, UPDATE_QUERY, DELETE_BY_ID_QUERY);
    }

    @Override
    public List<Payment> findByStatus(String status) {
        return findListByStringParam(status, FIND_BY_STATUS);
    }

    @Override
    public List<Payment> findByTimeRange(Timestamp from, Timestamp to) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_TIME_RANGE)) {

            preparedStatement.setTimestamp(1, from);
            preparedStatement.setTimestamp(2, to);
            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                List<Payment> entities = new ArrayList<>();
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
    public List<Payment> findByUserId(Long id) {
        return findListByLongParam(id, FIND_BY_USER_ID);
    }

    @Override
    public Optional<Payment> findByTicketId(Long id) {
        return findByLongParam(id, FIND_BY_TICKET_ID);
    }

    @Override
    public List<Payment> findByExpositionId(Long id) {
        return findListByLongParam(id, FIND_BY_EXPOSITION_ID);
    }

    @Override
    protected void insertStatementMapper(PreparedStatement preparedStatement, Payment entity) throws SQLException {

    }

    @Override
    protected void updateStatementMapper(PreparedStatement preparedStatement, Payment entity) throws SQLException {

    }

    @Override
    protected Optional<Payment> mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return Optional.empty();
    }
}
