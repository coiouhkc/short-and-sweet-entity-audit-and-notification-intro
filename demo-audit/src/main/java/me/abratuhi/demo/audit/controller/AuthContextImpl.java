package me.abratuhi.demo.audit.controller;

import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class AuthContextImpl implements AuthContext {

  private String principal;

  @Override
  public String getPrincipal() {
    return this.principal;
  }

  public void setPrincipal(String principal) {
    this.principal = principal;
  }
}
