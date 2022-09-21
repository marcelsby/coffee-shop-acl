package com.marcelsby.coffeeshopacl.api.controller;

import com.marcelsby.coffeeshopacl.api.model.CoffeeInputDTO;
import com.marcelsby.coffeeshopacl.domain.model.Coffee;
import com.marcelsby.coffeeshopacl.domain.repository.CoffeeRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("coffees")
@AllArgsConstructor
public class CoffeeController {

  private ModelMapper modelMapper;
  private CoffeeRepository coffeeRepository;

  @PostMapping
  public Coffee create(@RequestBody CoffeeInputDTO coffeeRequest) {
    Coffee newCoffee = modelMapper.map(coffeeRequest, Coffee.class);
    newCoffee.setCreatedAt(LocalDateTime.now());

    return coffeeRepository.save(newCoffee);
  }
}
