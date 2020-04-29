package org.igormokritsky.dao.impl;

import org.igormokritsky.dao.*;

public class MySqlDAOFactory {

    public CoachesDao getCoachesDao(){
        return CoachDaoImpl.getInstance();
    }

    public CompetitionsDao getCompetitionsDao(){
        return CompetitonDaoImpl.getInstance();
    }

    public CountriesDao getCountriesDao(){
        return CountryDaoImpl.getInstance();
    }

    public SponsorsDao getSponsorsDao(){
        return SponsorDaoImpl.getInstance();
    }

    public StylesDao getStylesDao(){
        return StyleDaoImpl.getInstance();
    }

    public SwimCompetsDao getSwimCompetsDao() {
        return SwimCompetDaoImpl.getInstance();
    }

    public SwimmersDao getSwimmersDao() {
        return SwimmerDaoImpl.getInstance();
    }

    public SwimmerSponsorsDao getSwimmerSponsorsDao() {
        return SwimmersSponsorDaoImpl.getInstance();
    }

    public UsersDao getUsersDao() {
        return UserDaoImpl.getInstance();
    }

}
