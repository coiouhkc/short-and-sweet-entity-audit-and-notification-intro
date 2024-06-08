package me.abratuhi.demo.audit.repository;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.*;
import java.time.OffsetDateTime;
import lombok.extern.jbosslog.JBossLog;
import me.abratuhi.demo.audit.controller.AuthContext;

@Dependent
@JBossLog
public class AuditListener {
  @Inject AuthContext authContext;

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
    return authContext.getPrincipal();
  }
}
