package org.igormokritsky.service.impl;

import org.igormokritsky.db.DAOException;
import org.igormokritsky.db.ServiceException;
import org.igormokritsky.dao.CoachDao;
import org.igormokritsky.entity.Coach;
import org.igormokritsky.service.CoachesWorkService;
import org.igormokritsky.db.transactions.TransactionManager;
import org.igormokritsky.db.transactions.TransactionalOperation;

import java.util.List;

public class CoachesWorkServiceImpl implements CoachesWorkService {

    private final CoachDao coachesDao;
    private final TransactionManager transactionManager;

    public CoachesWorkServiceImpl(CoachDao coachesDao, TransactionManager transactionManager) {
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
    public Coach readCoach(final Integer id) {
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
        return null;
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


    @Override
    public List<Coach> selectAll() {
        List<Coach> coaches;
        try{
            coaches = transactionManager.executeTransaction(() -> coachesDao.selectAll());
        } catch (DAOException e){
            throw new ServiceException(e.getMessage(), e);
        }
        return coaches;
    }
}
