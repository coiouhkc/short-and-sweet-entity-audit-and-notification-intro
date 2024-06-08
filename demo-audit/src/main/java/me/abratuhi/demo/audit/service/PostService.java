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

  public Post findPostById(int id) {
    return Post.findById(id);
  }

  public Post updatePost(Post post) {
    post.persistAndFlush();
    return post;
  }
}
