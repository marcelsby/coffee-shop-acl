package com.marcelsby.coffeeshopacl.api.model.input;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class CoffeeDTO {

  @NotNull
  @NotBlank
  private String name;

  @NotNull
  @NotBlank
  private String description;

  @Positive
  private float value;
}
