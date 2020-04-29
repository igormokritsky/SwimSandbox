package org.igormokritsky.transactions;

import org.igormokritsky.DAOException;
public interface TransactionalOperation<T> {
    T execute() throws DAOException;
}
