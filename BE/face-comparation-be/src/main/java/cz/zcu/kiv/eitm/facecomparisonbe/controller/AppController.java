package cz.zcu.kiv.eitm.facecomparisonbe.controller;

import cz.zcu.kiv.eitm.facecomparisonbe.dto.LoginRequest;
import cz.zcu.kiv.eitm.facecomparisonbe.dto.LoginResponse;
import cz.zcu.kiv.eitm.facecomparisonbe.dto.RegisterRequest;
import cz.zcu.kiv.eitm.facecomparisonbe.dto.RegisterResponse;
import cz.zcu.kiv.eitm.facecomparisonbe.model.User;
import cz.zcu.kiv.eitm.facecomparisonbe.service.AuthenticateService;
import cz.zcu.kiv.eitm.facecomparisonbe.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class AppController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticateService authenticateService;

    private final Logger LOG = LoggerFactory.getLogger(AppController.class);

    /**
     * Registers user in application.
     *
     * @param registerRequest register request
     * @return register response with status
     */
    @PostMapping("/register")
    public RegisterResponse register(@RequestBody RegisterRequest registerRequest) {
        LOG.info(registerRequest.toString());
        List<User> users = this.userService.getUsersByEmail(registerRequest.getEmail());
        if (users.size() > 0) {
            return new RegisterResponse(false, "This e-mail address is already taken.");
        }

        this.userService.createUser(new User(registerRequest.getFirstName(), registerRequest.getLastName(), registerRequest.getEmail(), registerRequest.getImage(), registerRequest.getIpAddress()));
        return new RegisterResponse(true, null);
    }

    /**
     * Logs user in application.
     *
     * @param loginRequest login request
     * @return login response with status, first name and last name
     */
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        LOG.info(loginRequest.toString());
        LoginResponse loginResponse = authenticateService.authenticate(loginRequest.getIpAddress(), loginRequest.getImage());
        LOG.info(loginResponse.toString());
        return loginResponse;
    }

    @GetMapping("/users")
    public List<User> test() {
        return userService.getAllUsers();

    }

}
