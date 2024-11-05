package org.example.blogsystem.repository;

import org.example.blogsystem.model.Comment;
import org.example.blogsystem.model.Post;
import org.example.blogsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface CommentRepository  extends JpaRepository<Comment, Long>{


List<Comment> findByUser(User user) ;

List<Comment> findByPost (Post post);
List<Comment> findByPostId (Long postId);

List<Comment> findByCreationDate(Date date);

void deleteByUser (User user);
void deleteByPost (Post post);

}
