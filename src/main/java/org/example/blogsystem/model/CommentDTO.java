package org.example.blogsystem.model;

import java.time.LocalDateTime;

public class CommentDTO {

    private Post post;



    private String comment;
    private String username;

    private LocalDateTime creationDate;

    public CommentDTO() {
        this.creationDate = LocalDateTime.now();
    }
    public CommentDTO (String comment,String username) {

        this.comment= comment;
        this.username = username;
        this.creationDate = LocalDateTime.now();
    }


    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}
