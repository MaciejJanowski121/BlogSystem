package org.example.blogsystem.controller;

import org.example.blogsystem.model.Comment;
import org.example.blogsystem.model.CommentDTO;
import org.example.blogsystem.model.Post;
import org.example.blogsystem.model.User;
import org.example.blogsystem.service.CommentService;
import org.example.blogsystem.service.PostService;
import org.example.blogsystem.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class PostController {

    private final PostService postService;
    private final UserService userService;
    private final CommentService commentService;

    // Constructor to inject services
    public PostController(PostService postService, UserService userService, CommentService commentService) {
        this.postService = postService;
        this.userService = userService;
        this.commentService = commentService;
    }

    // Endpoint to create a new post
    @PostMapping("/api/posts/create")
    public ResponseEntity<Post> createPost(@RequestBody Post post, Principal principal) {
        // Fetch user from the principal (logged-in user)
        User user = userService.findByUserName(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));
        post.setUser(user); // Associate the post with the user
        post.setAuthor(principal.getName()); // Set the author's username
        Post newPost = postService.savePost(post); // Save the post
        return ResponseEntity.status(HttpStatus.CREATED).body(newPost); // Return the created post
    }

    // Endpoint to get all posts
    @GetMapping("/api/posts")
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts(); // Retrieve all posts
        return ResponseEntity.ok(posts); // Return the list of posts
    }

    // Endpoint to delete a post along with its comments
    @DeleteMapping("/api/posts/{postId}")
    public ResponseEntity<Void> deletePostWithComments(@PathVariable Long postId) {
        postService.deletePostWithComments(postId); // Delete the post and its comments
        return ResponseEntity.noContent().build(); // Return no content status
    }

    // Endpoint to update a post
    @PutMapping("/api/posts/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody Post updatedPost) {
        postService.updatePost(id, updatedPost.getTitle(), updatedPost.getContent()); // Update the post
        return ResponseEntity.ok().build(); // Return OK status
    }

    // Endpoint to get comments by post ID
    @GetMapping("/api/posts/{postID}/comments")
    public ResponseEntity<List<Comment>> getCommentsByPostId(@PathVariable Long postID) {
        List<Comment> comments = commentService.getCommentsByPostId(postID); // Retrieve comments for a post
        return ResponseEntity.ok(comments); // Return the list of comments
    }

    // Endpoint to add a comment to a post
    @PostMapping("/api/posts/{postId}/comments/add")
    public ResponseEntity<Comment> addCommentToPost(@PathVariable Long postId, @RequestBody CommentDTO commentDTO, Principal principal) {
        // Set the username in the CommentDTO using the logged-in user
        commentDTO.setUsername(principal.getName());
        // Add the comment to the post
        Comment savedComment = commentService.addCommentToPost(postId, commentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedComment); // Return the created comment
    }
}