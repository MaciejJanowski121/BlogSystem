package org.example.blogsystem.service;

import org.example.blogsystem.exceptions.UserNotFoundException;
import org.example.blogsystem.model.User;
import org.example.blogsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Constructor for injecting UserRepository and PasswordEncoder
    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Registers a new user by encoding their password and saving to the repository
    public void registerUser(String username, String password) {
        User user = new User();
        user.setUserName(username);
        user.setPassword(passwordEncoder.encode(password)); // Encodes the raw password
        userRepository.save(user); // Saves the user to the database
    }

    // Finds a user by their username
    public Optional<User> findByUserName(String userName) {
        return userRepository.findUserByUserName(userName);
    }

    // Checks if the provided raw password matches the encoded password stored for the user
    public boolean checkPassword(User user, String rawPassword) {
        return passwordEncoder.matches(rawPassword, user.getPassword());
    }
}