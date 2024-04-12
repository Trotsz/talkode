package com.talkode.project.controllers;

import com.talkode.project.controllers.dto.CommentDTO;
import com.talkode.project.entities.Comment;
import com.talkode.project.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/comments")
    public ResponseEntity<List<Comment>> comments(@RequestParam(name = "post_id") Long post_id) {
        List<Comment> cmts = this.commentService.findAllByPostId(post_id);
        return ResponseEntity.ok(cmts);
    }
}
