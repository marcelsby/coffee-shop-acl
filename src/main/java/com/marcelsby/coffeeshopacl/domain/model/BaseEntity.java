package com.marcelsby.coffeeshopacl.domain.model;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@MappedSuperclass
public class BaseEntity {

  @Id
  private UUID id;
  @Column(name = "created_at")
  private LocalDateTime createdAt;

  public BaseEntity() {
    this.id = UUID.randomUUID();
    this.createdAt = LocalDateTime.now();
  }
}
