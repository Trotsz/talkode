package com.talkode.project.controllers;

import com.talkode.project.entities.CustomUser;
import com.talkode.project.entities.Post;
import com.talkode.project.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Controller
public class PostView {
    @Autowired
    private PostService postService;

    @GetMapping("/post")
    public String post(@RequestParam(name = "post_id") Long post_id, Model model) {
        Post post = this.postService.findById(post_id);

        CustomUser user = null;
        boolean isLogged = false;

        String token = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization");
        if(token != null) {
            user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }

        model.addAttribute("post", post);
        model.addAttribute("currentUser", user);
        model.addAttribute("isUserLogged", isLogged);
        return "postview";
    }
}
