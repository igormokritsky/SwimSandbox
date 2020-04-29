package org.igormokritsky.service.impl;

import org.igormokritsky.DAOException;
import org.igormokritsky.ServiceException;
import org.igormokritsky.dao.SwimmerSponsorsDao;
import org.igormokritsky.entity.SwimmersSponsor;
import org.igormokritsky.service.SponsorService;
import org.igormokritsky.transactions.TransactionManager;
import org.igormokritsky.transactions.*;

public class SponsorServiceImpl implements SponsorService {


    private final SwimmerSponsorsDao swimmerSponsorsDao;
    private final TransactionManager transactionManager;


    public SponsorServiceImpl(SwimmerSponsorsDao swimmerSponsorsDao, TransactionManager transactionManager) {
        this.swimmerSponsorsDao = swimmerSponsorsDao;
        this.transactionManager = transactionManager;
    }



    public SwimmersSponsor getInfo(final int id){

        try{
            return transactionManager.executeTransaction(new TransactionalOperation<SwimmersSponsor>() {
                @Override
                public SwimmersSponsor execute() throws DAOException {
                     return swimmerSponsorsDao.read(id);
                }
            });
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }

    }



}
