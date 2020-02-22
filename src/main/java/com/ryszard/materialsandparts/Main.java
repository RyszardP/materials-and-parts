package com.ryszard.materialsandparts;

import com.ryszard.materialsandparts.dao.PlateDao;
import com.ryszard.materialsandparts.dao.connectionPool.ConnectionPool;
import com.ryszard.materialsandparts.dao.connectionPool.ConnectionPoolException;
import com.ryszard.materialsandparts.dao.factory.DaoFactory;
import com.ryszard.materialsandparts.exception.DaoException;
import com.ryszard.materialsandparts.exception.NoSuchEntityException;

public class Main {

    private static DaoFactory factory = DaoFactory.getDaoFactory();

    public static void main(String[] args) throws ConnectionPoolException, NoSuchEntityException {
        ConnectionPool.getInstance().init();
        PlateDao plateDao = factory.getPlateDao();
        try {
            System.out.println(plateDao.findById(4L));
            System.out.println(plateDao.delete(9L));
            System.out.println(plateDao.findAll());

            // System.out.println(plateDao.create(new Plate("1","1","10","1",
            //        "1","nothing","1","1.0")));

        } catch (DaoException e) {
            e.printStackTrace();
        }


    }
}
