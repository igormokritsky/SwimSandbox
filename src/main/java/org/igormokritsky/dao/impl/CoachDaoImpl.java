package org.igormokritsky.dao.impl;


import org.apache.log4j.Logger;
import org.igormokritsky.ConnectionHolder;
import org.igormokritsky.DAOException;
import org.igormokritsky.DBUtils;
import org.igormokritsky.entity.Coach;
import org.igormokritsky.dao.CoachesDao;

import java.sql.*;
import java.sql.SQLException;


public class CoachDaoImpl implements CoachesDao {


    private static CoachDaoImpl coachDao;
    private Connection connection;

    private static final Logger LOG = Logger.getLogger(CoachDaoImpl.class);
    private static final String insert = "INSERT INTO coaches" + "(id, name, awards, country_id, user_id) VALUES" +
            "(?,?,?,?,?);";

    private static final String update = "UPDATE coaches SET name=?, awards=? WHERE id=?";


    static CoachesDao getInstance() {
        if(coachDao == null)
            coachDao = new CoachDaoImpl();
            return coachDao;
    }

    public CoachDaoImpl() {

    }

    @Override
    public Integer create(Coach coach) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = ConnectionHolder.getConnection();
            preparedStatement = connection.prepareStatement(insert);
            connection.setAutoCommit(false);

            preparedStatement.setInt(1,coach.getId());
            preparedStatement.setString(2,coach.getName());
            preparedStatement.setString(3,coach.getAwards());
            preparedStatement.setInt(4,coach.getCountry_id());
            preparedStatement.setInt(5,coach.getUser_id());
            preparedStatement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            LOG.error("Can not read", e);
            throw new DAOException(e.getMessage(), e);
        } finally {
            DBUtils.closeStatement(preparedStatement);
            DBUtils.closeConnection(connection);
        }
        return null;
    }

    @Override
    public Coach read(Integer id) throws DAOException {
        Connection connection;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionHolder.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM coaches WHERE id=" + id);
            if(resultSet.next()){
                Coach coach = new Coach();
                coach.setId(resultSet.getInt("id"));
                coach.setName(resultSet.getString("name"));
                coach.setAwards(resultSet.getString("awards"));
                return coach;
            }

        } catch (SQLException e) {
            LOG.error("Can not read", e);
            throw new DAOException(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public boolean update(Coach coach) throws DAOException {
        Connection connection;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionHolder.getConnection();
            preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1, coach.getName());
            preparedStatement.setString(2,coach.getAwards());
            preparedStatement.setInt(3, coach.getId());
            int i = preparedStatement.executeUpdate();
            if (i == 1){
                return true;
            }
        }catch (SQLException e) {
            LOG.error("Can not read", e);
            throw new DAOException(e.getMessage(), e);
        }
        return false;
    }

    @Override
    public boolean delete(Integer id) throws DAOException {
        Connection connection;
        Statement statement = null;
        try {
            connection = ConnectionHolder.getConnection();
            statement = connection.createStatement();
            int i = statement.executeUpdate("DELETE FROM coaches WHERE id=" + id);

            if (i == 1){
                return true;
            }
        }catch (SQLException e) {
            LOG.error("Can not read", e);
            throw new DAOException(e.getMessage(), e);
        }
        return false;
    }
}
