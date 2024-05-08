package com.talkode.project.controllers;

import com.talkode.project.config.TokenService;
import com.talkode.project.entities.CustomUser;
import com.talkode.project.entities.Post;
import com.talkode.project.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private PostService postService;

    @Autowired
    private TokenService tokenService;

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/")
    public ResponseEntity<List<Post>> index(Model model) {
        List<Post> posts = this.postService.findAllByOrderByTimestampDesc();

        return ResponseEntity.ok(posts);
    }
}
