package org.example.blogsystem.controller;

import org.example.blogsystem.exceptions.CommentNotFoundException;
import org.example.blogsystem.model.Comment;
import org.example.blogsystem.service.CommentService;
import org.example.blogsystem.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
public class CommentController {

    // Services for managing posts and comments
    private final PostService postService;
    private final CommentService commentService;

    // Constructor to initialize services
    public CommentController(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    // Endpoint to retrieve a comment by its ID
    @GetMapping("/api/comment/getcomment/{commentId}")
    public ResponseEntity<Comment> getComment(@PathVariable Long commentId) {
        Comment comment = commentService.getCommentById(commentId);
        return ResponseEntity.ok(comment); // Return the comment if found
    }

    // Endpoint to edit a comment by its ID
    @PutMapping("/api/comment/editcomment/{commentId}")
    public ResponseEntity<Comment> editComment(@PathVariable Long commentId, @RequestBody Comment comment) {
        Comment updatedComment = commentService.updateComment(comment.getComment(), commentId, comment.getAuthor());
        return ResponseEntity.ok(updatedComment); // Return the updated comment
    }

    // Endpoint to delete a comment by its ID
    @DeleteMapping("/api/comment/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        Comment comment = commentService.findCommentById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment with this ID doesn't exist"));
        commentService.deleteComment(commentId); // Delete the comment
        return ResponseEntity.noContent().build(); // Return no content status
    }

}