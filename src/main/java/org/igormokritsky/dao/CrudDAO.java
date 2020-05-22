package org.igormokritsky.dao;


import org.igormokritsky.db.DAOException;

public interface CrudDAO<T, PK> {

    PK create(T object) throws DAOException;

    T read(PK id) throws DAOException;

    boolean update(T object) throws DAOException;

    boolean delete(PK id) throws DAOException;
}
