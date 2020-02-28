package com.ryszard.materialsandparts.dao.utils;

import com.ryszard.materialsandparts.domain.to.PlateType;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PlateTypeDbUtil {

    private DataSource dataSource;

    public PlateTypeDbUtil(DataSource theDataSource) {
        dataSource = theDataSource;
    }

    public List<PlateType> getPlateTypes() throws Exception {
        List<PlateType> plateTypes = new ArrayList<>();
        Connection myConnection = null;
        Statement myStatement = null;
        ResultSet myResultSet = null;
        try {
            myConnection = dataSource.getConnection();
            String sql = "select * from plate_type order by plate_type_name";
            myStatement = myConnection.createStatement();
            myResultSet = myStatement.executeQuery(sql);
            while (myResultSet.next()) {
                Long plateTypeId = (long) myResultSet.getInt("plate_type_id");
                String plateTypeName = myResultSet.getString("plate_type_name");
                PlateType tempPlateType = new PlateType(plateTypeId, plateTypeName);
                plateTypes.add(tempPlateType);
            }
            return plateTypes;
        } finally {
            close(myConnection, myStatement, myResultSet);
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

    public void addPlateType(PlateType thePlateType) throws Exception {
        Connection myConnection = null;
        PreparedStatement myStatement = null;
        try {
            myConnection = dataSource.getConnection();
            String sql = "insert into plate_type "
                    + "(plate_type_name) "
                    + "values (?)";
            myStatement = myConnection.prepareStatement(sql);
            myStatement.setString(1, thePlateType.getPlateTypeName());
            myStatement.execute();
        } finally {
            close(myConnection, myStatement, null);
        }
    }

    public PlateType getPlateType(String thePlateTypeId) throws Exception {
        PlateType thePlateType = null;
        Connection myConnection = null;
        PreparedStatement myPreparedSatement = null;
        ResultSet myResultSet = null;
        long plateTypeId;
        try {
            plateTypeId = Long.parseLong(thePlateTypeId);
            myConnection = dataSource.getConnection();
            String sql = "select * from plate_type where plate_type_id=?";
            myPreparedSatement = myConnection.prepareStatement(sql);
            myPreparedSatement.setLong(1, plateTypeId);
            myResultSet = myPreparedSatement.executeQuery();
            if (myResultSet.next()) {
                String plateTypeName = myResultSet.getString("first_type_name");
                thePlateType = new PlateType(plateTypeId, plateTypeName);
            } else {
                throw new Exception("Could not find plate type id: " + plateTypeId);
            }
            return thePlateType;
        } finally {
            close(myConnection, myPreparedSatement, myResultSet);
        }
    }

    public void updatePlateType(PlateType thePlateType) throws Exception {
        Connection myConnection = null;
        PreparedStatement myStatement = null;
        try {
            myConnection = dataSource.getConnection();
            String sql = "update plate_type "
                    + "set plate_type_name=? "
                    + "where plate_type_id=?";
            myStatement = myConnection.prepareStatement(sql);
            myStatement.setString(1, thePlateType.getPlateTypeName());
            myStatement.execute();
        } finally {
            close(myConnection, myStatement, null);
        }
    }

    public void deletePlateType(String thePlateTypeId) throws Exception {
        Connection myConnection = null;
        PreparedStatement myPreparedStatement = null;
        try {
            long plateTypeId = Integer.parseInt(thePlateTypeId);
            myConnection = dataSource.getConnection();
            String sql = "delete from plate_type where plate_type_id=?";
            myPreparedStatement = myConnection.prepareStatement(sql);
            myPreparedStatement.setLong(1, plateTypeId);
            myPreparedStatement.execute();
        } finally {
            close(myConnection, myPreparedStatement, null);
        }
    }

}
