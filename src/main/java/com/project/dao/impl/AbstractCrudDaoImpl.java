package com.project.dao.impl;

import com.project.dao.CrudDao;
import com.project.dao.DBConnector;
import com.project.dao.DataBaseRuntimeException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public abstract class AbstractCrudDaoImpl<E, ID extends Long> implements CrudDao<E, ID> {
    private static final Logger LOGGER = Logger.getLogger(AbstractCrudDaoImpl.class);

    protected final DBConnector connector;

    public AbstractCrudDaoImpl(DBConnector connector) {
        this.connector = connector;
    }

    protected Optional<E> findById(ID id, String query) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);

            final ResultSet resultSet = preparedStatement.executeQuery();

            return mapResultSetToEntity(resultSet);
        } catch (SQLException e) {
           LOGGER.error("Connection not established");
           throw new DataBaseRuntimeException(e);
        }
    }

    protected abstract Optional<E> mapResultSetToEntity(ResultSet resultSet) throws SQLException;
}
