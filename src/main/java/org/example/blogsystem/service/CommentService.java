package org.example.blogsystem.service;

import org.example.blogsystem.exceptions.CommentNotFoundException;
import org.example.blogsystem.model.Comment;
import org.example.blogsystem.model.CommentDTO;
import org.example.blogsystem.model.Post;
import org.example.blogsystem.repository.CommentRepository;
import org.example.blogsystem.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    public final CommentRepository commentRepository;
    public final PostRepository postRepository;

    // Constructor for dependency injection
    @Autowired
    public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    // Finds a comment by its ID
    public Optional<Comment> findCommentById(Long id) {
        return commentRepository.findById(id);
    }

    // Updates a comment with new content and author information
    public Comment updateComment(String comment, Long id, String author) {
        Optional<Comment> optionalComment = commentRepository.findById(id);

        if (optionalComment.isPresent()) {
            Comment newComment = optionalComment.get();
            newComment.setComment(comment);
            newComment.setAuthor(author);
            commentRepository.save(newComment);
            return newComment;
        } else {
            throw new CommentNotFoundException("Comment not found");
        }
    }

    // Deletes a comment by its ID
    public void deleteComment(Long id) {
        if (commentRepository.existsById(id)) {
            commentRepository.deleteById(id);
        } else {
            throw new CommentNotFoundException("Comment not found");
        }
    }

    // Adds a comment to a specific post
    public Comment addCommentToPost(Long postId, CommentDTO commentDTO) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found with id: " + postId));

        // Converts CommentDTO to Comment
        Comment comment = new Comment();
        comment.setComment(commentDTO.getComment());
        comment.setAuthor(commentDTO.getUsername());
        comment.setPost(post);

        // Saves the newly created comment
        return commentRepository.save(comment);
    }

    // Retrieves a comment by its ID
    public Comment getCommentById(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException("Comment not found with id: " + commentId));
    }

    // Retrieves all comments associated with a specific post ID
    public List<Comment> getCommentsByPostId(Long postId) {
        return commentRepository.findByPostId(postId);
    }
}