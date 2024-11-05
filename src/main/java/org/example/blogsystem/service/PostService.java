package org.example.blogsystem.service;

import jakarta.transaction.Transactional;
import org.example.blogsystem.exceptions.PostNotFoundException;
import org.example.blogsystem.exceptions.UserNotFoundException;
import org.example.blogsystem.model.Comment;
import org.example.blogsystem.model.Post;
import org.example.blogsystem.repository.CommentRepository;
import org.example.blogsystem.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    // Constructor for injecting PostRepository and CommentRepository
    @Autowired
    public PostService(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    // Finds a post by its ID
    public Optional<Post> findPostById(Long id) {
        return postRepository.findById(id);
    }

    // Retrieves posts by a specific user's username
    public List<Post> findPostsByUserName(String userName) {
        return postRepository.findByUser_UserName(userName);
    }

    // Saves a new post to the database
    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    // Updates the title and content of an existing post
    public Post updatePost(Long id, String title, String content) {
        Optional<Post> optionalPost = postRepository.findById(id);

        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            post.setContent(content);
            post.setTitle(title);
            postRepository.save(post);
            return post;
        } else {
            throw new PostNotFoundException("This Post doesn't exist");
        }
    }

    // Deletes a post along with its associated comments using a transactional operation
    @Transactional
    public void deletePostWithComments(Long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        commentRepository.deleteAll(comments);
        postRepository.deleteById(postId);
    }

    // Retrieves all posts from the database
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    // Deletes a post by its ID
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}