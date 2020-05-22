package org.igormokritsky.dao.impl;

import org.apache.log4j.Logger;
import org.igormokritsky.db.ConnectionHolder;
import org.igormokritsky.db.DAOException;
import org.igormokritsky.dao.SwimCompetsDao;
import org.igormokritsky.entity.SwimCompet;
import java.sql.*;

public class SwimCompetDaoImpl implements SwimCompetsDao {

    private static SwimCompetDaoImpl swimCompetDao;
    private static final Logger LOG = Logger.getLogger(SwimCompetDaoImpl.class);
    private static final String INSERT = "INSERT INTO swim_compet (id, competition_id, swimmer_id, time) VALUES (?,?,?,?);";
    private static final String UPDATE = "UPDATE swim_compet SET competition_id=?, swimmer_id=?, time=? WHERE id=?";
    private static final String READ = "SELECT * FROM swim_compet WHERE id=";
    private static final String DELETE = "DELETE FROM swim_compet WHERE id=";



    static SwimCompetsDao getInstance() {
        if(swimCompetDao == null) swimCompetDao = new SwimCompetDaoImpl();
        return swimCompetDao;
    }
    private SwimCompetDaoImpl() {
    }

    @Override
    public Integer create(SwimCompet swimCompet) throws DAOException {
        Connection connection;
        PreparedStatement preparedStatement = null;
        try{
            connection = ConnectionHolder.getConnection();
            preparedStatement = connection.prepareStatement(INSERT);

            connection.setAutoCommit(false);
            preparedStatement.setInt(1,swimCompet.getId());
            preparedStatement.setInt(2,swimCompet.getCompetitionId());
            preparedStatement.setInt(3,swimCompet.getSwimmerId());
            preparedStatement.setInt(4,swimCompet.getTime());

            connection.commit();
        }catch (SQLException e) {
            LOG.error("Can not create", e);
            throw new DAOException(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public SwimCompet read(Integer id) throws DAOException {
        Connection connection;
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            connection = ConnectionHolder.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(READ + id);
            connection.setAutoCommit(false);
            if(resultSet.next()){
                SwimCompet swimCompet = new SwimCompet();
                swimCompet.setId(resultSet.getInt("id"));
                swimCompet.setCompetitionId(resultSet.getInt("competition_id"));
                swimCompet.setSwimmerId(resultSet.getInt("swimmer_id"));
                swimCompet.setTime(resultSet.getInt("time"));
            }
            connection.commit();

        }catch (SQLException e) {
            LOG.error("Can not read", e);
            throw new DAOException(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public boolean update(SwimCompet swimCompet) throws DAOException {
        Connection connection;
        PreparedStatement preparedStatement = null;

        try{
            connection = ConnectionHolder.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE);
            connection.setAutoCommit(false);
            preparedStatement.setInt(1,swimCompet.getCompetitionId());
            preparedStatement.setInt(2,swimCompet.getSwimmerId());
            preparedStatement.setInt(3,swimCompet.getTime());
            preparedStatement.setInt(4,swimCompet.getId());

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
        } catch (SQLException e){
            LOG.error("Can not delete", e);
            throw new DAOException(e.getMessage(), e);
        }
        return false;
    }
}
