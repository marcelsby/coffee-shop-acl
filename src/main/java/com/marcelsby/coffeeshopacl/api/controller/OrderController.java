package com.marcelsby.coffeeshopacl.api.controller;

import com.marcelsby.coffeeshopacl.api.model.input.OrderItemDTO;
import com.marcelsby.coffeeshopacl.api.model.input.OrderItemUpdateDTO;
import com.marcelsby.coffeeshopacl.domain.model.Order;
import com.marcelsby.coffeeshopacl.domain.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequestMapping("orders")
@AllArgsConstructor
@RestController
public class OrderController {

  private OrderService orderService;

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  public Order create(/* TODO: Receber o customer_id e o seller_id */) {
    return orderService.create();
  }

  @GetMapping("{orderId}")
  public Order find(@PathVariable UUID orderId) {
    return orderService.find(orderId);
  }

  @GetMapping
  List<Order> list() {
    return orderService.list();
  }

  @PatchMapping("/{orderId}/cancel")
  public void cancel(@PathVariable UUID orderId) {
    orderService.cancel(orderId);
  }

  @PatchMapping("/{orderId}/submit")
  public void submit(@PathVariable UUID orderId) {
    orderService.submit(orderId);
  }

  @PatchMapping("{orderId}/items")
  public void addItem(@PathVariable UUID orderId, @Valid @RequestBody OrderItemDTO newItemDTO) {
    orderService.addItem(orderId, newItemDTO);
  }

  @PatchMapping("{orderId}/items/{itemId}")
  public void updateItem(@PathVariable UUID orderId, @PathVariable UUID itemId,
                         @Valid @RequestBody OrderItemUpdateDTO updatedItemDTO) {
    orderService.updateItem(orderId, itemId, updatedItemDTO);
  }

  @PatchMapping("{orderId}/items/{itemId}/delete")
  public void deleteItem(@PathVariable UUID orderId, @PathVariable UUID itemId) {
    orderService.deleteItem(orderId, itemId);
  }
}
