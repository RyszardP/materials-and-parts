package com.ryszard.materialsandparts.dao.impl;

import com.ryszard.materialsandparts.dao.PlateDao;
import com.ryszard.materialsandparts.dao.connectionPool.ConnectionPool;
import com.ryszard.materialsandparts.domain.vo.Plate;

import java.util.List;

public class PlateDaoImpl implements PlateDao {

    private static final String DELETE_BY_ID = "DELETE FROM plate WHERE plate_id = ?";
    private static final String SELECT_BY_ID = "SELECT * FROM plate WHERE plate_id = ?";
    private static final String UPDATE_PLATE_PRICE = "UPDATE plate SET price WHERE plate_id = ? LIMIT  1";
    private static final String CREATE_PLATE = "INSERT INTO plate(plate_type, plate_manufacturer, plate_thickness, " +
            "plate_v_code, plate_sizes, plate_description, plate_price, plate_coefficient) " +
            "VALUES(?, ?, ?, ?, ?, ?, ?, ?,)";
    private static final String LAST_INSERT_ID = "SELECT last_insert_id() as lastId";

    private static final String PLATE_TYPE = "plateType";
    private static final String PLATE_MANUFACTURER = "plateManufacturer";
    private static final String PLATE_THICKNESS = "plateThickness";
    private static final String PLATE_V_CODE = "plateVCode";
    private static final String PLATE_SIZES = "plateSizes";
    private static final String PLATE_DESCRIPTION = "plateDescription";
    private static final String PLATE_PRICE = "platePrice";
    private static final String PLATE_COEFFICIENT = "plateCoefficient";

    private static final ConnectionPool pool = ConnectionPool.getInstance();

    public PlateDaoImpl() {
    }

    private static class SingletonHolder {
        private static final PlateDao instance = new PlateDaoImpl();
    }

    public static PlateDao getInstance() {
        return SingletonHolder.instance;
    }


    @Override
    public List<Plate> findAll() {
        return null;
    }

    @Override
    public Plate findById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Plate save(Plate entity) {
        return null;
    }

    @Override
    public Plate update(Plate entity) {
        return null;
    }
}
