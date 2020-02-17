package com.ryszard.materialsandparts.dao;

import com.ryszard.materialsandparts.dao.connectionPool.ConnectionPoolException;
import com.ryszard.materialsandparts.exception.DaoException;

import java.util.List;

public interface GenericDao<T, K> {

    List<T> findAll()throws DaoException;

    T findById(K id)throws DaoException;

    boolean delete(K id)throws DaoException;

    K update(T entity)throws DaoException;

    Long create(T entity) throws DaoException, ConnectionPoolException;
}
