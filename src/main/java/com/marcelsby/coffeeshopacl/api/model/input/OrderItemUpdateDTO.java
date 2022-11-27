package com.marcelsby.coffeeshopacl.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Positive;

@Getter
@Setter
public class OrderItemUpdateDTO {

  @Positive
  private int amount;
}
