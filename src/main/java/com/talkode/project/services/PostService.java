package com.talkode.project.services;

import com.talkode.project.entities.Post;
import com.talkode.project.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void post(Post post) {


        this.postRepository.save(post);
    }

    public List<Post> findAll() {
        return this.postRepository.findAll();
    }
}
