package com.talkode.project.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.*;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String text;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private Post post;

    @JsonIgnore
    @ManyToOne
    private CustomUser user;

    @Column(name = "timestamp", nullable = false, updatable = false)
    @CreationTimestamp
    private Instant timestamp;

    public Comment() {}

    public Comment(String text) {
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public CustomUser getUser() {
        return user;
    }

    public void setUser(CustomUser user) {
        this.user = user;
    }

    public Instant getTimestamp() {
        return this.timestamp;
    }

    public LocalDateTime getDateTime() {
        return LocalDateTime.ofInstant(this.timestamp, ZoneId.of("America/Sao_Paulo"));
    }
}