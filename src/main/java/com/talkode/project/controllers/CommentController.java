package com.talkode.project.controllers;

import com.talkode.project.controllers.dto.CommentDTO;
import com.talkode.project.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/comment")
    public void comment(@RequestBody CommentDTO comment) {
        this.commentService.comment(comment);
    }

    @GetMapping("/test")
    public String test() {
        return "AAAA";
    }

    // TODO: Implement comment deletion
}
