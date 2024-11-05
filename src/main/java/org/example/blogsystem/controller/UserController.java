package org.example.blogsystem.controller;

import org.example.blogsystem.model.User;
import org.example.blogsystem.model.UserDto;
import org.example.blogsystem.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    // Constructor to inject the UserService
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Endpoint to register a new user
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDto userDto) {
        try {
            // Calls the UserService to register a user with provided username and password
            userService.registerUser(userDto.getUsername(), userDto.getPassword());
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully"); // Returns success response
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error during registration"); // Returns error response if registration fails
        }
    }
}