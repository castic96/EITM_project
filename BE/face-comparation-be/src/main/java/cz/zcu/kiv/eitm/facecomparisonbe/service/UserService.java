package cz.zcu.kiv.eitm.facecomparisonbe.service;

import com.amazonaws.services.dynamodbv2.xspec.L;
import cz.zcu.kiv.eitm.facecomparisonbe.model.User;

import java.util.List;

public interface UserService {
    User createUser(User user);

    List<User> getAllUsers();

    User getUserByEmailAddress(String emailAddress);
}
