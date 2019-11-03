package com.project.dao.impl;

import com.project.Exception.DataBaseRuntimeException;
import com.project.dao.DBConnector;
import com.project.dao.TicketDao;
import com.project.domain.ticket.Ticket;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TicketDaoImpl extends AbstractDaoImpl<Ticket> implements TicketDao {
    private static final String SAVE_QUERY = "INSERT INTO tickets(expiration_date, exposition_id, exposition_hall_id, user_id) VALUES (?,?,?,?)";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM tickets WHERE id = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM tickets";
    private static final String UPDATE_QUERY = "UPDATE tickets SET expiration_date = ?, exposition_id = ?, exposition_hall_id = ?, user_id = ? WHERE id = ?";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM tickets WHERE id = ?";

    private static final String FIND_BY_EXPIRATION_DATE_RANGE = "SELECT * FROM tickets WHERE expiration_date > ? AND expiration_date < ?";
    private static final String FIND_BY_USER_ID = "SELECT * FROM tickets WHERE user_id = ?";
    private static final String FIND_BY_EXPOSITION_ID = "SELECT * FROM tickets WHERE exposition_id = ?";

    public TicketDaoImpl(DBConnector connector) {
        super(connector, SAVE_QUERY, FIND_BY_ID_QUERY, FIND_ALL_QUERY, UPDATE_QUERY, DELETE_BY_ID_QUERY);
    }

    @Override
    public List<Ticket> findByExpirationDateRange(Date from, Date to) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_EXPIRATION_DATE_RANGE)) {

            preparedStatement.setDate(1, from);
            preparedStatement.setDate(2, to);
            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                List<Ticket> entities = new ArrayList<>();
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
    public List<Ticket> findByUserId(Long id) {
        return findListByLongParam(id, FIND_BY_USER_ID);
    }

    @Override
    public List<Ticket> findByExpositionId(Long id) {
        return findListByLongParam(id, FIND_BY_EXPOSITION_ID);
    }

    @Override
    protected void insertStatementMapper(PreparedStatement preparedStatement, Ticket entity) throws SQLException {

    }

    @Override
    protected void updateStatementMapper(PreparedStatement preparedStatement, Ticket entity) throws SQLException {

    }

    @Override
    protected Optional<Ticket> mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return Optional.empty();
    }
}
