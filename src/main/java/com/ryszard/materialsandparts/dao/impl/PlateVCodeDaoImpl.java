package com.ryszard.materialsandparts.dao.impl;

import com.ryszard.materialsandparts.dao.PlateTypeDao;
import com.ryszard.materialsandparts.dao.connectionPool.ConnectionPoolException;
import com.ryszard.materialsandparts.domain.to.PlateType;
import com.ryszard.materialsandparts.exception.DaoException;

import java.util.List;

public class PlateVCodeDaoImpl implements PlateTypeDao {
    @Override
    public List<PlateType> findAll() throws DaoException {
        return null;
    }

    @Override
    public PlateType findById(Long id) throws DaoException {
        return null;
    }

    @Override
    public boolean delete(Long id) throws DaoException {
        return false;
    }

    @Override
    public Long update(PlateType entity) throws DaoException {
        return null;
    }

    @Override
    public Long create(PlateType entity) throws DaoException, ConnectionPoolException {
        return null;
    }
}
