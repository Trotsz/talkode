package com.talkode.project.controllers;

import com.talkode.project.entities.Post;
import com.talkode.project.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostController {
    @Autowired
    private PostService postService;
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/post")
    public String post(@RequestBody Post post) {
        this.postService.post(post);
        return "redirect:http:localhost:8080/";
    }

    @GetMapping("/posts")
    public List<Post> getPosts() {
        return this.postService.findAll();
    }
}
