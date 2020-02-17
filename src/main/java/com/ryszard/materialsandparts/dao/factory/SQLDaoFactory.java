package com.ryszard.materialsandparts.dao.factory;


import com.ryszard.materialsandparts.dao.PlateDao;

public class SQLDaoFactory extends DaoFactory {

        private static final SQLDaoFactory instance = new SQLDaoFactory();

        private SQLDaoFactory(){}

        private static SQLDaoFactory getInstance(){
            return instance;
        }


    @Override
    public PlateDao plateDao() {
        return null;
    }
}
