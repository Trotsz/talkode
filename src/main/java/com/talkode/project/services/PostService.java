package com.talkode.project.services;

import com.talkode.project.entities.CustomUser;
import com.talkode.project.entities.Post;
import com.talkode.project.exceptions.EmptyException;
import com.talkode.project.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public void post(Post post) {
        if(post.getTitle().length() < 1)
            throw new EmptyException("The title field cannot be empty.");

        CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setUser(user);
        user.getPosts().add(post);

        this.postRepository.save(post);
    }

    public List<Post> findAll() {
        return this.postRepository.findAll();
    }
}
