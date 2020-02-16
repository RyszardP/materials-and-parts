package com.ryszard.materialsandparts.dao.factory;



public abstract class DaoFactory {
    public static DaoFactory getDaoFactory() {
        return SQLDaoFactory.getInstance();
    }


}
