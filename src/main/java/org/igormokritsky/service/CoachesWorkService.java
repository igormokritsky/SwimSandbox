package org.igormokritsky.service;

import org.igormokritsky.entity.Coach;

import java.util.List;

public interface CoachesWorkService {

    void createCoach(Coach coach);
    Coach readCoach(Integer id);
    void updateCoach(Coach coach);
    void deleteCoach(Integer id);
    List<Coach> selectAll();

}
