package com.talkode.project.controllers;

import com.talkode.project.entities.Post;
import com.talkode.project.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
public class PostController {
    @Autowired
    private PostService postService;
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Post>> index() {
        return ResponseEntity.ok(this.postService.findAll());
    }

    @PostMapping("/post")
    public RedirectView post(@RequestBody Post post) {
        this.postService.post(post);

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8080/");

        return redirectView;
    }
}
