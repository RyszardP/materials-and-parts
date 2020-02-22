package com.ryszard.materialsandparts.dao.impl;

import com.ryszard.materialsandparts.dao.ThicknessDao;
import com.ryszard.materialsandparts.dao.connectionPool.ConnectionPoolException;
import com.ryszard.materialsandparts.domain.to.Thickness;
import com.ryszard.materialsandparts.exception.DaoException;

import java.util.List;

public class ThicknessDaoImpl implements ThicknessDao {

    @Override
    public List<Thickness> findAll() throws DaoException {
        return null;
    }

    @Override
    public Thickness findById(Long id) throws DaoException {
        return null;
    }

    @Override
    public boolean delete(Long id) throws DaoException {
        return false;
    }

    @Override
    public Long update(Thickness entity) throws DaoException {
        return null;
    }

    @Override
    public Long create(Thickness entity) throws DaoException, ConnectionPoolException {
        return null;
    }
}
