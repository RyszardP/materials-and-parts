package com.ryszard.materialsandparts.dao.utils;

import com.ryszard.materialsandparts.domain.to.PlateSizes;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PlateSizesDbUtil {

    private DataSource dataSource;

    public PlateSizesDbUtil(DataSource theDataSource) {
        dataSource = theDataSource;
    }

    public List<PlateSizes> getPlateSizes() throws Exception {
        List<PlateSizes> plateSizes = new ArrayList<>();

        Connection myConnection = null;
        Statement myStatement = null;
        ResultSet myResultSet = null;
        try {
            myConnection = dataSource.getConnection();
            String sql = "select * from plate_sizes order by plate_length";
            myStatement = myConnection.createStatement();
            myResultSet = myStatement.executeQuery(sql);
            while (myResultSet.next()) {

                int plateSizeId = myResultSet.getInt("plate_size_id");
                int plateLength = myResultSet.getInt("plate_length");
                int plateWidth = myResultSet.getInt("plate_width");

                PlateSizes tempPlateSizes = new PlateSizes(plateSizeId, plateLength, plateWidth);
                plateSizes.add(tempPlateSizes);
            }
            return plateSizes;
        } finally {

            close(myConnection, myStatement, myResultSet);
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

    public void addPlateSizes(PlateSizes thePlateSizes) throws Exception {
        Connection myConnection = null;
        PreparedStatement myStatement = null;

        try {
            myConnection = dataSource.getConnection();
            String sql = "insert into plate_sizes "
                    + "(plate_length, plate_width) "
                    + "values (?, ?)";
            myStatement = myConnection.prepareStatement(sql);
            myStatement.setString(1, String.valueOf(thePlateSizes.getPlateLength()));
            myStatement.setString(2, String.valueOf(thePlateSizes.getPlateLength()));
            myStatement.execute();
        } finally {
            close(myConnection, myStatement, null);
        }
    }

    public PlateSizes getPlateSizes(String thePlateSizesId) throws Exception {

        PlateSizes thePlateSizes = null;
        Connection myConnection = null;
        PreparedStatement myPreparedStatement = null;
        ResultSet myResultSet = null;
        int plateSizesId;

        try {
            plateSizesId = Integer.parseInt(thePlateSizesId);
            myConnection = dataSource.getConnection();
            String sql = "select * from plate_sizes where plate_sizes_id=?";
            myPreparedStatement = myConnection.prepareStatement(sql);
            myPreparedStatement.setInt(1, plateSizesId);
            myResultSet = myPreparedStatement.executeQuery();
            if (myResultSet.next()) {
                int plateLength = Integer.parseInt(myResultSet.getString("plate_length"));
                int plateWidth = Integer.parseInt(myResultSet.getString("plate-width"));

                thePlateSizes = new PlateSizes(plateSizesId, plateLength, plateWidth);
            } else {
                throw new Exception("Could not find plate sizes id: " + plateSizesId);
            }
            return thePlateSizes;
        } finally {
            close(myConnection, myPreparedStatement, myResultSet);
        }
    }

    public void updatePlateSizes(PlateSizes thePlateSizes) throws Exception {
        Connection myConnection = null;
        PreparedStatement myStatement = null;
        try {
            myConnection = dataSource.getConnection();
            String sql = "update plate_sizes "
                    + "set plate_length=?, plate_width=? "
                    + "where plate_sizes_id=?";
            myStatement = myConnection.prepareStatement(sql);
            myStatement.setString(1, String.valueOf(thePlateSizes.getPlateLength()));
            myStatement.setString(2, String.valueOf(thePlateSizes.getPlateWidth()));
            myStatement.execute();
        } finally {
            close(myConnection, myStatement, null);
        }
    }

    public void deletePlateSizes(String thePlateSizesId) throws Exception {
        Connection myConnection = null;
        PreparedStatement myStatement = null;
        try {
            int plateId = Integer.parseInt(thePlateSizesId);
            myConnection = dataSource.getConnection();
            String sql = "delete from plate_sizes where plate_sizes_id=?";
            myStatement = myConnection.prepareStatement(sql);
            myStatement.setInt(1, plateId);
            myStatement.execute();
        } finally {
            close(myConnection, myStatement, null);
        }
    }
}

