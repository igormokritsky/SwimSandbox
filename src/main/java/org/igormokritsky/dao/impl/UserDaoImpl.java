package org.igormokritsky.dao.impl;


import org.apache.log4j.Logger;
import org.igormokritsky.db.ConnectionHolder;
import org.igormokritsky.db.DAOException;
import org.igormokritsky.dao.UsersDao;
import org.igormokritsky.entity.User;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UsersDao {

    private static UserDaoImpl userDao;
    private static final Logger LOG = Logger.getLogger(UserDaoImpl.class);
    private static final String INSERT = "INSERT INTO users (id, username, email, phone, password) VALUES" +
            "(?,?,?,?,?);";
    private static final String UPDATE = "UPDATE users SET username=?, email=?, phone=?, password=? WHERE id=?";
    private static final String READ = "SELECT * FROM users WHERE id=";
    private static final String DELETE = "DELETE FROM users WHERE id=";
    private static final String SELECT_ALL = "SELECT * FROM users";

    static UsersDao getInstance() {
        if(userDao == null) userDao = new UserDaoImpl();
        return userDao;
    }

    public UserDaoImpl() {
    }

    @Override
    public Integer create(User user) throws DAOException {
        Connection connection;
        PreparedStatement preparedStatement = null;
        try{
            connection = ConnectionHolder.getConnection();
            preparedStatement = connection.prepareStatement(INSERT);
            connection.setAutoCommit(false);

            preparedStatement.setInt(1,user.getId());
            preparedStatement.setString(2,user.getUsername());
            preparedStatement.setString(3,user.getEmail());
            preparedStatement.setInt(4, user.getPhone());
            preparedStatement.setString(5, user.getPassword());
            connection.commit();


        } catch (SQLException e) {
            LOG.error("Can not create", e);
            throw new DAOException(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public User read(Integer id) throws DAOException {
        Connection connection;
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            connection = ConnectionHolder.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(READ + id);
            if(resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPhone(resultSet.getInt("phone"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }
        } catch (SQLException e) {
            LOG.error("Can not read", e);
            throw new DAOException(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public boolean update(User user) throws DAOException {
        Connection connection;
        PreparedStatement preparedStatement = null;
        try{
            connection = ConnectionHolder.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1,user.getUsername());
            preparedStatement.setString(2,user.getEmail());
            preparedStatement.setInt(3, user.getPhone());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setInt(5,user.getId());

            int i = preparedStatement.executeUpdate();
            if (i == 1){
                return true;
            }

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

    public List <User> selectAllusers() {
        Connection connection;
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            connection = ConnectionHolder.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_ALL);
            if(resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPhone(resultSet.getInt("phone"));
                user.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            try {
                throw new DAOException(e.getMessage(), e);
            } catch (DAOException ex) {
                LOG.error("Can not select all users", e);
                ex.printStackTrace();
            }
        }
        return null;
    }
}
