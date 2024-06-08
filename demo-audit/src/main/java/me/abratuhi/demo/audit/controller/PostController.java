package me.abratuhi.demo.audit.controller;

import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.SecurityContext;
import me.abratuhi.demo.audit.repository.Post;
import me.abratuhi.demo.audit.service.PostService;

@Authenticated
@Path("/post")
public class PostController {

  @Context
  SecurityContext securityContext;

  @Inject PostService postService;

  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @POST
  public Post createPost(Post post) {
    return postService.createPost(post);
  }

  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @PUT
  @Path("/{id}")
  public Post updatePost(@PathParam("id") int id, Post post) {
    Post stored = postService.findPostById(id);

    if (stored == null) {
      throw new NotFoundException();
    }

    stored.setText(post.getText());

    return postService.updatePost(stored);
  }
}
