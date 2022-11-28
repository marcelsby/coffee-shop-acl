package com.marcelsby.coffeeshopacl.domain.service.impl;

import com.marcelsby.coffeeshopacl.api.model.input.OrderItemDTO;
import com.marcelsby.coffeeshopacl.api.model.input.OrderItemUpdateDTO;
import com.marcelsby.coffeeshopacl.domain.model.Order;
import com.marcelsby.coffeeshopacl.domain.repository.OrderRepository;
import com.marcelsby.coffeeshopacl.domain.service.OrderItemService;
import com.marcelsby.coffeeshopacl.domain.service.OrderService;
import com.marcelsby.coffeeshopacl.exception.DomainException;
import com.marcelsby.coffeeshopacl.exception.EmptySearchResultException;
import com.marcelsby.coffeeshopacl.exception.RecordNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

  private static final String ORDER_NOT_PENDING_MESSAGE = "Exception.orderNotPending";
  private OrderRepository orderRepository;
  private OrderItemService orderItemService;

  @Override
  public Order create() {
    Order orderToBeCreated = new Order();
    orderToBeCreated.setStatus(Order.Status.PENDING);

    return orderRepository.save(orderToBeCreated);
  }

  @Override
  public List<Order> list() {
    List<Order> searchResult = orderRepository.findAll();

    if (searchResult.isEmpty()) {
      throw new EmptySearchResultException();
    }

    return searchResult;
  }

  @Override
  public Order find(UUID orderId) {
    return orderRepository.findById(orderId)
            .orElseThrow(() -> new RecordNotFoundException(Order.class));
  }

  @Override
  public void cancel(UUID orderId) {
    Order orderToBeCanceled = find(orderId);

    switch (orderToBeCanceled.getStatus()) {
      case CANCELED:
        throw new DomainException("Exception.orderCanceled");
      case FINISHED:
        throw new DomainException("Exception.orderFinished");
      default:
        orderToBeCanceled.setStatus(Order.Status.CANCELED);
        orderRepository.save(orderToBeCanceled);
    }
  }

  @Override
  public void submit(UUID orderId) {
    Order orderToBeSubmitted = find(orderId);

    switch (orderToBeSubmitted.getStatus()) {
      case CANCELED:
        throw new DomainException("Exception.orderCanceled");
      case FINISHED:
        throw new DomainException("Exception.orderFinished");
      case PREPARING:
        throw new DomainException("Exception.orderBeingPrepared");
      default:
        orderToBeSubmitted.setStatus(Order.Status.PREPARING);
        orderRepository.save(orderToBeSubmitted);
    }
  }

  @Override
  public void finish(UUID orderId) {
    Order orderToBeFinished = find(orderId);

    if (!orderToBeFinished.getStatus().equals(Order.Status.PREPARING)) {
      throw new DomainException("Exception.orderNotInPreparation");
    } else {
      orderToBeFinished.setStatus(Order.Status.FINISHED);
      orderRepository.save(orderToBeFinished);
    }
  }

  @Override
  public void addItem(UUID orderId, OrderItemDTO newItemDTO) {
    Order orderToItemBeAdded = find(orderId);

    if (isOrderPending(orderToItemBeAdded)) {
      orderItemService.add(orderToItemBeAdded, newItemDTO);
    } else {
      throw new DomainException(ORDER_NOT_PENDING_MESSAGE);
    }
  }

  @Override
  public void updateItem(UUID orderId, UUID itemId, OrderItemUpdateDTO updatedItemDTO) {
    Order orderToItemBeUpdated = find(orderId);

    if (isOrderPending(orderToItemBeUpdated)) {
      orderItemService.update(itemId, updatedItemDTO);
    } else {
      throw new DomainException(ORDER_NOT_PENDING_MESSAGE);
    }
  }

  @Override
  public void deleteItem(UUID orderId, UUID itemId) {
    Order orderToItemBeDeleted = find(orderId);

    if (isOrderPending(orderToItemBeDeleted)) {
      orderItemService.delete(itemId);
    } else {
      throw new DomainException(ORDER_NOT_PENDING_MESSAGE);
    }
  }

  private boolean isOrderPending(Order order) {
    return order.getStatus().equals(Order.Status.PENDING);
  }
}
