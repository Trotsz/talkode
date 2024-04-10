package com.talkode.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.talkode.project.entities.CustomUser;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<CustomUser, UUID> {
    public UserDetails findByUsername(String username);
    public List<CustomUser> findAllByUsername(String username);
}
