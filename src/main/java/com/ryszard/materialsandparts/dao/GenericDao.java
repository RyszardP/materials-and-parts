package com.ryszard.materialsandparts.dao;

import com.ryszard.materialsandparts.exception.DaoException;

import java.util.List;

public interface GenericDao<T, K> {

    List<T> findAll()throws DaoException;

    T findById(K id)throws DaoException;

    boolean delete(K id)throws DaoException;

    T save(T entity)throws DaoException;

    T update(T entity)throws DaoException;
}
