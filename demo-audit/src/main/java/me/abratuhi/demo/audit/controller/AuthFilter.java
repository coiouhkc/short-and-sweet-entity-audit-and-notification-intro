package me.abratuhi.demo.audit.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.SecurityContext;
import org.jboss.resteasy.reactive.server.ServerRequestFilter;

public class AuthFilter {
  @Context SecurityContext securityContext;
  @Inject AuthContextImpl authContext;

  @ServerRequestFilter(preMatching = true)
  public void filter() {
    authContext.setPrincipal(getPrincipal());
  }

  private String getPrincipal() {
    return (securityContext != null && securityContext.getUserPrincipal() != null)
        ? securityContext.getUserPrincipal().getName()
        : "anonymous";
  }
}
