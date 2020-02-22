package com.ryszard.materialsandparts.dao.impl;

import com.ryszard.materialsandparts.dao.PlateDao;
import com.ryszard.materialsandparts.dao.connectionPool.ConnectionPool;
import com.ryszard.materialsandparts.dao.connectionPool.ConnectionPoolException;
import com.ryszard.materialsandparts.domain.to.Plate;
import com.ryszard.materialsandparts.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlateDaoImpl implements PlateDao {

    private static final String DELETE_BY_ID = "DELETE FROM plate WHERE plate_id = ?";
    private static final String SELECT_BY_ID = "SELECT * FROM plate WHERE plate_id = ?";
    private static final String SELECT_ALL_PLATES = "SELECT * FROM plate";
    private static final String SELECT_PLATE_BY_TYPE_MANUFACTURER_VCODE = "SELECT * FROM plate WHERE plate_type = ?" +
            "and plate_manufacturer = ? and plate_v_code = ?";
    private static final String UPDATE_PLATE_PRICE = "UPDATE plate SET price = ? WHERE plate_id = ? LIMIT  1";
    private static final String CREATE_PLATE = "INSERT INTO plate(plate_type, plate_manufacturer, plate_thickness, " +
            "plate_v_code, plate_sizes, plate_description, plate_price, plate_coefficient) " +
            "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String LAST_INSERT_ID = "SELECT last_insert_id() as lastId";

    private static final String PLATE_ID = "plate_id";
    private static final String PLATE_TYPE = "plate_type";
    private static final String PLATE_MANUFACTURER = "plate_manufacturer";
    private static final String PLATE_THICKNESS = "plate_thickness";
    private static final String PLATE_V_CODE = "plate_v_code";
    private static final String PLATE_SIZES = "plate_sizes";
    private static final String PLATE_DESCRIPTION = "plate_description";
    private static final String PLATE_PRICE = "plate_price";
    private static final String PLATE_COEFFICIENT = "plate_coefficient";

    private static final ConnectionPool pool = ConnectionPool.getInstance();

    private PlateDaoImpl() {
    }

    private static class SingletonHolder {
        private static final PlateDao instance = new PlateDaoImpl();
    }

    public static PlateDao getInstance() {
        return SingletonHolder.instance;
    }

    @Override
    public boolean checkPlate(String plateType, String plateManufacturer, String plateVCode) throws DaoException {
        try (Connection connect = pool.getConnection();
             PreparedStatement statement = connect.prepareStatement(SELECT_PLATE_BY_TYPE_MANUFACTURER_VCODE)) {
            statement.setString(1, plateType);
            statement.setString(2, plateManufacturer);
            statement.setString(3, plateVCode);
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception", e);
        }
    }

    @Override
    public List<Plate> findAll() throws DaoException {
        List<Plate> plateList = new ArrayList<>();
        try (Connection connect = pool.getConnection();
             PreparedStatement statement = connect.prepareStatement(SELECT_ALL_PLATES)) {
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                Plate plate = new Plate();
                plate.setPlateType(set.getString(PLATE_TYPE));
                plate.setPlateManufacturer(set.getString(PLATE_MANUFACTURER));
                plate.setPlateThickness(set.getString(PLATE_THICKNESS));
                plate.setPlateVCode(set.getString(PLATE_V_CODE));
                plate.setPlateSizes(set.getString(PLATE_SIZES));
                plate.setPlateDescription(set.getString(PLATE_DESCRIPTION));
                plate.setPlatePrice(set.getString(PLATE_PRICE));
                plate.setPlateCoefficient(set.getString(PLATE_COEFFICIENT));
                plateList.add(plate);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception", e);
        }
        return plateList;
    }

    @Override
    public Plate findById(Long id) throws DaoException {
        try (Connection connect = pool.getConnection();
             PreparedStatement statement = connect.prepareStatement(SELECT_BY_ID)) {
            statement.setLong(1, id);
            ResultSet set = statement.executeQuery();

            if (set.next()) {
                Plate plate = new Plate();
                plate.setPlateId((long) set.getLong(PLATE_ID));
                plate.setPlateType(set.getString(PLATE_TYPE));
                plate.setPlateManufacturer(set.getString(PLATE_MANUFACTURER));
                plate.setPlateThickness(set.getString(PLATE_THICKNESS));
                plate.setPlateVCode(set.getString(PLATE_V_CODE));
                plate.setPlateSizes(set.getString(PLATE_SIZES));
                plate.setPlateDescription(set.getString(PLATE_DESCRIPTION));
                plate.setPlatePrice(set.getString(PLATE_PRICE));
                plate.setPlateCoefficient(set.getString(PLATE_COEFFICIENT));
                return plate;
            } else {
                return null;
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("id Exception", e);
        }
    }

    @Override
    public boolean delete(Long id) throws DaoException {
        try (Connection connect = pool.getConnection();
             PreparedStatement statement = connect.prepareStatement(DELETE_BY_ID)) {
            statement.setLong(1, id);
            return true;
        } catch (SQLException | ConnectionPoolException e) {
            e.printStackTrace();
            throw new DaoException("Exception! ", e);
        }
    }


    @Override
    public Long update(Plate entity) throws DaoException {
        try (Connection connect = pool.getConnection();
             PreparedStatement statement = connect.prepareStatement(UPDATE_PLATE_PRICE)) {
            statement.setString(1, String.valueOf(entity.getPlateId()));
            int rows = statement.executeUpdate();
            return entity.getPlateId();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception", e);
        }
    }

    @Override
    public Long create(Plate entity) throws DaoException, ConnectionPoolException {
        ConnectionPool.getInstance().init();
        try (Connection connect = pool.getConnection();
             PreparedStatement statement = connect.prepareStatement(CREATE_PLATE)) {
            statement.setString(1, entity.getPlateType());
            statement.setString(2, entity.getPlateManufacturer());
            statement.setString(3, entity.getPlateThickness());
            statement.setString(4, entity.getPlateVCode());
            statement.setString(5, entity.getPlateSizes());
            statement.setString(6, entity.getPlateDescription());
            statement.setString(7, entity.getPlatePrice());
            statement.setString(8, entity.getPlateCoefficient());
            int rows = statement.executeUpdate();
            return entity.getPlateId();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception", e);
        }
    }
}
