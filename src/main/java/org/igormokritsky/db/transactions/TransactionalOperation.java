package org.igormokritsky.db.transactions;

import org.igormokritsky.db.DAOException;
public interface TransactionalOperation<T> {
    T execute() throws DAOException;
}
