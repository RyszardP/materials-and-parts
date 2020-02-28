package com.ryszard.materialsandparts.dao.utils;

import com.ryszard.materialsandparts.domain.to.Thickness;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ThicknessDbUtil {
    public DataSource dataSource;

    public ThicknessDbUtil(DataSource theDataSource) {
        dataSource = theDataSource;
    }

    public List<Thickness> getThicknesses() throws Exception {
        List<Thickness> thicknesses = new ArrayList<>();
        Connection myConnection = null;
        Statement myStatement = null;
        ResultSet myResultSet = null;
        try {
            myConnection = dataSource.getConnection();
            String sql = "select * from thickness order by thickness_size";
            myStatement = myConnection.createStatement();
            myResultSet = myStatement.executeQuery(sql);
            while (myResultSet.next()) {
                long thicknessId = myResultSet.getLong("thickness_id");
                int thicknessSize = myResultSet.getInt("thickness_size");
                Thickness tempThickness = new Thickness(thicknessId, thicknessSize);
                thicknesses.add(tempThickness);
            }
            return thicknesses;
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

    public void addThickness(Thickness thethickness) throws Exception {
        Connection myConnection = null;
        PreparedStatement myPreparedStatement = null;
        try {
            myConnection = dataSource.getConnection();
            String sql = "insert into thickness "
                    + "(thickness_size) values (?)";
            myPreparedStatement = myConnection.prepareStatement(sql);
            myPreparedStatement.setString(1, String.valueOf(thethickness.getThicknessSize()));
            myPreparedStatement.execute();
        } finally {
            close(myConnection, myPreparedStatement, null);
        }
    }

    public Thickness getThickness(String theThicknessId) throws Exception {
        Thickness theThickness = null;
        Connection myConnection = null;
        PreparedStatement myPreparedStatement = null;
        ResultSet myResultSet = null;
        long thicknessId;

        try {
            thicknessId = Long.parseLong(theThicknessId);
            myConnection = dataSource.getConnection();
            String sql = "select * from thickness where thickness_id=?";
            myPreparedStatement = myConnection.prepareStatement(sql);
            myPreparedStatement.setLong(1, thicknessId);
            myResultSet = myPreparedStatement.executeQuery();
            if (myResultSet.next()) {
                int thicknessSize = myResultSet.getInt("thickness_size");

                theThickness = new Thickness(thicknessId, thicknessSize);
            } else {
                throw new Exception("Could not find thickness id: " + thicknessId);
            }
            return theThickness;
        } finally {

            close(myConnection, myPreparedStatement, myResultSet);
        }
    }

    public void updateThickness(Thickness theThickness) throws Exception {
        Connection myConnection = null;
        PreparedStatement myPreparedStatement = null;
        try {
            myConnection = dataSource.getConnection();
            String sql = "update thickness set thickness_size=? where thickness_id=?";
            myPreparedStatement = myConnection.prepareStatement(sql);
            myPreparedStatement.setString(1, String.valueOf(theThickness.getThicknessSize()));
            myPreparedStatement.execute();
        } finally {
            close(myConnection, myPreparedStatement, null);
        }
    }

    public void deleteThickness(String theThicknessId) throws Exception {
        Connection myConnection = null;
        PreparedStatement myPreparedStatement = null;
        try {
            int thicknessId = Integer.parseInt(theThicknessId);
            myConnection = dataSource.getConnection();
            String sql = "delete from thickness where thickness_id=?";
            myPreparedStatement = myConnection.prepareStatement(sql);
            myPreparedStatement.setInt(1, thicknessId);
            myPreparedStatement.execute();
        } finally {
            close(myConnection, myPreparedStatement, null);
        }
    }
}
