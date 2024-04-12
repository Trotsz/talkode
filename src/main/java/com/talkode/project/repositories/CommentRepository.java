package com.talkode.project.repositories;

import com.talkode.project.entities.Comment;
import com.talkode.project.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    public List<Comment> findAllByPostId(Long post_id);
}
