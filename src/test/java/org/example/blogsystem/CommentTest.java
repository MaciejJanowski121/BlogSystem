package org.example.blogsystem;

import org.example.blogsystem.model.Comment;
import org.example.blogsystem.repository.CommentRepository;
import org.example.blogsystem.service.CommentService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CommentTest {

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    CommentService commentService;

    @Test
    public void testSavedandFindComment() {
Comment comment = new Comment();
comment.setComment("AAA");

commentRepository.save(comment);

Comment expected = commentRepository.findById(comment.getId()).orElse(null);

assertEquals("AAA",comment.getComment());
    }

    @Test
    public void testDeleteComment() {
        Comment comment = new Comment();
        comment.setComment("something");
        Comment savedComment = commentRepository.save(comment);
commentService.deleteComment(savedComment.getId());

assertFalse(commentRepository.existsById(savedComment.getId()));

    }
}
