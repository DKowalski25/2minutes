package dev.twominutes.notetaking.controller;

import dev.twominutes.notetaking.models.User;
import dev.twominutes.notetaking.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.RegisterUser(user);
    }
}
