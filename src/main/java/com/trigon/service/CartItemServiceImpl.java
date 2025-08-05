package com.trigon.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trigon.entity.Cart;
import com.trigon.entity.CartItem;
import com.trigon.entity.Product;
import com.trigon.entity.User;
import com.trigon.exception.CartItemException;
import com.trigon.exception.UserException;
import com.trigon.repository.CartItemRepository;
import com.trigon.repository.CartRepository;

@Service
public class CartItemServiceImpl implements  CartItemService{

	@Autowired
	CartItemRepository cartItemRepo;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CartRepository cartRepo;
	
	@Override
	public CartItem createCartItem(CartItem cartItem) {

		cartItem.setQuantity(1);
		cartItem.setPrice(cartItem.getProduct().getPrice()*cartItem.getQuantity());
		cartItem.setDiscountedPrice(cartItem.getProduct().getDiscountedPrice()*cartItem.getQuantity());
		CartItem createdCartItem=cartItemRepo.save(cartItem);
		return createdCartItem;
	}

	@Override
	public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws UserException, CartItemException {
		CartItem item=findCartItemById(id);
		User user=userService.findUserById(item.getUserId());
		if(user.getId().equals(userId)) {
			item.setQuantity(cartItem.getQuantity());
			item.setPrice(item.getProduct().getPrice()*item.getQuantity());
			item.setDiscountedPrice(item.getProduct().getDiscountedPrice()*item.getQuantity());
			
		}
		return cartItemRepo.save(item);
	}

	@Override
	public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId) {
		
		return cartItemRepo.isCartItemExist(cart, product, size, userId);
	}

	@Override
	public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException {
		CartItem cartItem=findCartItemById(cartItemId);
		
		User user=userService.findUserById(cartItem.getUserId());
		User userReq=userService.findUserById(userId);
		if(user.getId().equals(userReq.getId())) {
			cartItemRepo.deleteById(cartItemId);
		}else {
			throw new UserException("Yoy cannot remove another useritem");
		}
	}

	@Override
	public CartItem findCartItemById(Long cartItemId) throws CartItemException {
		Optional<CartItem> opt=cartItemRepo.findById(cartItemId);
		
		if(opt.isPresent()) {
			return opt.get();
		}
		throw new CartItemException("CartItem not present for given id");
	}

}
