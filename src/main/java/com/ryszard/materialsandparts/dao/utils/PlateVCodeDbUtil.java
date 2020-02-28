package com.ryszard.materialsandparts.dao.utils;

import com.ryszard.materialsandparts.domain.to.PlateType;
import com.ryszard.materialsandparts.domain.to.PlateVCode;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PlateVCodeDbUtil {

    private DataSource dataSource;

    public PlateVCodeDbUtil(DataSource theDataSource) {
        dataSource = theDataSource;
    }

    public List<PlateVCode> getPlateVCodes() throws Exception {

        List<PlateVCode> plateVCodes = new ArrayList<>();
        Connection myConnection = null;
        Statement myStatement = null;
        ResultSet myResultSet = null;

        try {
            myConnection = dataSource.getConnection();
            String sql = "select * from plate_v_code order by plate_v_code_title";
            myStatement = myConnection.createStatement();
            myResultSet = myStatement.executeQuery(sql);
            while (myResultSet.next()) {
                Long plateVCodeId = (long) myResultSet.getInt("plate_v_code_id");
                String plateVCodeTitle = myResultSet.getString("plate_v_code_title");
                String plateVCodeDescription = myResultSet.getString("plate_v_code_description");
                PlateVCode tempPlateVCode = new PlateVCode(plateVCodeId, plateVCodeTitle,plateVCodeDescription);
                plateVCodes.add(tempPlateVCode);
            }
            return plateVCodes;
        } finally {
            close(myConnection, myStatement, myResultSet);
        }
    }

    private void close(Connection myConnection, Statement myStatement, ResultSet myResultset) {
        try {
            if (myResultset != null) {
                myResultset.close();
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

    public void addPlateVCode(PlateVCode thePlateVCode) throws Exception {

        Connection myConnection = null;
        PreparedStatement myStatement = null;
        try {
            myConnection = dataSource.getConnection();
            String sql = "insert into plate_v_code "
                    + "(plate_v_code_title, plate_v_code_description) "
                    + "values (?, ?)";
            myStatement = myConnection.prepareStatement(sql);

            myStatement.setString(1, thePlateVCode.getPlateVCodeTitle());
            myStatement.setString(2, thePlateVCode.getPlateVCodeDescription());
            myStatement.execute();
        }
        finally {
            close(myConnection, myStatement, null);
        }
    }

    public PlateVCode getPlateVCode(String thePlateVCodeId) throws Exception {
        PlateVCode thePlateVCode = null;
        Connection myConnection = null;
        PreparedStatement myPreparedStatement = null;
        ResultSet myResultSet = null;
        long plateVCodeId;

        try {
            plateVCodeId = Integer.parseInt(thePlateVCodeId);
            myConnection = dataSource.getConnection();
            String sql = "select * from plate_v_code where plate_v_code_id=?";
            myPreparedStatement = myConnection.prepareStatement(sql);
            myPreparedStatement.setLong(1, plateVCodeId);
            myResultSet = myPreparedStatement.executeQuery();
            if (myResultSet.next()) {
                String plateVCodeTitle = myResultSet.getString("plate_v_code_title");
                String plateVCodeDescription = myResultSet.getString("plate_v_code_description");
                thePlateVCode = new PlateVCode(plateVCodeId, plateVCodeTitle, plateVCodeDescription);
            }
            else {
                throw new Exception("Could not find plate vendor code id: " + plateVCodeId);
            }
            return thePlateVCode;
        }
        finally {
            close(myConnection, myPreparedStatement, myResultSet);
        }
    }

    public void updatePlateVCode(PlateVCode thePlateVCode) throws Exception {
        Connection myConnection = null;
        PreparedStatement myPreparedSatement = null;
        try {
            myConnection = dataSource.getConnection();
            String sql = "update plate_v_code "
                    + "set plate_v_code_title=?, plate_v_code_description=? "
                    + "where plate_v_code_id=?";

            myPreparedSatement = myConnection.prepareStatement(sql);
            myPreparedSatement.setString(1, thePlateVCode.getPlateVCodeTitle());
            myPreparedSatement.setString(2, thePlateVCode.getPlateVCodeDescription());
            myPreparedSatement.execute();
        }
        finally {
            close(myConnection, myPreparedSatement, null);
        }
    }

    public void deletePlateVCode(String theStudentId) throws Exception {
        Connection myConnection = null;
        PreparedStatement myPreparedStatement = null;
        try {
            long plateVCodeId = Long.parseLong(theStudentId);
            myConnection = dataSource.getConnection();
            String sql = "delete from plate_v_code where plate_v_code_id=?";
            myPreparedStatement = myConnection.prepareStatement(sql);
            myPreparedStatement.setLong(1, plateVCodeId);
            myPreparedStatement.execute();
        }
        finally {
            close(myConnection, myPreparedStatement, null);
        }
    }
}
