package com.trigon.service;

import com.trigon.entity.Cart;
import com.trigon.entity.CartItem;
import com.trigon.entity.Product;
import com.trigon.exception.CartItemException;
import com.trigon.exception.UserException;

public interface CartItemService {

	public CartItem createCartItem(CartItem cartItem);
	
	public CartItem updateCartItem(Long userId , Long id , CartItem cartItem) throws UserException,CartItemException;

	public CartItem isCartItemExist(Cart cart,Product product,String size,Long userId);
	
	public void removeCartItem(Long userId , Long cartItemId) throws CartItemException,UserException;
	
	public CartItem findCartItemById(Long cartItemId) throws CartItemException;

	
}