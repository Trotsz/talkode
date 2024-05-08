package com.talkode.project.services;

import com.talkode.project.controllers.dto.CommentDTO;
import com.talkode.project.entities.Comment;
import com.talkode.project.entities.CustomUser;
import com.talkode.project.entities.Post;
import com.talkode.project.exceptions.EmptyException;
import com.talkode.project.repositories.CommentRepository;
import com.talkode.project.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    public void comment(CommentDTO comment) {
        if(comment.text().length() < 1)
            throw new EmptyException("The text field cannot be empty.");

        Comment comment1 = new Comment(comment.text());

        CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Post post = this.postRepository.getReferenceById(comment.post_id());
        post.setCommentCount(post.getCommentCount() + 1);

        comment1.setUser(user);
        comment1.setPost(post);

        this.commentRepository.save(comment1);
    }

    public List<Comment> findAllByPostId(Long post_id) {
        return this.commentRepository.findAllByPostId(post_id);
    }
}
