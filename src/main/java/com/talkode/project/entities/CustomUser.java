package com.talkode.project.entities;

import java.util.*;

import com.talkode.project.entities.enums.Role;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class CustomUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String username;
    private String hash;
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> posts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Comment> comments;

    public CustomUser() {}

    public CustomUser(String name, String username, String hash, Role role) {
        this.name = name;
        this.username = username;
        this.hash = hash;
        this.posts = new ArrayList<>();
        this.comments = new ArrayList<>();
        this.role = role;
    }

    public UUID getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == Role.ADMIN)
            return List.of(new SimpleGrantedAuthority(Role.ADMIN.getRole()), new SimpleGrantedAuthority(Role.USER.getRole()));

        return List.of(new SimpleGrantedAuthority(Role.USER.getRole()));
    }

    @Override
    public String getPassword() {
        return this.hash;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
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

    public List<Comment> getComments() {
        return comments;
    }

    public List<Post> getPosts() {
        return posts;
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
