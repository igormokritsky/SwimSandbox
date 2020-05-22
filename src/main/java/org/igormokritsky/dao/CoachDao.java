package org.igormokritsky.dao;


import org.igormokritsky.db.DAOException;
import org.igormokritsky.entity.Coach;

import java.util.List;

public interface CoachDao extends CrudDAO<Coach, Integer>{
    List<Coach> selectAll() throws DAOException;
}
