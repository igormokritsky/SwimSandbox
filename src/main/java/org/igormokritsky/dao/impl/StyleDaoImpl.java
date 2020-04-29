package org.igormokritsky.dao.impl;

import org.igormokritsky.ConnectionHolder;
import org.igormokritsky.DAOException;
import org.igormokritsky.dao.StylesDao;
import org.igormokritsky.entity.Style;
import java.sql.*;

public class StyleDaoImpl implements StylesDao {

    private static StyleDaoImpl styleDao;

    final private static String insert = "INSERT INTO styles" + "(id, style_name) VALUES (?,?);";
    final private static String update = "UPDATE styles SET style_name=? WHERE id=?";

    public static void main(String[] args) {

    }


    static StylesDao getInstance(){
        if(styleDao == null) styleDao = new StyleDaoImpl();
        return styleDao;
    }
    private StyleDaoImpl() {
    }

    @Override
    public Integer create(Style style) throws DAOException {
        Connection connection;
        PreparedStatement preparedStatement = null;
        try{
            connection = ConnectionHolder.getConnection();
            preparedStatement = connection.prepareStatement(insert);
            connection.setAutoCommit(false);

            preparedStatement.setInt(1,style.getId());
            preparedStatement.setString(2,style.getStyle_name());

            connection.commit();

        }catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public Style read(Integer id) throws DAOException {
        Connection connection;
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            connection = ConnectionHolder.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM styles WHERE id=" + id);
            connection.setAutoCommit(false);
            if(resultSet.next()){
                Style style = new Style();
                style.setId(resultSet.getInt("id"));
                style.setStyle_name(resultSet.getString("style_name"));
            }
            connection.commit();
        }catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public boolean update(Style style) throws DAOException {
        Connection connection;
        PreparedStatement preparedStatement = null;
        try{
            connection = ConnectionHolder.getConnection();
            preparedStatement = connection.prepareStatement(update);
            connection.setAutoCommit(false);

            preparedStatement.setString(1,style.getStyle_name());
            preparedStatement.setInt(2,style.getId());

            int i = preparedStatement.executeUpdate();
            if (i == 1){
                return true;
            }
            connection.commit();
        }catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
        return false;
    }

    @Override
    public boolean delete(Integer id) throws DAOException {
        Connection connection;
        Statement statement = null;
        try{
            connection = ConnectionHolder.getConnection();
            statement = connection.createStatement();
            int i = statement.executeUpdate("DELETE FROM styles WHERE id=" + id);

            if (i == 1){
                return true;
            }
        }catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
        return false;
    }
}
