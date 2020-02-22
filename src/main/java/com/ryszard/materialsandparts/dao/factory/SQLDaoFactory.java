package com.ryszard.materialsandparts.dao.factory;


import com.ryszard.materialsandparts.dao.PlateDao;
import com.ryszard.materialsandparts.dao.impl.PlateDaoImpl;

public class SQLDaoFactory extends DaoFactory {

        private static final SQLDaoFactory instance = new SQLDaoFactory();

        private SQLDaoFactory(){}

        protected static SQLDaoFactory getInstance(){
            return instance;
        }


    @Override
    public PlateDao getPlateDao() {
        return PlateDaoImpl.getInstance();
    }
}
