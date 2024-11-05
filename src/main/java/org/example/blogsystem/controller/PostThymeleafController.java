package org.example.blogsystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class PostThymeleafController {

    // Displays the form for creating a new post
    @GetMapping("/posts/create")
    public String createPost() {
        return "createPost"; // Returns the "createPost" Thymeleaf template
    }

    // Displays the list of posts
    @GetMapping("/posts")
    public String viewPosts() {
        return "posts"; // Returns the "posts" Thymeleaf template
    }

    // Displays the registration form
    @GetMapping("/register")
    public String register() {
        return "register"; // Returns the "register" Thymeleaf template
    }

    // Retrieves the current logged-in user's name
    @GetMapping("/api/user")
    public ResponseEntity<String> getCurrentUser(Principal principal) {
        return ResponseEntity.ok(principal.getName()); // Returns the username as a response
    }
}