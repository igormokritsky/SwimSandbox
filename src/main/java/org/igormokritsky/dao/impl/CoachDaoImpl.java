package org.igormokritsky.dao.impl;


import org.apache.log4j.Logger;
import org.igormokritsky.db.ConnectionHolder;
import org.igormokritsky.db.DBUtils;
import org.igormokritsky.db.DAOException;
import org.igormokritsky.entity.Coach;
import org.igormokritsky.dao.CoachDao;
import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CoachDaoImpl implements CoachDao {


    private static CoachDaoImpl coachDaoImpl;

    private static final Logger LOG = Logger.getLogger(CoachDaoImpl.class);
    private static final String SELECT_ALL = "SELECT * FROM swimmers_schema.coaches";
    private static final String INSERT = "INSERT INTO swimmers_schema.coaches" +
            "(name, awards, country_id, user_id) VALUES" + "(?,?,?,?);";
    private static final String UPDATE = "UPDATE coaches SET name=?, awards=?, country_id=?, user_id=? WHERE id=?";
    private static final String READ = "SELECT * FROM swimmers_schema.coaches WHERE id=";
    private static final String DELETE = "DELETE FROM swimmers_schema.coaches WHERE id=";


    public static CoachDao getInstance() {
        if(coachDaoImpl == null)
            coachDaoImpl = new CoachDaoImpl();
            return coachDaoImpl;
    }

    private CoachDaoImpl() {

    }


    public List<Coach> selectAll() throws DAOException {
        List<Coach> coaches = new ArrayList<Coach>();
        try(Connection conn = ConnectionHolder.getConnection()) {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);
            while (resultSet.next()) {
                Coach coach = new Coach();
                coach.setId(resultSet.getInt("id"));
                coach.setName(resultSet.getString("name"));
                coach.setAwards(resultSet.getString("awards"));
                coach.setCountryId(resultSet.getInt("country_id"));
                coach.setUserId(resultSet.getInt("user_id"));
                coaches.add(coach);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return coaches;
    }

    @Override
    public Integer create(Coach coach) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = ConnectionHolder.getConnection();
            preparedStatement = connection.prepareStatement(INSERT);
            connection.setAutoCommit(false);
            preparedStatement.setString(1,coach.getName());
            preparedStatement.setString(2,coach.getAwards());
            preparedStatement.setInt(3,coach.getCountryId());
            preparedStatement.setInt(4,coach.getUserId());
            preparedStatement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            LOG.error("Can not create", e);
            throw new DAOException(e.getMessage(), e);
        } finally {
            DBUtils.closeStatement(preparedStatement);
            DBUtils.closeConnection(connection);
        }
        return null;
    }

    @Override
    public Coach read(Integer id) throws DAOException {
        Coach coach = new Coach();
        Connection connection;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionHolder.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(READ + id);
            if(resultSet.next()){
                coach.setId(resultSet.getInt("id"));
                coach.setName(resultSet.getString("name"));
                coach.setAwards(resultSet.getString("awards"));
                coach.setCountryId(resultSet.getInt("country_id"));
                coach.setUserId(resultSet.getInt("user_id"));
            }

        } catch (SQLException e) {
            LOG.error("Can not read", e);
            throw new DAOException(e.getMessage(), e);
        }
        return coach;
    }

    @Override
    public boolean update(Coach coach) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionHolder.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, coach.getName());
            preparedStatement.setString(2,coach.getAwards());
            preparedStatement.setInt(3,coach.getCountryId());
            preparedStatement.setInt(4,coach.getUserId());
            preparedStatement.setInt(5, coach.getId());

            int i = preparedStatement.executeUpdate();
            if (i == 1){
                return true;
            }
        }catch (SQLException e) {
            LOG.error("Can not update", e);
            throw new DAOException(e.getMessage(), e);
        } finally {
            DBUtils.closeStatement(preparedStatement);
            DBUtils.closeConnection(connection);
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
