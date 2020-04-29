package org.igormokritsky.service;

import org.igormokritsky.entity.Coach;

public interface CoachesWorkService {

    void createCoach(Coach coach);
    void readCoach(Integer id);
    void updateCoach(Coach coach);
    void deleteCoach(Integer id);

}
