package me.abratuhi.demo.audit.controller;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import com.github.database.rider.cdi.api.DBRider;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.http.ContentType;
import me.abratuhi.demo.audit.repository.Post;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

@DBRider
@QuarkusTest
public class PostControllerTest {
  @Test
  void createOkAnon() {
    Post post = new Post();
    post.setText("My first post");

    given()
        .contentType(ContentType.JSON)
        .body(post)
        .when()
        .post("/post")
        .then()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
  }

  @Test
  void createOkAuthHeader() {
    Post post = new Post();
    post.setText("My first post");

    Post result =
        given()
            .contentType(ContentType.JSON)
            .auth()
            .basic("alice", "alice")
            .body(post)
            .when()
            .post("/post")
            .then()
            .statusCode(HttpStatus.SC_OK)
            .extract()
            .as(Post.class);

    assertThat(result).isNotNull();
    assertThat(result.getId()).isNotNull();
    assertThat(result.getText()).isEqualTo("My first post");
    assertThat(result.getCreatedBy()).isEqualTo("alice");
  }

  @TestSecurity(user = "alice")
  @Test
  void createOkAuthAnnotation() {
    Post post = new Post();
    post.setText("My first post");

    Post result =
        given()
            .contentType(ContentType.JSON)
            .body(post)
            .when()
            .post("/post")
            .then()
            .statusCode(HttpStatus.SC_OK)
            .extract()
            .as(Post.class);

    assertThat(result).isNotNull();
    assertThat(result.getId()).isNotNull();
    assertThat(result.getText()).isEqualTo("My first post");
    assertThat(result.getCreatedBy()).isEqualTo("alice");
  }
}
