package com.marcelsby.coffeeshopacl.api.controller;

import com.marcelsby.coffeeshopacl.api.model.input.CoffeeDTO;
import com.marcelsby.coffeeshopacl.domain.model.Coffee;
import com.marcelsby.coffeeshopacl.domain.service.CoffeeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequestMapping("coffees")
@AllArgsConstructor
@RestController
public class CoffeeController {

  private CoffeeService coffeeService;
  private ModelMapper modelMapper;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void create(@Valid @RequestBody CoffeeDTO newCoffee) {
    coffeeService.create(modelMapper.map(newCoffee, Coffee.class));
  }

  @PutMapping("{coffeeId}")
  public void update(@PathVariable UUID coffeeId,
                     @Valid @RequestBody CoffeeDTO updatedCoffee) {
    coffeeService.update(coffeeId, modelMapper.map(updatedCoffee, Coffee.class));
  }

  @GetMapping
  public List<Coffee> list() {
    return coffeeService.list();
  }

  @GetMapping("{coffeeId}")
  public Coffee find(@PathVariable UUID coffeeId) {
    return coffeeService.find(coffeeId);
  }

  @DeleteMapping("{coffeeId}")
  /* TODO: Excluir essa rota para não permitir a exclusão de cafés */
  public void delete(@PathVariable UUID coffeeId) {
    coffeeService.delete(coffeeId);
  }
}
