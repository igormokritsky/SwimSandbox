package org.igormokritsky.dao.impl;

import  org.apache.log4j.Logger;
import org.igormokritsky.db.ConnectionHolder;
import org.igormokritsky.db.DAOException;
import org.igormokritsky.dao.StylesDao;
import org.igormokritsky.entity.Style;
import java.sql.*;

public class StyleDaoImpl implements StylesDao {

    private static StyleDaoImpl styleDao;
    private static final Logger LOG = Logger.getLogger(StyleDaoImpl.class);
    private static final String INSERT = "INSERT INTO styles (id, style_name) VALUES (?,?);";
    private static final String UPDATE = "UPDATE styles SET style_name=? WHERE id=?";
    private static final String READ = "SELECT * FROM styles WHERE id=";
    private static final String DELETE = "DELETE FROM styles WHERE id=";


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
            preparedStatement = connection.prepareStatement(INSERT);
            connection.setAutoCommit(false);

            preparedStatement.setInt(1,style.getId());
            preparedStatement.setString(2,style.getStyleName());

            connection.commit();

        }catch (SQLException e) {
            LOG.error("Can not create", e);
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
            resultSet = statement.executeQuery(READ + id);
            connection.setAutoCommit(false);
            if(resultSet.next()){
                Style style = new Style();
                style.setId(resultSet.getInt("id"));
                style.setStyleName(resultSet.getString("style_name"));
            }
            connection.commit();
        }catch (SQLException e) {
            LOG.error("Can not read", e);
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
            preparedStatement = connection.prepareStatement(UPDATE);
            connection.setAutoCommit(false);

            preparedStatement.setString(1,style.getStyleName());
            preparedStatement.setInt(2,style.getId());

            int i = preparedStatement.executeUpdate();
            if (i == 1){
                return true;
            }
            connection.commit();
        }catch (SQLException e) {
            LOG.error("Can not update", e);
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
            int i = statement.executeUpdate(DELETE + id);

            if (i == 1){
                return true;
            }
        }catch (SQLException e) {
            LOG.error("Can not delete", e);
            throw new DAOException(e.getMessage(), e);
        }
        return false;
    }
}
