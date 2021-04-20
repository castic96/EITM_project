package cz.zcu.kiv.eitm.facecomparisonbe.service;

import cz.zcu.kiv.eitm.facecomparisonbe.model.User;

import java.util.List;

public interface UserService {
    User createUser(User user);

    List<User> getAllUsers();

    List<User> getUsersByEmail(String email);

    List<User> getUsersByIpAddress(String ipAddress);
}
