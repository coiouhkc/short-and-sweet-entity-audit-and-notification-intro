package me.abratuhi.demo.audit.service;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import me.abratuhi.demo.audit.repository.Post;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class PostServiceTest {
  @Inject
  PostService postService;

  @Test
  void createOk() {
    Post post = new Post();
    post.setText("My first post");
    Post result = postService.createPost(post);
    assertThat(result.getId()).isNotNull();
  }
}
