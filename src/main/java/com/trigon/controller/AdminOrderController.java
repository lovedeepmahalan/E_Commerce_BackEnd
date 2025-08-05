package com.trigon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trigon.authresponse.ApiResponse;
import com.trigon.entity.Order;
import com.trigon.exception.OrderException;
import com.trigon.service.OrderService;

@RestController
@RequestMapping("api/admin/orders")
public class AdminOrderController {

	@Autowired
	private OrderService service;
	
	@GetMapping("/")
	public ResponseEntity<List<Order>> getAllOrderHandler(){
		List<Order> orders=service.getAllOrder();
		return new ResponseEntity<List<Order>>(orders,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/{orderId}/confirmed")
	public ResponseEntity<Order> confirmOrderHandler(@PathVariable Long orderId,
			@RequestHeader("Authorization") String jwt) throws OrderException{
		Order order=service.confirmOrder(orderId);
		
		return new ResponseEntity<>(order,HttpStatus.OK);
	}
	
	@PutMapping("/{orderId}/ship")
	public ResponseEntity<Order> shippedOrderHandler(@PathVariable Long orderId,
			@RequestHeader("Authorization") String jwt) throws OrderException{
		Order order=service.shippedOrder(orderId);
		
		return ResponseEntity.ok(order);
	}
	
	@PutMapping("/{orderId}/deliver")
	public ResponseEntity<Order> deliverOrderHandler(@PathVariable Long orderId,
			@RequestHeader("Authorization") String jwt) throws OrderException{
		Order order=service.deliveredOrder(orderId);
		
		return ResponseEntity.ok(order);
	}
	
	@PutMapping("/{orderId}/cancel")
	public ResponseEntity<Order> cancelOrderHandler(@PathVariable Long orderId,
			@RequestHeader("Authorization") String jwt) throws OrderException{
		Order order=service.canceledOrder(orderId);
		
		return ResponseEntity.ok(order);
	}
	
	@PutMapping("/{orderId}/delete")
	public ResponseEntity<ApiResponse> deleteOrderHandler(@PathVariable Long orderId,
			@RequestHeader("Authorization") String jwt) throws OrderException{
		service.deleteOrder(orderId);
		
		ApiResponse response=new ApiResponse();
		response.setMessage("order deleted successfuly");
		response.setStatus(true);
		return ResponseEntity.ok(response);
	}
}
