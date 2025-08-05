package com.trigon.service;

import java.util.List;

import com.trigon.entity.Address;
import com.trigon.entity.Order;
import com.trigon.entity.User;
import com.trigon.exception.OrderException;



public interface OrderService {

	public Order findOrderById(Long id) throws OrderException;
	
	public Order createOrder(User user,Address shippingAddress);
	
	public List<Order> usersOrderHistory(Long userId);
	
	public Order confirmOrder(Long orderId) throws OrderException;
	
	public Order placeOrder(Long orderId) throws OrderException;
	
	public Order shippedOrder(Long orderId) throws OrderException;
	
	public Order deliveredOrder(Long orderId) throws OrderException;
	
	public Order canceledOrder(Long orderId) throws OrderException;
	
	public List<Order> getAllOrder();
	
	public void deleteOrder(Long orderId) throws OrderException;
	
	
}
