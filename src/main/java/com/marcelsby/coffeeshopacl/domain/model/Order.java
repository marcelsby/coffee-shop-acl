package com.marcelsby.coffeeshopacl.domain.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

  /* TODO: colocar os campos para guardar as referências para o cliente e o atendente */

  @Enumerated(EnumType.STRING)
  private Status status;

  @JsonManagedReference
  @OneToMany(fetch = FetchType.EAGER, mappedBy = "order")
  private List<OrderItem> orderItems;

  public enum Status {
    PENDING,
    PREPARING,
    FINISHED,
    CANCELED
  }
}
