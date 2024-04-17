package com.talkode.project.services;

import com.talkode.project.controllers.dto.LikeDTO;
import com.talkode.project.entities.CustomUser;
import com.talkode.project.entities.Post;
import com.talkode.project.exceptions.EmptyException;
import com.talkode.project.repositories.PostRepository;
import com.talkode.project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    public void post(Post post) {
        if(post.getTitle().length() < 1)
            throw new EmptyException("The title field cannot be empty.");

        CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        post.setUser(user);
        post.setLikeCount(0);

        user.getPosts().add(post);

        this.postRepository.save(post);
    }

    public List<Post> findAll() {
        return this.postRepository.findAll();
    }

    public void like(LikeDTO likeDTO) {
        CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Long> likedPosts = user.getLikedPosts();

        Long post_id = likeDTO.post_id();
        boolean liked = false;

        int lp_size = likedPosts.size();
        for(int i = 0; i < lp_size; i++) {
            if(likedPosts.get(i).equals(post_id)) {
                liked = true;
                break;
            }
        }

        Post post = this.postRepository.getReferenceById(post_id);
        int likeCount = post.getLikeCount();

        if(liked) {
            post.setLikeCount(likeCount - 1);
            likedPosts.remove(post_id);
        } else {
            post.setLikeCount(likeCount + 1);
            likedPosts.add(post_id);
        }

        // If the id of the User who made the post is the same as the logged user id
        // then I'll iterate through the user posts and make those changes to the post inside the posts List
        // ! That issue is probably related to the EAGER FetchType, which prevents it from being updated.
        if(post.getUser().getId().equals(user.getId())) {
            int posts_size = user.getPosts().size();

            for(int i = 0; i < posts_size; i++) {
                Post user_post = user.getPosts().get(i);

                if(user_post.getId().equals(post_id)) {
                    user_post.setLikeCount(liked ? likeCount - 1 : likeCount + 1);
                    break;
                }
            }
        }

        this.postRepository.save(post);
        this.userRepository.save(user);
    }
}
