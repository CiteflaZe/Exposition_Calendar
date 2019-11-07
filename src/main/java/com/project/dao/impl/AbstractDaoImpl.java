package com.project.dao.impl;

import com.project.dao.CrudDao;
import com.project.dao.DBConnector;
import com.project.exception.DataBaseRuntimeException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;

public abstract class AbstractDaoImpl<E> implements CrudDao<E, Long> {
    private static final Logger LOGGER = Logger.getLogger(AbstractDaoImpl.class);
    private static final BiConsumer<PreparedStatement, Long> LONG_CONSUMER =
            (PreparedStatement preparedStatement, Long param) -> {
                try {
                    preparedStatement.setLong(1, param);
                } catch (SQLException e) {
                    throw new DataBaseRuntimeException(e);
                }
            };
    private static final BiConsumer<PreparedStatement, String> STRING_CONSUMER =
            (PreparedStatement preparedStatement, String param) -> {
                try {
                    preparedStatement.setString(1, param);
                } catch (SQLException e) {
                    throw new DataBaseRuntimeException(e);
                }
            };

    protected final DBConnector connector;
    private final String saveQuery;
    private final String findByIdQuery;
    private final String findAllQuery;
    private final String updateQuery;


    public AbstractDaoImpl(DBConnector connector, String saveQuery,
                           String findByIdQuery, String findAllQuery,
                           String updateQuery) {
        this.connector = connector;
        this.saveQuery = saveQuery;
        this.findByIdQuery = findByIdQuery;
        this.findAllQuery = findAllQuery;
        this.updateQuery = updateQuery;
    }

    @Override
    public boolean save(E entity) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(saveQuery)) {

            insertStatementMapper(preparedStatement, entity);
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            LOGGER.error("Failed to establish connection to database");
            throw new DataBaseRuntimeException(e);
        }
    }

    @Override
    public Optional<E> findById(Long id) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(findByIdQuery)) {
            preparedStatement.setLong(1, id);

            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToEntity(resultSet);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            LOGGER.error("Connection not established");
            throw new DataBaseRuntimeException(e);
        }
    }

    //TODO pagination
    @Override
    public List<E> findAll(Integer firstRow, Integer startFrom) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(findAllQuery)) {

            preparedStatement.setInt(1, firstRow);
            preparedStatement.setInt(2, startFrom);
            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                List<E> entities = new ArrayList<>();
                while (resultSet.next()) {
                    mapResultSetToEntity(resultSet).ifPresent(entities::add);
                }
                return entities;
            }
        } catch (SQLException e) {
            LOGGER.error("Connection not established", e);
            throw new DataBaseRuntimeException(e);
        }
    }

    @Override
    public boolean update(E entity) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

            updateStatementMapper(preparedStatement, entity);
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            LOGGER.error("Connection not established");
            throw new DataBaseRuntimeException(e);
        }
    }

    protected Optional<E> findByLongParam(Long id, String query) {
        return findByParam(id, query, LONG_CONSUMER);
    }

    protected List<E> findListByLongParam(Long id, String query){
        return findListByParam(id, query, LONG_CONSUMER);
    }

    protected Optional<E> findByStringParam(String param, String query) {
        return findByParam(param, query, STRING_CONSUMER);
    }
    protected List<E> findListByStringParam(String param, String query){
        return findListByParam(param, query, STRING_CONSUMER);
    }

    private <T> Optional<E> findByParam(T param, String query, BiConsumer<PreparedStatement, T> consumer) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            consumer.accept(preparedStatement, param);
            try(final ResultSet resultSet = preparedStatement.executeQuery()){
                return resultSet.next() ? mapResultSetToEntity(resultSet) : Optional.empty();
            }
        } catch (SQLException e){
            LOGGER.error("Failed operation", e);
            throw new DataBaseRuntimeException(e);
        }
    }

    private <T> List<E> findListByParam(T param, String query, BiConsumer<PreparedStatement, T> consumer){
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            consumer.accept(preparedStatement, param);
            try(final ResultSet resultSet = preparedStatement.executeQuery()){
                List<E> entities = new ArrayList<>();
                while (resultSet.next()) {
                    mapResultSetToEntity(resultSet).ifPresent(entities::add);
                }
                return entities;
            }
        } catch (SQLException e){
            LOGGER.error("Failed operation", e);
            throw new DataBaseRuntimeException(e);
        }
    }

    protected abstract void insertStatementMapper(PreparedStatement preparedStatement, E entity) throws SQLException;

    protected abstract void updateStatementMapper(PreparedStatement preparedStatement, E entity) throws SQLException;

    protected abstract Optional<E> mapResultSetToEntity(ResultSet resultSet) throws SQLException;
}
