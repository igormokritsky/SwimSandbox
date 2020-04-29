package org.igormokritsky;

import java.sql.*;
import java.util.logging.*;


public class DBUtils {


    public static void rollback(Connection connection){
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    public static void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {

                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
