package org.igormokritsky.dao.impl;

import org.apache.log4j.Logger;
import org.igormokritsky.db.ConnectionHolder;
import org.igormokritsky.db.DAOException;
import org.igormokritsky.entity.Country;
import org.igormokritsky.dao.CountriesDao;

import java.sql.*;

public class CountryDaoImpl implements CountriesDao {

    private static CountryDaoImpl countryDao;
    private static final Logger LOG = Logger.getLogger(CountryDaoImpl.class);
    private static final String READ = "SELECT * FROM countries WHERE id=";
    private static final String INSERT = "INSERT INTO countries" + "(id, country_name) VALUES (?,?);";
    private static final String UPDATE = "UPDATE countries SET country_name=? WHERE id=?";
    private static final String DELETE = "DELETE FROM countries WHERE id=";



    static CountriesDao getInstance(){
        if(countryDao == null) countryDao = new CountryDaoImpl();
        return countryDao;
    }
    private CountryDaoImpl() {
    }

    @Override
    public Integer create(Country country) throws DAOException {
        Connection connection;
        PreparedStatement preparedStatement = null;
        try{
            connection = ConnectionHolder.getConnection();
            preparedStatement = connection.prepareStatement(INSERT);
            connection.setAutoCommit(false);

            preparedStatement.setInt(1,country.getId());
            preparedStatement.setString(2,country.getCountryName());

            connection.commit();

        }catch (SQLException e) {
            LOG.error("Can not create", e);
            throw new DAOException(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public Country read(Integer id) throws DAOException {
        Connection connection;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionHolder.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(READ + id);
            connection.setAutoCommit(false);
            if(resultSet.next()){
                Country country = new Country();
                country.setId(resultSet.getInt("id"));
                country.setCountryName(resultSet.getString("country_name"));
                return country;
            }
            connection.commit();
        }catch (SQLException e) {
            LOG.error("Can not read", e);
            throw new DAOException(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public boolean update(Country country) throws DAOException {
        Connection connection;
        PreparedStatement preparedStatement = null;
        try{
            connection = ConnectionHolder.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE);
            connection.setAutoCommit(false);

            preparedStatement.setString(1,country.getCountryName());
            preparedStatement.setInt(2,country.getId());

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
