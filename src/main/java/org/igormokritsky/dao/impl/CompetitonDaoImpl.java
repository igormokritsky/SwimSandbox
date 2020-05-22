package org.igormokritsky.dao.impl;

import org.apache.log4j.Logger;
import org.igormokritsky.db.ConnectionHolder;
import org.igormokritsky.db.DBUtils;
import org.igormokritsky.db.DAOException;
import org.igormokritsky.entity.Competition;
import org.igormokritsky.dao.CompetitionsDao;

import java.sql.*;

public class CompetitonDaoImpl implements CompetitionsDao {

    private static CompetitonDaoImpl competitonDao;
    private static final Logger LOG = Logger.getLogger(CompetitonDaoImpl.class);
    private static final String INSERT = "INSERT INTO competitions (id, county_id, style_id, distance) VALUES (?,?,?,?);";
    private static final String UPDATE = "UPDATE competitions SET county_id=?, style_id=?, distance=? WHERE id=?";
    private static final String READ = "SELECT * FROM competitions WHERE id=";
    private static final String DELETE = "DELETE FROM competitions WHERE id=";


    static CompetitionsDao getInstance() {
        if (competitonDao == null) competitonDao = new CompetitonDaoImpl();
        return competitonDao;
    }


    private CompetitonDaoImpl() {
    }

    @Override
    public Integer create(Competition competition) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionHolder.getConnection();
            preparedStatement = connection.prepareStatement(INSERT);
            connection.setAutoCommit(false);

            preparedStatement.setInt(1,competition.getId());
            preparedStatement.setInt(2,competition.getCountryId());
            preparedStatement.setInt(3,competition.getStyleId());
            preparedStatement.setInt(4,competition.getDistance());

            connection.commit();

        }catch (SQLException e) {
            LOG.error("Can not create", e);
            throw new DAOException(e.getMessage(), e);
        } finally {
            DBUtils.rollback(connection);
        }
        return null;
    }

    @Override
    public Competition read(Integer id) throws DAOException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionHolder.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(READ + id);
            connection.setAutoCommit(false);
            if(resultSet.next()){
                Competition competition = new Competition();
                competition.setId(resultSet.getInt("id"));
                competition.setCountryId(resultSet.getInt("country_id"));
                competition.setStyleId(resultSet.getInt("style_id"));
            }
            connection.commit();
        }catch (SQLException e) {
            LOG.error("Can not read", e);
            throw new DAOException(e.getMessage(), e);
        } finally {

        }
        return null;
    }

    @Override
    public boolean update(Competition competition) throws DAOException {
        Connection connection;
        PreparedStatement preparedStatement = null;
        try{
            connection = ConnectionHolder.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE);
            connection.setAutoCommit(false);

            preparedStatement.setInt(1,competition.getCountryId());
            preparedStatement.setInt(2,competition.getStyleId());
            preparedStatement.setInt(3,competition.getDistance());
            preparedStatement.setInt(4,competition.getId());

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
