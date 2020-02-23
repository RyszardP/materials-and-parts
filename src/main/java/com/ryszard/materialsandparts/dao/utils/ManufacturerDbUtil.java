package com.ryszard.materialsandparts.dao.utils;

import com.ryszard.materialsandparts.domain.to.Manufacturer;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ManufacturerDbUtil {

    private DataSource dataSource;

    public ManufacturerDbUtil(DataSource theDataSource) {
        dataSource = theDataSource;
    }

    public List<Manufacturer> getManufacturers() throws Exception {

        List<Manufacturer> manufacturers = new ArrayList<>();
        Connection myConnection = null;
        Statement myStatement = null;
        ResultSet resultSet = null;

        try {
            myConnection = dataSource.getConnection();
            String sql = "select * from manufacturer order by manufacturer_title";
            myStatement = myConnection.createStatement();
            resultSet = myStatement.executeQuery(sql);

            while (resultSet.next()) {
                Long manufacturerId = resultSet.getLong("manufacturer_id");
                String manufacturerTitle = resultSet.getString("manufacturer_title");
                Manufacturer tempManufacturer = new Manufacturer(manufacturerId, manufacturerTitle);
                manufacturers.add(tempManufacturer);
            }
            return manufacturers;
        } finally {
            close(myConnection, myStatement, resultSet);
        }
    }

    private void close(Connection myConnection, Statement myStatement, ResultSet myResultSet) {

        try {
            if (myResultSet != null) {
                myResultSet.close();
            }

            if (myStatement != null) {
                myStatement.close();
            }

            if (myConnection != null) {
                myConnection.close();
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public void addManufacturer(Manufacturer theManufacturer) throws Exception{
        Connection myConnection = null;
        PreparedStatement myStatement = null;
        try {
            myConnection = dataSource.getConnection();
            String sql = "insert into manufacturer " +
                    "(manufacturer_title) values (?)";
        myStatement = myConnection.prepareStatement(sql);
        myStatement.setString(1,theManufacturer.getManufacturerTitle());
        myStatement.execute();
        } finally {
            close(myConnection,myStatement,null);
        }
    }

    public Manufacturer getManufacturer(String theManufacturerId) throws Exception{
        Manufacturer theManufacturer = null;

        Connection myConnection = null;
        PreparedStatement myPreparedStatement = null;
        ResultSet myResultSet = null;
       long manufacturerId;

        try{
            manufacturerId = Long.parseLong(theManufacturerId);
            myConnection = dataSource.getConnection();
            String sql = "select * from manufacturer where manufacturer_id=?";
            myPreparedStatement = myConnection.prepareStatement(sql);
            myPreparedStatement.setLong(1,manufacturerId);
            myResultSet = myPreparedStatement.executeQuery();
            if(myResultSet.next()){
                String manufacturerTitle = myResultSet.getString("manufacturer_title");
                theManufacturer = new Manufacturer(manufacturerId,manufacturerTitle);
            }
            else {
                throw new Exception("Could not find manufacturer id: " + manufacturerId);
            }
            return theManufacturer;
        }
        finally {
            close(myConnection,myPreparedStatement,myResultSet);
        }
    }

    public void updateManufaturer(Manufacturer theManufacturer) throws Exception{
        Connection myConnection = null;
        PreparedStatement thePrparedStatement = null;

        try {
            myConnection = dataSource.getConnection();
            String sql = "update manufacturer set manufacturer_title=? where manufacturer_id = ?";
            thePrparedStatement = myConnection.prepareStatement(sql);
            thePrparedStatement.setString(1,theManufacturer.getManufacturerTitle());
            thePrparedStatement.execute();
        }
        finally {
            close(myConnection,thePrparedStatement,null);
        }
    }

    public void deleteManufacturer(String theManufacturerId) throws Exception{
        Connection myConnection = null;
        PreparedStatement myPreparedStatement = null;
        try {
            Long manufacturerId = Long.parseLong(theManufacturerId);
            myConnection = dataSource.getConnection();
            String sql = "delete from manufacturer where manufacturer_id = ?";
            myPreparedStatement = myConnection.prepareStatement(sql);
            myPreparedStatement.setLong(1, Long.parseLong(theManufacturerId));
            myPreparedStatement.execute();
        }
        finally {
            close(myConnection,myPreparedStatement,null);
        }
    }
}
