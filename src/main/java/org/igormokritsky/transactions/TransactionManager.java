package org.igormokritsky.transactions;

import org.igormokritsky.DAOException;

public interface TransactionManager {
    <T> T executeTransaction(TransactionalOperation<T> operation) throws DAOException, DAOException;
}
