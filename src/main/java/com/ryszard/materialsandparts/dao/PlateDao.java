package com.ryszard.materialsandparts.dao;

import com.ryszard.materialsandparts.domain.vo.Plate;
import com.ryszard.materialsandparts.exception.DaoException;

public interface PlateDao extends GenericDao<Plate,Long> {
    boolean checkPlate(String plateType, String plateManufacturer, String plateVCode) throws DaoException;
}
