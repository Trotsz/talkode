package com.talkode.project.controllers;

import com.talkode.project.controllers.dto.LikeDTO;
import com.talkode.project.entities.Post;
import com.talkode.project.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("/post")
    public RedirectView post(@RequestBody Post post) {
        this.postService.post(post);

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8080/");

        return redirectView;
    }

    @PostMapping("/like")
    public void like(@RequestBody LikeDTO likeDTO) {
        this.postService.like(likeDTO);
    }

    // Implement post deletion
}
