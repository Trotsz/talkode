package com.talkode.project.entities;

import jakarta.persistence.*;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String text;
    @ManyToOne
    private Post post;
    @ManyToOne
    private CustomUser user;

    public Comment() {}

    public Comment(String text, Post post, CustomUser user) {
        this.text = text;
        this.post = post;
        this.user = user;
    }
}