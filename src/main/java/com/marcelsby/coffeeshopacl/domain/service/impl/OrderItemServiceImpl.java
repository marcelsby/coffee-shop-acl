package com.marcelsby.coffeeshopacl.domain.service.impl;

import com.marcelsby.coffeeshopacl.api.model.input.OrderItemDTO;
import com.marcelsby.coffeeshopacl.api.model.input.OrderItemUpdateDTO;
import com.marcelsby.coffeeshopacl.domain.model.Coffee;
import com.marcelsby.coffeeshopacl.domain.model.Order;
import com.marcelsby.coffeeshopacl.domain.model.OrderItem;
import com.marcelsby.coffeeshopacl.domain.repository.OrderItemRepository;
import com.marcelsby.coffeeshopacl.domain.service.CoffeeService;
import com.marcelsby.coffeeshopacl.domain.service.OrderItemService;
import com.marcelsby.coffeeshopacl.exception.DomainException;
import com.marcelsby.coffeeshopacl.exception.RecordNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class OrderItemServiceImpl implements OrderItemService {

  private OrderItemRepository orderItemRepository;
  private CoffeeService coffeeService;

  @Override
  public void add(Order order, OrderItemDTO newItemDTO) {
    if (isCoffeeAlreadyRegisteredInOrder(newItemDTO.getCoffeeId(), order.getId())) {
      throw new DomainException("Exception.coffeeAlreadyRegisteredInOrder");
    }

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
    OrderItem itemToBeDeleted = find(itemId);

    orderItemRepository.delete(itemToBeDeleted);
  }

  private OrderItem find(UUID orderItemId) {
    return orderItemRepository.findById(orderItemId)
            .orElseThrow(() -> new RecordNotFoundException(OrderItem.class));
  }

  private boolean isCoffeeAlreadyRegisteredInOrder(UUID coffeeId, UUID orderId) {
    return orderItemRepository.existsByCoffeeIdAndOrderId(coffeeId, orderId);
  }
}
