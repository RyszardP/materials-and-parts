package com.ryszard.materialsandparts.dao.factory;


import com.ryszard.materialsandparts.dao.PlateDao;

public abstract class DaoFactory {
    public static DaoFactory getDaoFactory() {
        return SQLDaoFactory.getInstance();
    }

public abstract PlateDao getPlateDao();
}
