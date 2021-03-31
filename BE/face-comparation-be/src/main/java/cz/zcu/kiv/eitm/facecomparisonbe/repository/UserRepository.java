package cz.zcu.kiv.eitm.facecomparisonbe.repository;

import cz.zcu.kiv.eitm.facecomparisonbe.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findUsersByIpAddress(String ipAddress);

}
