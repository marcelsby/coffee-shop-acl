package com.marcelsby.coffeeshopacl.domain.service.impl;

import com.marcelsby.coffeeshopacl.api.model.input.OrderItemDTO;
import com.marcelsby.coffeeshopacl.api.model.input.OrderItemUpdateDTO;
import com.marcelsby.coffeeshopacl.domain.model.Order;
import com.marcelsby.coffeeshopacl.domain.repository.OrderRepository;
import com.marcelsby.coffeeshopacl.domain.service.OrderItemService;
import com.marcelsby.coffeeshopacl.domain.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

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
    return orderRepository.findAll();
  }

  @Override
  public Order find(UUID orderId) {
    return orderRepository.findById(orderId)
            .orElseThrow(EntityNotFoundException::new);
  }

  @Override
  public void cancel(UUID orderId) {
    Order orderToBeCanceled = find(orderId);

    switch (orderToBeCanceled.getStatus()) {
      case CANCELED:
        /* TODO: exception avisando que o pedido não pode ser cancelado, pois já está cancelado  */
        break;
      case FINISHED:
        /* TODO: exception avisando que o pedido não pode ser cancelado, pois já está finalizado */
        break;
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
        /* TODO: exception avisando que o pedido não pode ser enviado, pois está cancelado  */
        break;
      case FINISHED:
        /* TODO: exception avisando que o pedido não pode ser enviado, pois está finalizado */
        break;
      case PREPARING:
        /* TODO: exception avisando que o pedido não pode ser enviado, pois está em preparo */
        break;
      default:
        orderToBeSubmitted.setStatus(Order.Status.PREPARING);
        orderRepository.save(orderToBeSubmitted);
    }
  }

  @Override
  public void finish(UUID orderId) {
    Order orderToBeFinished = find(orderId);

    if (!orderToBeFinished.getStatus().equals(Order.Status.PREPARING)) {
      /* TODO: exception falando que não é possível fechar o pedido, pois ele não está no estado de preparação */
    } else {
      orderToBeFinished.setStatus(Order.Status.FINISHED);
      orderRepository.save(orderToBeFinished);
    }
  }

  @Override
  public void addItem(UUID orderId, OrderItemDTO newItemDTO) {
    Order orderToItemBeAdded = find(orderId);

    if (!orderToItemBeAdded.getStatus().equals(Order.Status.PENDING)) {
      orderItemService.add(orderToItemBeAdded, newItemDTO);
    } else {
      /* TODO: exception falando que não é possível adicionar um item pois o pedido não está no estado de pendente */
    }
  }

  @Override
  public void updateItem(UUID orderId, UUID itemId, OrderItemUpdateDTO updatedItemDTO) {
    if (isOrderPending(orderId)) {
      orderItemService.update(itemId, updatedItemDTO);
    } else {
      /* TODO: exception falando que não é possível adicionar um item pois o pedido não está no estado de pendente */
    }
  }

  @Override
  public void deleteItem(UUID orderId, UUID itemId) {
    if (isOrderPending(orderId)) {
      orderItemService.delete(itemId);
    } else {
      /* TODO: exception falando que não é possível adicionar um item pois o pedido não está no estado de pendente */
    }
  }

  private boolean isOrderPending(UUID orderId) {
    return orderRepository.existsByIdAndStatus(orderId, Order.Status.PENDING);
  }
}
