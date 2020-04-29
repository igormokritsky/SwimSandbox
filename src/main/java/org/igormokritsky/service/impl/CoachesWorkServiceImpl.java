package org.igormokritsky.service.impl;

import org.igormokritsky.DAOException;
import org.igormokritsky.ServiceException;
import org.igormokritsky.dao.CoachesDao;
import org.igormokritsky.entity.Coach;
import org.igormokritsky.service.CoachesWorkService;
import org.igormokritsky.transactions.TransactionManager;
import org.igormokritsky.transactions.TransactionalOperation;

public class CoachesWorkServiceImpl implements CoachesWorkService {

    private final CoachesDao coachesDao;
    private final TransactionManager transactionManager;

    public CoachesWorkServiceImpl(CoachesDao coachesDao, TransactionManager transactionManager) {
        this.coachesDao = coachesDao;
        this.transactionManager = transactionManager;
    }

    @Override
    public void createCoach(final Coach coach) {
        try{
            transactionManager.executeTransaction(new TransactionalOperation<Coach>() {
                @Override
                public Coach execute() throws DAOException {
                    coachesDao.create(coach);
                    return null;
                }
            });
        } catch (DAOException e){
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void readCoach(final Integer id) {
        try{
            transactionManager.executeTransaction(new TransactionalOperation<Coach>() {
                @Override
                public Coach execute() throws DAOException {
                    coachesDao.read(id);
                    return null;
                }
            });
        } catch (DAOException e){
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void updateCoach(final Coach coach) {
        try{
            transactionManager.executeTransaction(new TransactionalOperation<Coach>() {
                @Override
                public Coach execute() throws DAOException {
                    coachesDao.update(coach);
                    return null;
                }
            });
        } catch (DAOException e){
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void deleteCoach(final Integer id) {
        try{
            transactionManager.executeTransaction(new TransactionalOperation<Coach>() {
                @Override
                public Coach execute() throws DAOException {
                    coachesDao.delete(id);
                    return null;
                }
            });
        } catch (DAOException e){
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
