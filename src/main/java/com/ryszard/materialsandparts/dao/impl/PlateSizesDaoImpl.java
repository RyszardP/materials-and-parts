package com.ryszard.materialsandparts.dao.impl;

import com.ryszard.materialsandparts.dao.PlateSizesDao;
import com.ryszard.materialsandparts.dao.connectionPool.ConnectionPoolException;
import com.ryszard.materialsandparts.domain.to.PlateSizes;
import com.ryszard.materialsandparts.exception.DaoException;

import java.util.List;

public class PlateSizesDaoImpl implements PlateSizesDao {
    @Override
    public List<PlateSizes> findAll() throws DaoException {
        return null;
    }

    @Override
    public PlateSizes findById(Long id) throws DaoException {
        return null;
    }

    @Override
    public boolean delete(Long id) throws DaoException {
        return false;
    }

    @Override
    public Long update(PlateSizes entity) throws DaoException {
        return null;
    }

    @Override
    public Long create(PlateSizes entity) throws DaoException, ConnectionPoolException {
        return null;
    }
}
