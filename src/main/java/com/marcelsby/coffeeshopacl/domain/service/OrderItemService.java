package com.marcelsby.coffeeshopacl.domain.service;

import com.marcelsby.coffeeshopacl.api.model.input.OrderItemDTO;
import com.marcelsby.coffeeshopacl.api.model.input.OrderItemUpdateDTO;
import com.marcelsby.coffeeshopacl.domain.model.Order;

import java.util.UUID;

public interface OrderItemService {

  void add(Order order, OrderItemDTO newItemDTO);

  void update(UUID itemId, OrderItemUpdateDTO updatedItemDTO);

  void delete(UUID itemId);
}
