package me.abratuhi.demo.audit.service;

import com.github.database.rider.cdi.api.DBRider;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import me.abratuhi.demo.audit.repository.Post;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DBRider
@QuarkusTest
class PostServiceTest {
  @Inject PostService postService;

  @Inject
  EntityManager entityManager;

  @DataSet(value = "dataset/pre_create_ok.yaml", cleanBefore = true, cleanAfter = true)
  @ExpectedDataSet(value = "dataset/expected_create_ok.yaml")
  @Test
  void createOk() {
    Post post = new Post();
    post.setText("My first post");
    Post result = postService.createPost(post);
    assertThat(result.getId()).isNotNull();
  }

  @DataSet(value = "dataset/pre_create_ok.yaml", cleanBefore = true, cleanAfter = true)
  @ExpectedDataSet(value = "dataset/expected_create_ok.yaml")
  @Test
  void createOkEnvers() {
    Post post = new Post();
    post.setText("My first post");
    Post result = postService.createPost(post);
    assertThat(result.getId()).isNotNull();

    AuditReader auditReader = AuditReaderFactory.get(entityManager);
    List<Number> postRevisions = auditReader.getRevisions(Post.class, result.getId());
    assertThat(postRevisions).isNotNull();
    assertThat(postRevisions).hasSize(1);
  }
}
