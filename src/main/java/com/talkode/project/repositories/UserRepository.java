package com.talkode.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.talkode.project.entities.CustomUser;

public interface UserRepository extends JpaRepository<CustomUser, Long> {
    
}
