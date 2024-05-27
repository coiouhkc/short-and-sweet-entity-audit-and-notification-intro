package me.abratuhi.demo.audit.repository;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@MappedSuperclass
public class AuditableEntity extends PanacheEntityBase {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column private String text;

  @Column(name = "created_by")
  private String createdBy;

  @Column(name = "created_at")
  private OffsetDateTime createdAt;

  @Column(name = "changed_by")
  private String changedBy;

  @Column(name = "changed_at")
  private OffsetDateTime changedAt;
}
