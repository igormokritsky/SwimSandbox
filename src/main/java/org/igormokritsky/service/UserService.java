package org.igormokritsky.service;

import org.igormokritsky.entity.User;

public interface UserService {
    User getUser(int id);
    boolean createUser(User user);
    boolean deleteUser(int id);
}
