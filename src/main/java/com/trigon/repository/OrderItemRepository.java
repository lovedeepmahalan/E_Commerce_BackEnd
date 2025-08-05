package com.trigon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trigon.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

}
