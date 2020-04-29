package org.example.mysql;

import org.igormokritsky.ConnectionHolder;
import org.igormokritsky.DAOException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.igormokritsky.entity.*;
import java.sql.*;
import static org.junit.Assert.*;
import org.igormokritsky.dao.impl.CoachDaoImpl;


public class CoachesDaoTest {

    private CoachDaoImpl coachDaoImpl;

    @BeforeClass
    public static void beforeClass() throws Exception {
     CoachDaoImpl coachDao = new CoachDaoImpl();
     coachDao.delete(4);
    }


    @Test
    public void testGetCoach() throws DAOException {
        coachDaoImpl = new CoachDaoImpl();
        coachDaoImpl.read(1);
        assertNotNull(coachDaoImpl.read(1));
    }

    @Test
    public void testInsertCoach() throws DAOException {
        coachDaoImpl = new CoachDaoImpl();
        Coach coach = new Coach();
        coach.setId(4);
        coach.setName("Hasso");
        coach.setAwards("Halal");
        coach.setCountry_id(1);
        coach.setUser_id(1);

        coachDaoImpl.create(coach);
        assertNotNull("we added someone", coachDaoImpl.read(4));
    }

    @Test
    public void testUpdateCoach() throws DAOException {
        coachDaoImpl = new CoachDaoImpl();
        coachDaoImpl.read(1);

        Coach coach = new Coach();
        coach.setName("Ragucci");
        coach.setAwards("Best in 2019");
        coach.setId(1);

        coachDaoImpl.update(coach);
        assertEquals(coachDaoImpl.read(1).getId(), coach.getId());
        assertEquals(coachDaoImpl.read(1).getName(), coach.getName());
        assertEquals(coachDaoImpl.read(1).getAwards(), coach.getAwards());
    }

    @Test
    public void testDeleteCoach() throws DAOException {
        coachDaoImpl = new CoachDaoImpl();
        coachDaoImpl.read(4);
        coachDaoImpl.delete(4);
        assertNull("we don't have this coach",coachDaoImpl.read(4));
    }

}