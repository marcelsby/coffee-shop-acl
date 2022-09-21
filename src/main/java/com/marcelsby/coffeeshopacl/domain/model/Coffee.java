package com.marcelsby.coffeeshopacl.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "coffees")
public class Coffee extends BaseEntity {

  private String name;
  private String description;
  private float value;
}
