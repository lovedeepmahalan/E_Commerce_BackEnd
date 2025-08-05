package com.trigon.service;

import com.trigon.entity.Cart;
import com.trigon.entity.User;
import com.trigon.exception.ProductException;
import com.trigon.request.AddItemRequest;

public interface CartService {

	public Cart createCart(User user);
	
	public String addCartItem(Long userId,AddItemRequest req) throws ProductException;
	
	public Cart findUserCart(Long userId);
}
