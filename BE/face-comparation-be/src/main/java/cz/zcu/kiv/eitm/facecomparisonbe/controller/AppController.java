package cz.zcu.kiv.eitm.facecomparisonbe.controller;

import cz.zcu.kiv.eitm.facecomparisonbe.dto.LoginRequest;
import cz.zcu.kiv.eitm.facecomparisonbe.dto.LoginResponse;
import cz.zcu.kiv.eitm.facecomparisonbe.dto.RegisterRequest;
import cz.zcu.kiv.eitm.facecomparisonbe.dto.RegisterResponse;
import cz.zcu.kiv.eitm.facecomparisonbe.model.User;
import cz.zcu.kiv.eitm.facecomparisonbe.service.AuthoriseService;
import cz.zcu.kiv.eitm.facecomparisonbe.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "${client.url}")
@RestController
public class AppController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthoriseService authoriseService;

    private final Logger LOG = LoggerFactory.getLogger(AppController.class);

    @PostMapping("/register")
    public RegisterResponse register(@RequestBody RegisterRequest registerRequest) {
        LOG.info(registerRequest.toString());
        this.userService.createUser(new User(registerRequest.getFirstName(), registerRequest.getLastName(), registerRequest.getImage(), registerRequest.getIpAddress()));
        return new RegisterResponse(true, null); // todo případně dodělat kontrolu existující uživatele podle emailové adresy
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        LOG.info(loginRequest.toString());
        LoginResponse loginResponse = authoriseService.authorise(loginRequest.getIpAddress(), loginRequest.getImage());
        LOG.info(loginResponse.toString());
        return loginResponse;
    }

    @GetMapping("/test")
    public RegisterResponse test() {
        return new RegisterResponse(false, "This is from BE");
    }

}
