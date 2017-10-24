package pl.edu.oswiecim.pwsz.inf.hrs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.oswiecim.pwsz.inf.hrs.model.Role;
import pl.edu.oswiecim.pwsz.inf.hrs.model.User;
import pl.edu.oswiecim.pwsz.inf.hrs.service.UserService;

import java.util.Arrays;
import java.util.HashSet;

@RestController
public class TestController {

    @Autowired
    private UserService userService;

    @RequestMapping("/add/{username}/{password}/{role}")
    public void create(@PathVariable String username, @PathVariable String password, @PathVariable String role) {
        User user = new User();
        user.setPassword(password);
        user.setUsername(username);
        Role userRole = new Role();
        userRole.setRole(role);
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        userService.saveUser(user);
    }

    @RequestMapping("/list")
    public Iterable <User> get() {
        return userService.findAll();
    }
}
