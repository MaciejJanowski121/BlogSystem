package org.example.blogsystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Post {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @OneToMany(mappedBy = "post")
    @JsonBackReference
    private List<Comment> comments;


    private String title;
    private String content;
    private String author;



    public Post() {

    }

    public Post (String title,String content,User user,String author) {
        this.title = title;
        this.content = content;
        this.user = user;
        this.author = author;
    }
    public Post (String title,String content,String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
