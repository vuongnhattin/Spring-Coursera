package com.tin.springcoursera.repository;

import com.tin.springcoursera.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Orders, Integer> {
    void deleteByToken(String token);

    Optional<Orders> findByToken(String token);
}
