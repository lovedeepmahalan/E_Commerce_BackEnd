package com.trigon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trigon.entity.OrderItem;
import com.trigon.repository.OrderItemRepository;

@Service
public class OrderItemServiceImpl implements OrderItemService{

	
	@Autowired
	OrderItemRepository orderItemRepo;
	
	@Override
	public OrderItem createOrderItem(OrderItem orderItem) {
		// TODO Auto-generated method stub
		return orderItemRepo.save(orderItem);
	}

}
