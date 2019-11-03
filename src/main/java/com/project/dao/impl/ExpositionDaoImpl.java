package com.project.dao.impl;

import com.project.dao.DBConnector;
import com.project.Exception.DataBaseRuntimeException;
import com.project.dao.ExpositionDao;
import com.project.domain.exposition.Exposition;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExpositionDaoImpl extends AbstractDaoImpl<Exposition> implements ExpositionDao {
    private static final String SAVE_QUERY = "INSERT INTO expositions(title, theme, start_time, finish_time, ticket_price, description, hall_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM expositions WHERE id = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM expositions";
    private static final String UPDATE_QUERY = "UPDATE expositions SET title = ?, theme = ?, start_time = ?, finish_time = ?, ticket_price = ?, description = ?, hall_id = ? WHERE id = ?";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM users WHERE id = ?";

    private static final String FIND_BY_TITLE_QUERY = "SELECT * FROM expositions WHERE title = ?";
    private static final String FIND_BY_THEME_QUERY = "SELECT * FROM expositions WHERE theme = ?";
    private static final String FIND_BY_PRICE_RANGE_QUERY = "SELECT * FROM expositions WHERE ticket_price > ? AND ticket_price < ?";
    private static final String FIND_BY_TIME_RANGE_QUERY = "SELECT * FROM expositions WHERE finish_time > ? AND start_time < ?";

    public ExpositionDaoImpl(DBConnector connector) {
        super(connector, SAVE_QUERY, FIND_BY_ID_QUERY, FIND_ALL_QUERY, UPDATE_QUERY, DELETE_BY_ID_QUERY);
    }

    @Override
    public Optional<Exposition> findByTitle(String title) {
        return findByStringParam(title, FIND_BY_TITLE_QUERY);
    }

    @Override
    public List<Exposition> findByTheme(String theme) {
        return findListByStringParam(theme, FIND_BY_THEME_QUERY);
    }

    @Override
    public List<Exposition> findByPriceRange(BigDecimal min, BigDecimal max) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_PRICE_RANGE_QUERY)) {

            preparedStatement.setBigDecimal(1, min);
            preparedStatement.setBigDecimal(2, max);
            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                List<Exposition> entities = new ArrayList<>();
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
    public List<Exposition> findByTimeRange(Timestamp start, Timestamp finish) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_TIME_RANGE_QUERY)) {

            preparedStatement.setTimestamp(1, start);
            preparedStatement.setTimestamp(2, finish);
            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                List<Exposition> entities = new ArrayList<>();
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
    protected void insertStatementMapper(PreparedStatement preparedStatement, Exposition entity) throws SQLException {

    }

    @Override
    protected void updateStatementMapper(PreparedStatement preparedStatement, Exposition entity) throws SQLException {

    }

    @Override
    protected Optional<Exposition> mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return Optional.empty();
    }
}
