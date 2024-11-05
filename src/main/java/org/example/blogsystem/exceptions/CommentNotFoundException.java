package org.example.blogsystem.exceptions;

public class CommentNotFoundException extends RuntimeException{
    public CommentNotFoundException (String message) {
        super(message);
    }
}
