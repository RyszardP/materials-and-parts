package com.ryszard.materialsandparts.dao.utils;

import com.ryszard.materialsandparts.domain.to.Plate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PlateDbUtil {

    private DataSource dataSource;

    public PlateDbUtil(DataSource theDataSource) {
        dataSource = theDataSource;
    }

    public List<Plate> getPlates() throws Exception {

        List<Plate> plates = new ArrayList<>();

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            myConn = dataSource.getConnection();

            String sql = "select * from plate order by plate_type";

            myStmt = myConn.createStatement();

            myRs = myStmt.executeQuery(sql);

            while (myRs.next()) {

                // retrieve data from result set row
                Long plate_id = myRs.getLong("plate_id");
                String plateType = myRs.getString("plate_type");
                String plateManufacturer = myRs.getString("plate_manufacturer");
                String plateThickness = myRs.getString("plate_thickness");
                String plateVCode = myRs.getString("plate_v_code");
                String plateSize = myRs.getString("plate_sizes");
                String plateDescription = myRs.getString("plate_description");
                String platePrice = myRs.getString("plate_price");
                String plateCoefficient = myRs.getString("plate_coefficient");

                // create new  plate object
                Plate tempPlate = new Plate(plate_id, plateType, plateManufacturer, plateThickness, plateVCode,
                        plateSize, plateDescription, platePrice, plateCoefficient);

                // add it to the list of plates
                plates.add(tempPlate);
            }

            return plates;
        } finally {
            close(myConn, myStmt, myRs);
        }
    }

    private void close(Connection myConn, Statement myStmt, ResultSet myRs) {

        try {
            if (myRs != null) {
                myRs.close();
            }

            if (myStmt != null) {
                myStmt.close();
            }

            if (myConn != null) {
                myConn.close();
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public void addPlate(Plate thePlate) throws Exception {
        Connection myConnection = null;
        PreparedStatement myStatement = null;

        try {
            myConnection = dataSource.getConnection();

            String sql = "insert into plate" +
                    "(plate_type, plate_manufacturer, plate_thickness, plate_v_code, plate_sizes, plate_description, " +
                    "plate_price, plate_coefficient) " +
                    "values(?, ?, ?, ?, ?, ?, ?, ?)";
            myStatement = myConnection.prepareStatement(sql);

            myStatement.setString(1, thePlate.getPlateType());
            myStatement.setString(2, thePlate.getPlateManufacturer());
            myStatement.setString(3, thePlate.getPlateThickness());
            myStatement.setString(4, thePlate.getPlateVCode());
            myStatement.setString(5, thePlate.getPlateSizes());
            myStatement.setString(6, thePlate.getPlateDescription());
            myStatement.setString(7, thePlate.getPlatePrice());
            myStatement.setString(8, thePlate.getPlateCoefficient());


        } finally {
            close(myConnection, myStatement, null);
        }
    }

    public Plate getPlate(String thePlateId) throws Exception {
        Plate thePlate = null;
        Connection myConnection = null;
        PreparedStatement myStatement = null;
        ResultSet myResultSet = null;
        long plateId;

        try {
            plateId = Long.parseLong(thePlateId);
            myConnection = dataSource.getConnection();

            String sql = "select * from plate where id = ?";

            myStatement = myConnection.prepareStatement(sql);
            myStatement.setLong(1, plateId);
            myResultSet = myStatement.executeQuery();

            if (myResultSet.next()) {
                String plateType = myResultSet.getString("plate_type");
                String plateManufacturer = myResultSet.getString("plate_manufacturer");
                String plateThickness = myResultSet.getString("plate_thickness");
                String plateVCode = myResultSet.getString("plate_v_code");
                String plateSizes = myResultSet.getString("plate_sizes");
                String plateDescription = myResultSet.getString("plate_description");
                String platePrice = myResultSet.getString("plate_price");
                String plateCoefficient = myResultSet.getString("plate_coefficient");

                thePlate = new Plate(plateId, plateType, plateManufacturer, plateThickness, plateVCode, plateSizes,
                        plateDescription, platePrice, plateCoefficient);
            } else {
                throw new Exception("Could not find plate id: " + plateId);
            }
            return thePlate;
        } finally {
            close(myConnection, myStatement, myResultSet);
        }

    }

    public void updatePlate(Plate thePlate) throws Exception {
        Connection connection = null;
        PreparedStatement myPreparedStatement = null;
        try {
            connection = dataSource.getConnection();

            String sql = "update plate " +
                    "SET plate_type = ?, plate_manufacturer = ?, plate_thickness = ?, plate_v_code = ?, plate_sizes = ?," +
                    " plate_description = ?, plate_price= ?, plate_coefficient= ?" +
                    "where plate_id = ?";

            myPreparedStatement = connection.prepareStatement(sql);

            myPreparedStatement.setString(1, thePlate.getPlateType());
            myPreparedStatement.setString(2, thePlate.getPlateManufacturer());
            myPreparedStatement.setString(3, thePlate.getPlateThickness());
            myPreparedStatement.setString(4, thePlate.getPlateVCode());
            myPreparedStatement.setString(5, thePlate.getPlateSizes());
            myPreparedStatement.setString(6, thePlate.getPlateDescription());
            myPreparedStatement.setString(7, thePlate.getPlatePrice());
            myPreparedStatement.setString(8, thePlate.getPlateCoefficient());
            myPreparedStatement.execute();
        } finally {
            close(connection, myPreparedStatement, null);
        }
    }

    public void deletePlate(String thePlateId) throws Exception {

        Connection myConnection = null;
        PreparedStatement myPreparedStatement = null;

        try {
            Long plateId = Long.parseLong(thePlateId);
            myConnection = dataSource.getConnection();

            String sql = "delete from plate where plate_id = ?";
            myPreparedStatement = myConnection.prepareStatement(sql);
            myPreparedStatement.setLong(1, plateId);
            myPreparedStatement.execute();
        } finally {
            close(myConnection, myPreparedStatement, null);
        }
    }

}
