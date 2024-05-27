package me.abratuhi.demo.audit.repository;

import jakarta.persistence.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.SecurityContext;
import lombok.extern.jbosslog.JBossLog;

import java.time.OffsetDateTime;

@JBossLog
public class AuditListener {
  @Context SecurityContext securityContext;

  @PrePersist
  public void onPrePersist(Object o) {
    log.infov("onPrePersist {0}", o);
    if (o instanceof AuditableEntity) {
      ((AuditableEntity) o).setCreatedAt(OffsetDateTime.now());
      ((AuditableEntity) o).setCreatedBy(getPrincipal());
    }
  }

  @PostPersist
  public void onPostPersist(Object o) {
    log.infov("onPostPersist {0}", o);
  }

  @PostLoad
  public void onPostLoad(Object o) {
    log.infov("onPostLoad {0}", o);
  }

  @PreUpdate
  public void onPreUpdate(Object o) {
    log.infov("onPreUpdate {0}", o);
    if (o instanceof AuditableEntity) {
      ((AuditableEntity) o).setChangedAt(OffsetDateTime.now());
      ((AuditableEntity) o).setChangedBy(getPrincipal());
    }
  }

  @PostUpdate
  public void onPostUpdate(Object o) {
    log.infov("onPostUpdate {}", o);
  }

  @PreRemove
  public void onPreRemove(Object o) {
    log.infov("onPreRemove {}", o);
    if (o instanceof AuditableEntity) {
      ((AuditableEntity) o).setChangedAt(OffsetDateTime.now());
      ((AuditableEntity) o).setChangedBy(getPrincipal());
    }
  }

  @PostRemove
  public void onPostRemove(Object o) {
    log.infov("onPostRemove {}", o);
  }

  private String getPrincipal() {
    return (securityContext != null && securityContext.getUserPrincipal() != null) ? securityContext.getUserPrincipal().getName() : "anonymous";
  }
}
