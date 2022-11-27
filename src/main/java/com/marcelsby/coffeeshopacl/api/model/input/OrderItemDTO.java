package com.marcelsby.coffeeshopacl.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Positive;
import java.util.UUID;

@Getter
@Setter
public class OrderItemDTO {

  private UUID coffeeId;

  @Positive
  private int amount;
}
