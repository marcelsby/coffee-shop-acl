package com.marcelsby.coffeeshopacl.domain.service;

import com.marcelsby.coffeeshopacl.domain.model.Coffee;

import java.util.List;
import java.util.UUID;

public interface CoffeeService {

  /**
   * Cadastra um novo café.
   *
   * @param newCoffee dados para cadastrar o novo café.
   */
  void create(Coffee newCoffee);


  /**
   * Atualiza os dados de um café existente.
   *
   * @param coffeeId      o id do café que será atualizado.
   * @param updatedCoffee os dados para atualizar o café.
   */
  void update(UUID coffeeId, Coffee updatedCoffee);


  /**
   * Lista todos os cafés cadastrados.
   *
   * @return a lista dos cafés cadastrados.
   */
  List<Coffee> list();

  /**
   * Busca e retorna um café específico.
   *
   * @param coffeeId o id do café que será buscado.
   * @return o café que foi buscado ou EntityNotFoundException.
   */
  Coffee find(UUID coffeeId);


  /**
   * Deleta um café.
   *
   * @param coffeeId o id do café que será deletado.
   */
  void delete(UUID coffeeId);
}
