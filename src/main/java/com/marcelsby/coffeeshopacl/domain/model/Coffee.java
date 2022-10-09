package com.marcelsby.coffeeshopacl.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Accessors(chain = true)
@Table(name = "coffees")
@Entity
public class Coffee extends BaseEntity {

  private String name;
  private String description;
  private float value;
}
