package com.marcelsby.coffeeshopacl.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "order_items")
public class OrderItem extends BaseEntity {

  @JsonBackReference
  @ManyToOne
  private Order order;

  @ManyToOne
  private Coffee coffee;

  private float value;

  private int amount;
}
