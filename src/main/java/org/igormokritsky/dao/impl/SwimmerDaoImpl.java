package org.igormokritsky.dao.impl;

import org.apache.log4j.Logger;
import org.igormokritsky.db.ConnectionHolder;
import org.igormokritsky.db.DAOException;
import org.igormokritsky.dao.SwimmersDao;
import org.igormokritsky.entity.Swimmer;
import java.sql.*;

public class SwimmerDaoImpl implements SwimmersDao {

    private static SwimmerDaoImpl swimmerDao;

    private static final Logger LOG = Logger.getLogger(SwimmerDaoImpl.class);
    private static final String INSERT = "INSERT INTO swimmers" +
            "(id, name, age, sex, weight, height, user_id, country_id, coach_id) VALUES" +
            "(?,?,?,?,?,?,?,?,?);";
    private static final String UPDATE = "UPDATE swimmers SET name=?, age=?, sex=?, weight=?, height=? WHERE id=?";
    private static final String READ = "SELECT * FROM swimmers WHERE id=";
    private static final String DELETE = "DELETE FROM swimmers WHERE id=";



    static SwimmersDao getInstance() {
        if(swimmerDao == null) swimmerDao = new SwimmerDaoImpl();
        return swimmerDao;
    }
    private SwimmerDaoImpl() {
    }

    @Override
    public Integer create(Swimmer swimmer) throws DAOException {
        Connection connection;
        PreparedStatement preparedStatement = null;
        try{
            connection = ConnectionHolder.getConnection();
            preparedStatement = connection.prepareStatement(INSERT);
            connection.setAutoCommit(false);
            preparedStatement.setInt(1,swimmer.getId());
            preparedStatement.setString(2,swimmer.getName());
            preparedStatement.setInt(3,swimmer.getAge());
            preparedStatement.setString(4,swimmer.getSex());
            preparedStatement.setInt(5,swimmer.getWeight());
            preparedStatement.setInt(6,swimmer.getHeight());
            preparedStatement.setInt(7,swimmer.getUserId());
            preparedStatement.setInt(8,swimmer.getCountryId());
            preparedStatement.setInt(9,swimmer.getCoachId());

            connection.commit();

        } catch (SQLException e) {
            LOG.error("Can not create", e);
            throw new DAOException(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public Swimmer read(Integer id) throws DAOException {
        Connection connection;
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            connection = ConnectionHolder.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(READ + id);
            connection.setAutoCommit(false);
            if(resultSet.next()){
                Swimmer swimmer = new Swimmer();
                swimmer.setId(resultSet.getInt("id"));
                swimmer.setName(resultSet.getString("name"));
                swimmer.setAge(resultSet.getInt("age"));
                swimmer.setSex(resultSet.getString("sex"));
                swimmer.setWeight(resultSet.getInt("weight"));
                swimmer.setHeight(resultSet.getInt("height"));
                return swimmer;
            }
            connection.commit();

        }catch (SQLException e) {
            LOG.error("Can not read", e);
            throw new DAOException(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public boolean update(Swimmer swimmer) throws DAOException {
        Connection connection;
        PreparedStatement preparedStatement = null;
        try{
            connection = ConnectionHolder.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1,swimmer.getName());
            preparedStatement.setInt(2,swimmer.getAge());
            preparedStatement.setString(3,swimmer.getSex());
            preparedStatement.setInt(4,swimmer.getWeight());
            preparedStatement.setInt(5,swimmer.getHeight());
            preparedStatement.setInt(6,swimmer.getId());
            int i = preparedStatement.executeUpdate();
            if (i == 1){
                return true;
            }

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
