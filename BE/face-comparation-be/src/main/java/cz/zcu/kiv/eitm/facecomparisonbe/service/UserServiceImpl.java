package cz.zcu.kiv.eitm.facecomparisonbe.service;

import cz.zcu.kiv.eitm.facecomparisonbe.model.User;
import cz.zcu.kiv.eitm.facecomparisonbe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getUsersByEmail(String email) {
        return this.userRepository.findUsersByEmail(email);
    }

    @Override
    public List<User> getUsersByIpAddress(String ipAddress) {
        return this.userRepository.findUsersByIpAddress(ipAddress);
    }
}
