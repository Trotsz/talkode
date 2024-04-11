package com.talkode.project.services;

import com.talkode.project.entities.CustomUser;
import com.talkode.project.entities.Post;
import com.talkode.project.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void post(Post post) {
        if(post.getTitle().length() < 1)
            throw new RuntimeException("The title field cannot be empty"); // TODO: Replace by a customized error later

        CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setUser(user);

        this.postRepository.save(post);
    }

    public List<Post> findAll() {
        return this.postRepository.findAll();
    }
}
