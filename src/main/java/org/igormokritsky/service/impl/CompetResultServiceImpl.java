package org.igormokritsky.service.impl;

import org.igormokritsky.DAOException;
import org.igormokritsky.ServiceException;
import org.igormokritsky.dao.SwimCompetsDao;
import org.igormokritsky.entity.SwimCompet;
import org.igormokritsky.service.CompetResultService;
import org.igormokritsky.transactions.TransactionManager;
import org.igormokritsky.transactions.*;

public class CompetResultServiceImpl implements CompetResultService {

    private final SwimCompetsDao swimCompetsDao;
    private final TransactionManager transactionManager;

    public CompetResultServiceImpl(SwimCompetsDao swimCompetsDao, TransactionManager transactionManager) {
        this.swimCompetsDao = swimCompetsDao;
        this.transactionManager = transactionManager;
    }

    @Override
    public SwimCompet getResult(final int id) {
        SwimCompet swimCompet;
        try{
            swimCompet = transactionManager.executeTransaction(new TransactionalOperation<SwimCompet>() {
                @Override
                public SwimCompet execute() throws DAOException {
                    return swimCompetsDao.read(id);
                }
            });
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return swimCompet;
    }

    @Override
    public boolean createResult(final SwimCompet swimCompet) {
        try {
            transactionManager.executeTransaction(new TransactionalOperation<Object>() {
                @Override
                public Object execute() throws DAOException {
                    return swimCompetsDao.create(swimCompet);
                }
            });
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return false;
    }

    @Override
    public boolean deleteResult(final int id) {
        try {
            transactionManager.executeTransaction(new TransactionalOperation<Object>() {
                @Override
                public Object execute() throws DAOException {
                    return swimCompetsDao.delete(id);
                }
            });
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return false;
    }
}
