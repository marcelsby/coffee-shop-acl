package com.marcelsby.coffeeshopacl.domain.service.impl;

import com.marcelsby.coffeeshopacl.api.model.input.OrderItemDTO;
import com.marcelsby.coffeeshopacl.api.model.input.OrderItemUpdateDTO;
import com.marcelsby.coffeeshopacl.domain.model.Coffee;
import com.marcelsby.coffeeshopacl.domain.model.Order;
import com.marcelsby.coffeeshopacl.domain.model.OrderItem;
import com.marcelsby.coffeeshopacl.domain.repository.OrderItemRepository;
import com.marcelsby.coffeeshopacl.domain.service.CoffeeService;
import com.marcelsby.coffeeshopacl.domain.service.OrderItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@AllArgsConstructor
@Service
public class OrderItemServiceImpl implements OrderItemService {

  private OrderItemRepository orderItemRepository;
  private CoffeeService coffeeService;

  @Override
  public void add(Order order, OrderItemDTO newItemDTO) {
    Coffee coffeeToBeAdded = coffeeService.find(newItemDTO.getCoffeeId());

    OrderItem itemToBeCreated = new OrderItem();
    itemToBeCreated.setOrder(order);
    itemToBeCreated.setCoffee(coffeeToBeAdded);
    itemToBeCreated.setValue(coffeeToBeAdded.getValue());
    itemToBeCreated.setAmount(newItemDTO.getAmount());

    orderItemRepository.save(itemToBeCreated);
  }

  @Override
  public void update(UUID itemId, OrderItemUpdateDTO updatedItemDto) {
    OrderItem itemToBeUpdated = find(itemId);

    itemToBeUpdated.setAmount(updatedItemDto.getAmount());

    orderItemRepository.save(itemToBeUpdated);
  }

  @Override
  public void delete(UUID itemId) {
    orderItemRepository.deleteById(itemId);
  }

  private OrderItem find(UUID orderItemId) {
    return orderItemRepository.findById(orderItemId)
            .orElseThrow(EntityNotFoundException::new);
  }
}
