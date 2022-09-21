package com.marcelsby.coffeeshopacl.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@MappedSuperclass
public class BaseEntity {

  public BaseEntity() {
    this.id = UUID.randomUUID();
  }

  @Id
  private UUID id;

  @Setter
  @Column(name = "created_at")
  private LocalDateTime createdAt;
}
