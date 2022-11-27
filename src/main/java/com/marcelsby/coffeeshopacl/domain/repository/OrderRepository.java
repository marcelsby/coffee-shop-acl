package com.marcelsby.coffeeshopacl.domain.repository;

import com.marcelsby.coffeeshopacl.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {

  boolean existsByIdAndStatus(UUID id, Order.Status status);
}
