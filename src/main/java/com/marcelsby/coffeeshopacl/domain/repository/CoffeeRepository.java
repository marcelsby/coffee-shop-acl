package com.marcelsby.coffeeshopacl.domain.repository;

import com.marcelsby.coffeeshopacl.domain.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CoffeeRepository extends JpaRepository<Coffee, UUID> {
}
