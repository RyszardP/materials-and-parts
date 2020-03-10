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
                int plateId = myRs.getInt("plate_id");
                int plateType = myRs.getInt("plate_type");
                int plateManufacturer = myRs.getInt("plate_manufacturer");
                int plateThickness = myRs.getInt("plate_thickness");
                int plateVCode = myRs.getInt("plate_v_code");
                int plateSize = myRs.getInt("plate_sizes");
                String plateDescription = myRs.getString("plate_description");
                double platePrice = myRs.getDouble("plate_price");
                double plateCoefficient = myRs.getDouble("plate_coefficient");

                // create new  plate object
                Plate tempPlate = new Plate(plateId, plateType, plateManufacturer, plateThickness, plateVCode,
                        plateSize, plateDescription, platePrice, plateCoefficient);

                // add it to the list of plates
                plates.add(tempPlate);
            }
            return plates;
        } finally {
            close(myConn, myStmt, myRs);
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

    public void addPlate(Plate thePlate) throws Exception {
        Connection myConnection = null;
        PreparedStatement myStatement = null;

        try {
            myConnection = dataSource.getConnection();
            String sql = "insert into plate" +
                    "(plate_type, plate_manufacturer, plate_thickness, plate_v_code, plate_sizes, plate_description, " +
                    "plate_price, plate_coefficient) " +
                    "values (?, ?, ?, ?, ?, ?, ?, ?)";
            myStatement = myConnection.prepareStatement(sql);
            myStatement.setInt(1, thePlate.getPlateType());
            myStatement.setInt(2, thePlate.getPlateManufacturer());
            myStatement.setInt(3, thePlate.getPlateThickness());
            myStatement.setInt(4, thePlate.getPlateVCode());
            myStatement.setInt(5, thePlate.getPlateSizes());
            myStatement.setString(6, thePlate.getPlateDescription());
            myStatement.setDouble(7, thePlate.getPlatePrice());
            myStatement.setDouble(8, thePlate.getPlateCoefficient());
            myStatement.execute();
        } finally {
            close(myConnection, myStatement, null);
        }
    }

    public Plate getPlate(String thePlateId) throws Exception {
        Plate thePlate = null;
        Connection myConnection = null;
        PreparedStatement myStatement = null;
        ResultSet myResultSet = null;
        int plateId;

        try {
            plateId = Integer.parseInt(thePlateId);
            myConnection = dataSource.getConnection();

            String sql = "select * from plate where plate_id = ?";

            myStatement = myConnection.prepareStatement(sql);
            myStatement.setInt(1, plateId);
            myResultSet = myStatement.executeQuery();

            if (myResultSet.next()) {
                int plateType = myResultSet.getInt("plate_type");
                int plateManufacturer = myResultSet.getInt("plate_manufacturer");
                int plateThickness = myResultSet.getInt("plate_thickness");
                int plateVCode = myResultSet.getInt("plate_v_code");
                int plateSizes = myResultSet.getInt("plate_sizes");
                String plateDescription = myResultSet.getString("plate_description");
                double platePrice = myResultSet.getDouble("plate_price");
                double plateCoefficient = myResultSet.getDouble("plate_coefficient");

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

            myPreparedStatement.setInt(1, thePlate.getPlateType());
            myPreparedStatement.setInt(2, thePlate.getPlateManufacturer());
            myPreparedStatement.setInt(3, thePlate.getPlateThickness());
            myPreparedStatement.setInt(4, thePlate.getPlateVCode());
            myPreparedStatement.setInt(5, thePlate.getPlateSizes());
            myPreparedStatement.setString(6, thePlate.getPlateDescription());
            myPreparedStatement.setDouble(7, thePlate.getPlatePrice());
            myPreparedStatement.setDouble(8, thePlate.getPlateCoefficient());
            myPreparedStatement.setInt(9,thePlate.getPlateId());
            myPreparedStatement.execute();
        } finally {
            close(connection, myPreparedStatement, null);
        }
    }

    public void deletePlate(String thePlateId) throws Exception {

        Connection myConnection = null;
        PreparedStatement myPreparedStatement = null;

        try {
            int plateId = Integer.parseInt(thePlateId);
            myConnection = dataSource.getConnection();

            String sql = "delete from plate where plate_id = ?";
            myPreparedStatement = myConnection.prepareStatement(sql);
            myPreparedStatement.setInt(1, plateId);
            myPreparedStatement.execute();
        } finally {
            close(myConnection, myPreparedStatement, null);
        }
    }

}
