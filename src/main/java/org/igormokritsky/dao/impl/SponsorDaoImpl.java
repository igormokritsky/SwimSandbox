package org.igormokritsky.dao.impl;

import org.apache.log4j.Logger;
import org.igormokritsky.db.ConnectionHolder;
import org.igormokritsky.db.DAOException;
import org.igormokritsky.dao.SponsorsDao;
import org.igormokritsky.entity.Sponsor;
import java.sql.*;

public class SponsorDaoImpl implements SponsorsDao {

    private static SponsorDaoImpl sponsorDao;
    private static final Logger LOG = Logger.getLogger(SponsorDaoImpl.class);
    private static final String INSERT = "INSERT INTO sponsors (id, name) VALUES (?,?);";
    private static final String UPDATE = "UPDATE sponsors SET name=? WHERE id=?";
    private static final String READ = "SELECT * FROM sponsors WHERE id=";
    private static final String DELETE = "DELETE FROM sponsors WHERE id=";


    static SponsorsDao getInstance(){
        if(sponsorDao == null) sponsorDao = new SponsorDaoImpl();
        return sponsorDao;
    }
    private SponsorDaoImpl() {
    }

    @Override
    public Integer create(Sponsor sponsor) throws DAOException {
        Connection connection;
        PreparedStatement preparedStatement = null;
        try{
            connection = ConnectionHolder.getConnection();
            preparedStatement = connection.prepareStatement(INSERT);
            connection.setAutoCommit(false);

            preparedStatement.setInt(1,sponsor.getId());
            preparedStatement.setString(2,sponsor.getName());

            connection.commit();

        }catch (SQLException e) {
            LOG.error("Can not create", e);
            throw new DAOException(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public Sponsor read(Integer id) throws DAOException {
        Connection connection;
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            connection = ConnectionHolder.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(READ + id);
            connection.setAutoCommit(false);
            if(resultSet.next()){
                Sponsor sponsor = new Sponsor();
                sponsor.setId(resultSet.getInt("id"));
                sponsor.setName(resultSet.getString("name"));
            }
            connection.commit();
        } catch (SQLException e) {
            LOG.error("Can not read", e);
            throw new DAOException(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public boolean update(Sponsor sponsor) throws DAOException {
        Connection connection;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionHolder.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE);
            connection.setAutoCommit(false);

            preparedStatement.setString(1,sponsor.getName());
            preparedStatement.setInt(2,sponsor.getId());

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

            if (i == 1) {
                return true;
            }
        }catch (SQLException e) {
            LOG.error("Can not delete", e);
            throw new DAOException(e.getMessage(), e);
        }
        return false;
    }
}
