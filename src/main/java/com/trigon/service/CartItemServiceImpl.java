package com.trigon.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	public CartItem updateCartItem(Long userId, Long id, Integer quantity) throws UserException, CartItemException {
		CartItem item=findCartItemById(id);
		User user=userService.findUserById(item.getUserId());
		if(user.getId().equals(userId)) {
			item.setQuantity(quantity);
			item.setPrice(item.getProduct().getPrice()*item.getQuantity());
			item.setDiscountedPrice(item.getProduct().getDiscountedPrice()*item.getQuantity());
		}
		 CartItem cartupdate=cartItemRepo.save(item);
		
	    Cart cart = cartRepo.findByUserId(userId);
		
		int totalPrice = 0;
	    int totalDiscountedPrice = 0;
	    int totalItem = 0;
	    for (CartItem items : cart.getCartItem()) {
	        totalPrice += items.getPrice();
	        totalDiscountedPrice += items.getDiscountedPrice();
	        totalItem += items.getQuantity();
	    }
	    cart.setTotalPrice(totalPrice);
	    cart.setTotalDiscountedPrice(totalDiscountedPrice);
	    cart.setTotalItem(totalItem);
	    cart.setDiscounte(totalPrice - totalDiscountedPrice);
	    cartRepo.save(cart);
		return  cartupdate;
	}

	@Override
	public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId) {
		
		return cartItemRepo.isCartItemExist(cart, product, size, userId);
	}

	@Override
	@Transactional
	public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException {
		CartItem cartItem=findCartItemById(cartItemId);
		
		User user=userService.findUserById(cartItem.getUserId());
		User userReq=userService.findUserById(userId);
		if(user.getId().equals(userReq.getId())) {
			System.out.println("cart Item id is :: "+cartItemId);
			System.out.println(cartItemId instanceof Long);
			cartItemRepo.deleteById(cartItemId);
			boolean exists = cartItemRepo.existsById(cartItemId);
			System.out.println("Deleted? " + !exists);
		}else {
			throw new UserException("You cannot remove another useritem");
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
