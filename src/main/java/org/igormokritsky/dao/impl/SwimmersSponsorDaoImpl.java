package org.igormokritsky.dao.impl;

import org.apache.log4j.Logger;
import org.igormokritsky.db.ConnectionHolder;
import org.igormokritsky.db.DAOException;
import org.igormokritsky.dao.SwimmerSponsorsDao;
import org.igormokritsky.entity.SwimmersSponsor;
import java.sql.*;

public class SwimmersSponsorDaoImpl implements SwimmerSponsorsDao {

    private static SwimmersSponsorDaoImpl swimmersSponsorDao;
    private static final Logger LOG = Logger.getLogger(SwimmersSponsorDaoImpl.class);
    private static final String INSERT = "INSERT INTO swimmers_sponsors" +
            "(id, swimmer_id, sponsor_id, contract_amount) VALUES (?,?,?,?);";
    private static final String UPDATE =
            "UPDATE swimmers_sponsors SET swimmer_id=?, sponsor_id=?, contract_amount=? WHERE id=?";
    private static final String READ = "SELECT * FROM swimmers_sponsors WHERE id=";
    private static final String DELETE = "DELETE FROM swimmers_sponsors WHERE id=";


    static SwimmerSponsorsDao getInstance() {
        if(swimmersSponsorDao == null) swimmersSponsorDao = new SwimmersSponsorDaoImpl();
        return swimmersSponsorDao;
    }
    private SwimmersSponsorDaoImpl() {
    }

    @Override
    public Integer create(SwimmersSponsor swimmersSponsor) throws DAOException {
        Connection connection;
        PreparedStatement preparedStatement = null;
        try{
            connection = ConnectionHolder.getConnection();
            preparedStatement = connection.prepareStatement(INSERT);
            connection.setAutoCommit(false);

            preparedStatement.setInt(1, swimmersSponsor.getId());
            preparedStatement.setInt(2, swimmersSponsor.getSwimmerId());
            preparedStatement.setInt(3, swimmersSponsor.getSponsorId());
            preparedStatement.setInt(4, swimmersSponsor.getContractAmount());

            connection.commit();
        }catch (SQLException e) {
            LOG.error("Can not create", e);
            throw new DAOException(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public SwimmersSponsor read(Integer id) throws DAOException {
        Connection connection;
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            connection = ConnectionHolder.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(READ + id);
            connection.setAutoCommit(false);
            if(resultSet.next()){
                SwimmersSponsor swimmersSponsor = new SwimmersSponsor();
                swimmersSponsor.setId(resultSet.getInt("id"));
                swimmersSponsor.setSwimmerId(resultSet.getInt("swimmer_id"));
                swimmersSponsor.setSponsorId(resultSet.getInt("sponsor_id"));
                swimmersSponsor.setContractAmount(resultSet.getInt("contract_amount"));
            }

            connection.commit();

        } catch (SQLException e) {
            LOG.error("Can not read", e);
            throw new DAOException(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public boolean update(SwimmersSponsor swimmersSponsor) throws DAOException {
        Connection connection;
        PreparedStatement preparedStatement = null;
        try{
            connection = ConnectionHolder.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE);
            connection.setAutoCommit(false);

            preparedStatement.setInt(1, swimmersSponsor.getSwimmerId());
            preparedStatement.setInt(2, swimmersSponsor.getSponsorId());
            preparedStatement.setInt(3, swimmersSponsor.getContractAmount());
            preparedStatement.setInt(4, swimmersSponsor.getId());

            int i = preparedStatement.executeUpdate();
            if (i == 1){
                return true;
            }
            connection.commit();
        } catch (SQLException e) {
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
        } catch (SQLException e) {
            LOG.error("Can not delete", e);
            throw new DAOException(e.getMessage(), e);
        }
        return false;
    }
}
