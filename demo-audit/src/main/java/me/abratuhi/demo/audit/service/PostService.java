package me.abratuhi.demo.audit.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import me.abratuhi.demo.audit.repository.Post;

@ApplicationScoped
@Transactional
public class PostService {
    public Post createPost(Post post) {
        post.persistAndFlush();
        return post;
    }
}
