package org.igormokritsky.service;

import org.igormokritsky.entity.SwimCompet;

public interface CompetResultService {

    SwimCompet getResult(int id);
    boolean createResult(SwimCompet swimCompet);
    boolean deleteResult(int id);
}
