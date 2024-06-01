package me.abratuhi.demo.audit.repository;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "post")
@Entity
@EntityListeners(value = {AuditListener.class})
public class Post extends AuditableEntity {
  @Audited @Column private String text;
}
