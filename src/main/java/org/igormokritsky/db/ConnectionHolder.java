package org.igormokritsky.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.commons.dbcp.BasicDataSource;

public class ConnectionHolder {

    private static final String userName = "root";
    private static final String password = "Jummetmokai2145";
    private static final String connectionURL =
            "jdbc:mysql://localhost:3306/swimmers_schema?useUnicode=true&serverTimezone=UTC&autoReconnect=true&useSSL=false";


    private static final ThreadLocal<Connection> connectionHolder = new ThreadLocal<>();

    public ConnectionHolder() {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return basicDataSource.getConnection();
    }


    public static void setConnection(Connection connection){
        connectionHolder.set(connection);
    }

    private static final BasicDataSource basicDataSource = new BasicDataSource();
    static {
        basicDataSource.setUrl(connectionURL);
        basicDataSource.setUsername(userName);
        basicDataSource.setPassword(password);
        basicDataSource.setMinIdle(5);
        basicDataSource.setMaxIdle(100);
        basicDataSource.setMaxOpenPreparedStatements(1000);
    }

}
