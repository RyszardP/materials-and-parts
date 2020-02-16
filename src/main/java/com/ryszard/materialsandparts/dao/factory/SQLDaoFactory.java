package com.ryszard.materialsandparts.dao.factory;



public class SQLDaoFactory extends DaoFactory {

        private static final SQLDaoFactory instance = new SQLDaoFactory();

        private SQLDaoFactory(){}

        public static SQLDaoFactory getInstance(){
            return instance;
        }



    }
