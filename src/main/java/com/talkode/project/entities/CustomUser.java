package com.talkode.project.entities;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class CustomUser {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String username;
    private String hash;
    public CustomUser() {}

    public CustomUser(String name, String username, String hash) {
        this.name = name;
        this.username = username;
        this.hash = hash;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHash() {
        return this.hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public int hashCode() {
        return Objects.hash(this.id, this.name, this.username, this.hash);
    }

    public boolean equals(Object obj) {
        if(obj == this)
            return true;

        if(!(obj instanceof CustomUser))
            return false;

        CustomUser user = (CustomUser) obj;
        
        return Objects.equals(user.id, this.id) && Objects.equals(user.name, this.name) && Objects.equals(user.username, this.username) && Objects.equals(user.hash, this.hash);
    }
}
