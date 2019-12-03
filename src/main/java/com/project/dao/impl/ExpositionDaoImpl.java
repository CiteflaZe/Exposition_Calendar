package com.project.dao.impl;

import com.project.exception.DataBaseRuntimeException;
import com.project.dao.DBConnector;
import com.project.dao.ExpositionDao;
import com.project.entity.ExpositionEntity;
import com.project.entity.HallEntity;
import com.project.service.ExpositionService;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExpositionDaoImpl extends AbstractDaoImpl<ExpositionEntity> implements ExpositionDao {
    private static final String SAVE_QUERY = "INSERT INTO expositions(title, theme, start_date, end_date, ticket_price, description, hall_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM expositions INNER JOIN halls ON expositions.hall_id = halls.id WHERE expositions.id = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM expositions INNER JOIN halls ON expositions.hall_id = halls.id ORDER BY expositions.id DESC LIMIT ?, ?";
    private static final String UPDATE_QUERY = "UPDATE expositions SET title = ?, theme = ?, start_date = ?, end_date = ?, ticket_price = ?, description = ?, hall_id = ? WHERE id = ?";
    private static final String COUNT_QUERY = "SELECT COUNT(*) AS count FROM expositions";

    private static final String COUNT_BY_END_DATE_GREATER_THAN = "SELECT COUNT(*) AS count FROM expositions_calendar.expositions WHERE end_date > now()";
    private static final String FIND_NOT_ENDED = "SELECT * FROM expositions_calendar.expositions INNER JOIN halls ON expositions.hall_id = halls.id WHERE end_date > now() ORDER BY expositions.id DESC LIMIT ?, ?";
    private static final String FIND_BY_TITLE_QUERY = "SELECT * FROM expositions INNER JOIN halls ON expositions.hall_id = halls.id WHERE title = ?";

    public ExpositionDaoImpl(DBConnector connector) {
        super(connector, SAVE_QUERY, FIND_BY_ID_QUERY, FIND_ALL_QUERY, UPDATE_QUERY, COUNT_QUERY);
    }

    @Override
    public Optional<ExpositionEntity> findByTitle(String title) {
        return findByStringParam(title, FIND_BY_TITLE_QUERY);
    }

    @Override
    public List<ExpositionEntity> findAllWhereEndDateGreaterThanNow(Integer startFrom, Integer rowCount) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_NOT_ENDED)) {

            preparedStatement.setInt(1, startFrom);
            preparedStatement.setInt(2, rowCount);
            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                List<ExpositionEntity> entities = new ArrayList<>();
                while (resultSet.next()) {
                    mapResultSetToEntity(resultSet).ifPresent(entities::add);
                }
                return entities;
            }
        } catch (SQLException e) {
            throw new DataBaseRuntimeException("Connection not established", e);
        }
    }

    @Override
    public long countByEndDateGreaterThan() {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(COUNT_BY_END_DATE_GREATER_THAN)) {

            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() ? resultSet.getLong("count") : 0;
            }
        } catch (SQLException e) {
            throw new DataBaseRuntimeException("Connection was not established", e);
        }
    }

    @Override
    protected void insertStatementMapper(PreparedStatement preparedStatement, ExpositionEntity entity) throws SQLException {
        preparedStatement.setString(1, entity.getTitle());
        preparedStatement.setString(2, entity.getTheme());
        preparedStatement.setDate(3, Date.valueOf(entity.getStartDate()));
        preparedStatement.setDate(4, Date.valueOf(entity.getEndDate()));
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
                .withStartDate(resultSet.getDate("start_date").toLocalDate())
                .withEndDate(resultSet.getDate("end_date").toLocalDate())
                .withTicketPrice(resultSet.getBigDecimal("ticket_price"))
                .withDescription(resultSet.getString("description"))
                .withHall(hall)
                .build());
    }
}
