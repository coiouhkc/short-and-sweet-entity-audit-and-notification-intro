package me.abratuhi.demo.audit.repository;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "posts")
@Entity
@EntityListeners(value = {AuditListener.class})
public class Post extends AuditableEntity {
  @Column private String text;
}
