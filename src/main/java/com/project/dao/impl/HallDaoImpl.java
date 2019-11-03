package com.project.dao.impl;

import com.project.dao.DBConnector;
import com.project.dao.HallDao;
import com.project.domain.hall.Hall;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class HallDaoImpl extends AbstractDaoImpl<Hall> implements HallDao {
    private static final String SAVE_QUERY = "INSERT INTO halls(name, city, street, house_number) VALUES (?,?,?,?)";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM halls WHERE id = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM halls";
    private static final String UPDATE_QUERY = "UPDATE halls SET name = ?, city = ?, street = ?, house_number = ? WHERE id = ?";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM users WHERE id = ?";
    private static final String FIND_BY_NAME_QUERY = "SELECT * FROM halls WHERE name = ?";
    private static final String FIND_BY_CITY_QUERY = "SELECT * FROM halls WHERE city = ?";
    private static final String FIND_BY_STREET_QUERY = "SELECT * FROM halls WHERE street = ?";

    public HallDaoImpl(DBConnector connector) {
        super(connector, SAVE_QUERY, FIND_BY_ID_QUERY, FIND_ALL_QUERY, UPDATE_QUERY, DELETE_BY_ID_QUERY);
    }

    @Override
    public Optional<Hall> findByName(String name) {
        return findByStringParam(name, FIND_BY_NAME_QUERY);
    }

    @Override
    public List<Hall> findByCity(String city) {
        return findListByStringParam(city, FIND_BY_CITY_QUERY);
    }

    @Override
    public Optional<Hall> findByStreetName(String streetName) {
        return findByStringParam(streetName, FIND_BY_STREET_QUERY);
    }

    @Override
    protected void insertStatementMapper(PreparedStatement preparedStatement, Hall entity) throws SQLException {
        preparedStatement.setString(1, entity.getName());
        preparedStatement.setString(2, entity.getCity());
        preparedStatement.setString(3, entity.getStreet());
        preparedStatement.setInt(4, entity.getHouseNumber());
    }

    @Override
    protected void updateStatementMapper(PreparedStatement preparedStatement, Hall entity) throws SQLException {
        insertStatementMapper(preparedStatement, entity);
        preparedStatement.setLong(5, entity.getId());
    }

    @Override
    protected Optional<Hall> mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return Optional.ofNullable(Hall.builder()
                .withId(resultSet.getLong("id"))
                .withName(resultSet.getString("name"))
                .withCity(resultSet.getString("city"))
                .withStreet(resultSet.getString("street"))
                .withHouseNumber(resultSet.getInt("house_number"))
                .build());
    }
}
