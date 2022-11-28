package com.marcelsby.coffeeshopacl.domain.service;

import com.marcelsby.coffeeshopacl.api.model.input.OrderItemDTO;
import com.marcelsby.coffeeshopacl.api.model.input.OrderItemUpdateDTO;
import com.marcelsby.coffeeshopacl.domain.model.Order;

import java.util.List;
import java.util.UUID;

public interface OrderService {

  /**
   * Cria um pedido com o status pendente.
   *
   * @return o pedido criado.
   */
  Order create();

  /**
   * Retorna uma lista com todos os pedidos cadastrados.
   *
   * @return lista com todos os pedidos cadastrados.
   */
  List<Order> list();

  /**
   * Busca um pedido específico.
   *
   * @param orderId o id do pedido que será buscado.
   * @return o pedido buscado ou EntityNotFoundException.
   */
  Order find(UUID orderId);

  /**
   * Adiciona um novo item ao pedido.
   *
   * @param orderId    id do pedido que o item será adicionado.
   * @param newItemDTO item que será adicionado.
   */
  void addItem(UUID orderId, OrderItemDTO newItemDTO);

  /**
   * Atualiza um item do pedido.
   *
   * @param orderId        id do pedido que será realizada a ação.
   * @param itemId         id do item que será atualizado.
   * @param updatedItemDTO conteúdo do item atualizado
   */
  void updateItem(UUID orderId, UUID itemId, OrderItemUpdateDTO updatedItemDTO);

  /**
   * Exclui um item do pedido.
   *
   * @param orderId id do pedido que será realizada a ação.
   * @param itemId  id do item que será excluído.
   */
  void deleteItem(UUID orderId, UUID itemId);

  /**
   * Cancela um pedido.
   */
  void cancel(UUID orderId);

  /**
   * Envia um pedido para ser preparado.
   */
  void submit(UUID orderId);


  /**
   * Finaliza um pedido
   */
  void finish(UUID orderId);
}
