package com.trigon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trigon.entity.Address;
import com.trigon.entity.Order;
import com.trigon.entity.User;
import com.trigon.exception.OrderException;
import com.trigon.exception.UserException;
import com.trigon.service.OrderService;
import com.trigon.service.UserService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/")
	public ResponseEntity<Order> createOrder(@RequestBody Address shippingAddress,
			@RequestHeader("Authentication") String jwt) throws UserException{
		User user=userService.findUserProfileByjwt(jwt);
		Order order=orderService.createOrder(user, shippingAddress);
		
		return new ResponseEntity<Order>(order,HttpStatus.OK);
	}
	
	@GetMapping("/user")
	public ResponseEntity<List<Order>>usersOrderHistory(@RequestHeader("Authorization") String jwt) throws UserException{
		User user=userService.findUserProfileByjwt(jwt);
		List<Order> orders=orderService.usersOrderHistory(user.getId());
		return ResponseEntity.ok(orders);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Order> findOrderById(
			@PathVariable("id") Long orderId,
			@RequestHeader("Authentication") String jwt) throws OrderException, UserException{
		User user=userService.findUserProfileByjwt(jwt);
		Order order=orderService.findOrderById(orderId);
		return ResponseEntity.ok(order);
	}
	
	
}
