package com.marcelsby.coffeeshopacl.domain.service.impl;

import com.marcelsby.coffeeshopacl.domain.model.Coffee;
import com.marcelsby.coffeeshopacl.domain.repository.CoffeeRepository;
import com.marcelsby.coffeeshopacl.domain.service.CoffeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class CoffeeServiceImpl implements CoffeeService {

  private CoffeeRepository coffeeRepository;

  @Override
  public void create(Coffee newCoffee) {
    coffeeRepository.save(newCoffee);
  }

  @Override
  public void update(UUID coffeeId, Coffee updatedCoffee) {
    Coffee coffeeToUpdate = coffeeRepository.findById(coffeeId)
            .orElseThrow(EntityNotFoundException::new);

    coffeeToUpdate.setName(updatedCoffee.getName());
    coffeeToUpdate.setDescription(updatedCoffee.getDescription());
    coffeeToUpdate.setValue(updatedCoffee.getValue());

    coffeeRepository.save(coffeeToUpdate);
  }

  @Override
  public List<Coffee> list() {
    return coffeeRepository.findAll();
  }

  @Override
  public Coffee find(UUID coffeeId) {
    return coffeeRepository.findById(coffeeId)
            .orElseThrow(EntityNotFoundException::new);
  }

  @Override
  public void delete(UUID coffeeId) {
    coffeeRepository.deleteById(coffeeId);
  }
}
