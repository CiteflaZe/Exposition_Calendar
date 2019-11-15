package com.project.dao.impl;

import com.project.exception.DataBaseRuntimeException;
import com.project.dao.DBConnector;
import com.project.dao.ExpositionDao;
import com.project.entity.exposition.ExpositionEntity;
import com.project.entity.hall.HallEntity;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExpositionDaoImpl extends AbstractDaoImpl<ExpositionEntity> implements ExpositionDao {
    private static final String SAVE_QUERY = "INSERT INTO expositions(title, theme, start_time, finish_time, ticket_price, description, hall_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM expositions INNER JOIN halls ON expositions.hall_id = halls.id WHERE expositions.id = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM expositions INNER JOIN halls ON expositions.hall_id = halls.id ORDER BY expositions.id DESC LIMIT ?, ?";
    private static final String UPDATE_QUERY = "UPDATE expositions SET title = ?, theme = ?, start_time = ?, finish_time = ?, ticket_price = ?, description = ?, hall_id = ? WHERE id = ?";
    private static final String COUNT_QUERY = "SELECT COUNT(*) AS count FROM expositions";

    private static final String FIND_BY_TITLE_QUERY = "SELECT * FROM expositions WHERE title = ?";
    private static final String FIND_BY_THEME_QUERY = "SELECT * FROM expositions WHERE theme = ?";
    private static final String FIND_BY_PRICE_RANGE_QUERY = "SELECT * FROM expositions WHERE ticket_price > ? AND ticket_price < ?";
    private static final String FIND_BY_DATE_QUERY = "SELECT * FROM expositions WHERE finish_time > ? AND start_time < ?";
    private static final String FIND_BY_HALL_ID = "SELECT * FROM expositions WHERE hall_id = ?";

    public ExpositionDaoImpl(DBConnector connector) {
        super(connector, SAVE_QUERY, FIND_BY_ID_QUERY, FIND_ALL_QUERY, UPDATE_QUERY, COUNT_QUERY);
    }

    @Override
    public Optional<ExpositionEntity> findByTitle(String title) {
        return findByStringParam(title, FIND_BY_TITLE_QUERY);
    }

    @Override
    public List<ExpositionEntity> findByTheme(String theme) {
        return findListByStringParam(theme, FIND_BY_THEME_QUERY);
    }

    @Override
    public List<ExpositionEntity> findByPriceRange(BigDecimal min, BigDecimal max) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_PRICE_RANGE_QUERY)) {

            preparedStatement.setBigDecimal(1, min);
            preparedStatement.setBigDecimal(2, max);
            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                List<ExpositionEntity> entities = new ArrayList<>();
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
    public List<ExpositionEntity> findByDate(LocalDate date) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_DATE_QUERY)) {

            preparedStatement.setDate(1, Date.valueOf(date));
            preparedStatement.setDate(2, Date.valueOf(date));
            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                List<ExpositionEntity> entities = new ArrayList<>();
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
    public List<ExpositionEntity> findByHallId(Long id) {
        return findListByLongParam(id, FIND_BY_HALL_ID);
    }

    @Override
    protected void insertStatementMapper(PreparedStatement preparedStatement, ExpositionEntity entity) throws SQLException {
        preparedStatement.setString(1, entity.getTitle());
        preparedStatement.setString(2, entity.getTheme());
        preparedStatement.setDate(3, Date.valueOf(entity.getStartTime()));
        preparedStatement.setDate(4, Date.valueOf(entity.getFinishTime()));
        preparedStatement.setBigDecimal(5, entity.getTicketPrice());
        preparedStatement.setString(6, entity.getDescription());
        preparedStatement.setLong(7, entity.getHall().getId());
    }

    @Override
    protected void updateStatementMapper(PreparedStatement preparedStatement, ExpositionEntity entity) throws SQLException {
        insertStatementMapper(preparedStatement, entity);
        preparedStatement.setLong(8, entity.getId());
    }

    @Override
    protected Optional<ExpositionEntity> mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        HallEntity hall = HallEntity.builder()
                .withId(resultSet.getLong("hall_id"))
                .withName(resultSet.getString("halls.name"))
                .build();
        return Optional.ofNullable(ExpositionEntity.builder()
                .withId(resultSet.getLong("expositions.id"))
                .withTitle(resultSet.getString("title"))
                .withTheme(resultSet.getString("theme"))
                .withStartTime(resultSet.getDate("start_time").toLocalDate())
                .withFinishTime(resultSet.getDate("finish_time").toLocalDate())
                .withTicketPrice(resultSet.getBigDecimal("ticket_price"))
                .withDescription(resultSet.getString("description"))
                .withHall(hall)
                .build());
    }
}
