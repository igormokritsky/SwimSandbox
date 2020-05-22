package org.igormokritsky.db.transactions;

import org.igormokritsky.db.DAOException;

public interface TransactionManager {
    <T> T executeTransaction(TransactionalOperation<T> operation) throws DAOException, DAOException;
}
