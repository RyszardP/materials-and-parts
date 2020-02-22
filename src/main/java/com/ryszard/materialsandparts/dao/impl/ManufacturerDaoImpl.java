package com.ryszard.materialsandparts.dao.impl;

import com.ryszard.materialsandparts.dao.ManufacturerDao;
import com.ryszard.materialsandparts.dao.connectionPool.ConnectionPool;
import com.ryszard.materialsandparts.dao.connectionPool.ConnectionPoolException;
import com.ryszard.materialsandparts.domain.to.Manufacturer;
import com.ryszard.materialsandparts.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManufacturerDaoImpl implements ManufacturerDao {

    private static final String DELETE_BY_ID = "DELETE FROM manufacturer WHERE manufacturer_id = ?";
    private static final String SELECT_BY_ID = "SELECT * FROM manufacturer WHERE manufacturer_id = ?";
    private static final String SELECT_BY_TITLE = "SELECT * FROM manufacturer WHERE manufacturer_title = ?";
    private static final String SELECT_ALL = "SELECT * FROM manufacturer";
    private static final String CREATE_MANUFACTURER = "INSERT INTO manufacturer(manufacturer_id, manufacturer_title)" +
            "VALUES(?,?)";
    private static final String LAST_INSERT_ID = "SELECT last_insert_id() as lastId";
    private static final String UPDATE_MANUFACTURER = "UPDATE manufacturer SET manufacturer_title = ? WHERE " +
            "manufacturer_id = ? LIMIT 1";

    private static final String MANUFACTURER_ID = "manufacturer_id";
    private static final String MANUFACTURER_TITLE = "manufacturer_title";

    private static final ConnectionPool pool = ConnectionPool.getInstance();

    ManufacturerDaoImpl() {
    }

    private static class SingletonHolder {
        private static final ManufacturerDao instance = new ManufacturerDaoImpl();
    }

    public static ManufacturerDao getInstance() {
        return SingletonHolder.instance;
    }

    @Override
    public List<Manufacturer> findAll() throws DaoException {
        List<Manufacturer> manufacturerList = new ArrayList<>();
        try (Connection connect = pool.getConnection();
             PreparedStatement statement = connect.prepareStatement(SELECT_ALL)) {
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                Manufacturer manufacturer = new Manufacturer();
                manufacturer.setManufacturerTitle(set.getString(MANUFACTURER_TITLE));
                manufacturerList.add(manufacturer);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception", e);
        }
        return manufacturerList;
    }

    @Override
    public Manufacturer findById(Long id) throws DaoException {
        try (Connection connect = pool.getConnection();
             PreparedStatement statement = connect.prepareStatement(SELECT_BY_ID)) {
            statement.setLong(1, id);
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                Manufacturer manufacturer = new Manufacturer();
                manufacturer.setManufacturerId((long) set.getLong(MANUFACTURER_ID));
                manufacturer.setManufacturerTitle(set.getString(MANUFACTURER_TITLE));
                return manufacturer;
            } else {
                return null;
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("id Exception", e);
        }
    }

    @Override
    public boolean delete(Long manufacturer_id) throws DaoException {
        try (Connection connect = pool.getConnection();
             PreparedStatement statement = connect.prepareStatement(DELETE_BY_ID)) {
            statement.setLong(1, manufacturer_id);
            return true;
        } catch (SQLException | ConnectionPoolException e) {
            e.printStackTrace();
            throw new DaoException("Exception! ", e);
        }
    }

    @Override
    public Long update(Manufacturer entity) throws DaoException {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_MANUFACTURER)) {
            statement.setString(1, entity.getManufacturerTitle());
            int rows = statement.executeUpdate();
            return 0L;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception", e);
        }
    }

    @Override
    public Long create(Manufacturer entity) throws DaoException, ConnectionPoolException {
        return null;
    }
}
