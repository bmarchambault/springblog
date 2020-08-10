package com.codeup.springblog.models;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name="posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 150)
    private String title;

    @Column(columnDefinition = "TEXT NOT NULL")
    private String body;

//    this represents the user object that wrote the posts
    @ManyToOne
    @JoinColumn (name = "author_id")
    @JsonBackReference
    private User author;

    public Post(String title, String body, User author) {
        this.title = title;
        this.body = body;
        this.author = author;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User postAuthor) {
        this.author = postAuthor;
    }

    public Post (){};

 public Post(String title, String body){
    this.title = title;
    this.body = body;
 }

    public Post(int id, String title, String body){
        this.id = id;
        this.title = title;
        this.body = body;
    }


    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Post{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}