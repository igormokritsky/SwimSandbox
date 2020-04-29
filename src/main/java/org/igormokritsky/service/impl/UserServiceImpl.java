package org.igormokritsky.service.impl;

import org.igormokritsky.DAOException;
import org.igormokritsky.dao.UsersDao;
import org.igormokritsky.service.UserService;
import org.igormokritsky.entity.User;
import org.igormokritsky.ServiceException;
import org.igormokritsky.transactions.TransactionManager;
import org.igormokritsky.transactions.*;


public class UserServiceImpl implements UserService {
    private final UsersDao usersDao;
    private final TransactionManager transactionManager;

    public UserServiceImpl(UsersDao usersDao, TransactionManager transactionManager) {
        this.usersDao = usersDao;
        this.transactionManager = transactionManager;
    }


    @Override
    public User getUser(final int id) {
        User user;
        try{
            user = transactionManager.executeTransaction(new TransactionalOperation<User>() {
                @Override
                public User execute() throws DAOException {
                    return usersDao.read(id);
                }
            });
        } catch (DAOException e){
            throw new ServiceException(e.getMessage(), e);
        }

        return user;
    }

    @Override
    public boolean createUser(final User user){
        try {
            transactionManager.executeTransaction(new TransactionalOperation<Object>() {
                @Override
                public Object execute() throws DAOException {
                    return usersDao.create(user);
                }
            });
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return false;
    }

    @Override
    public boolean deleteUser(final int id) {
        try {
            transactionManager.executeTransaction(new TransactionalOperation<Object>() {
                @Override
                public Object execute() throws DAOException {
                    return usersDao.delete(id);
                }
            });
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }

        return false;
    }


}
