package cz.zcu.kiv.eitm.facecomparisonbe.controller;

import cz.zcu.kiv.eitm.facecomparisonbe.model.Result;
import cz.zcu.kiv.eitm.facecomparisonbe.model.User;
import cz.zcu.kiv.eitm.facecomparisonbe.service.AuthoriseService;
import cz.zcu.kiv.eitm.facecomparisonbe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AppController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthoriseService authoriseService;

    @PostMapping("/register")
    public @ResponseBody
    Result register(@RequestBody User user) {

        this.userService.createUser(user);

        return new Result("register", true);
    }

    @PostMapping("/login")
    public @ResponseBody
    Result login(@RequestBody User user) {
        User userDb = userService.getUserByEmailAddress(user.getEmailAddress());
        return authoriseService.authorise(user, userDb);
    }

    @GetMapping("/users")
    public @ResponseBody
    List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
