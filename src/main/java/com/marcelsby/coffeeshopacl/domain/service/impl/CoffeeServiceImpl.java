package com.marcelsby.coffeeshopacl.domain.service.impl;

import com.marcelsby.coffeeshopacl.domain.model.Coffee;
import com.marcelsby.coffeeshopacl.domain.repository.CoffeeRepository;
import com.marcelsby.coffeeshopacl.domain.service.CoffeeService;
import com.marcelsby.coffeeshopacl.exception.EmptySearchResultException;
import com.marcelsby.coffeeshopacl.exception.RecordNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
    Coffee coffeeToUpdate = find(coffeeId);

    coffeeToUpdate.setName(updatedCoffee.getName());
    coffeeToUpdate.setDescription(updatedCoffee.getDescription());
    coffeeToUpdate.setValue(updatedCoffee.getValue());

    coffeeRepository.save(coffeeToUpdate);
  }

  @Override
  public List<Coffee> list() {
    List<Coffee> searchResult = coffeeRepository.findAll();

    if (searchResult.isEmpty()) {
      throw new EmptySearchResultException();
    }

    return searchResult;
  }

  @Override
  public Coffee find(UUID coffeeId) {
    return coffeeRepository.findById(coffeeId)
            .orElseThrow(() -> new RecordNotFoundException(Coffee.class));
  }

  @Override
  public void delete(UUID coffeeId) {
    coffeeRepository.deleteById(coffeeId);
  }
}
