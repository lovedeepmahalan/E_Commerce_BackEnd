package com.trigon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trigon.entity.Cart;
import com.trigon.entity.CartItem;
import com.trigon.entity.Product;
import com.trigon.entity.User;
import com.trigon.exception.ProductException;
import com.trigon.repository.CartRepository;
import com.trigon.request.AddItemRequest;

@Service
public class CartServiceImpl implements CartService{

	
	@Autowired
	private CartRepository cartRepo;
	
	@Autowired
	private CartItemService cartItemService;
	
	@Autowired 
	private ProductService productService;
	
	@Override
	public Cart createCart(User user) {
		Cart cart=new Cart();
		cart.setUser(user);
		return cartRepo.save(cart);
	}
	@Override
	public String addCartItem(Long userId, AddItemRequest req) throws ProductException {
	    Cart cart = cartRepo.findByUserId(userId);
	    Product product = productService.getProductById(req.getProductId());

	    CartItem isPresent = cartItemService.isCartItemExist(cart, product, req.getSize(), userId);

	    if (isPresent == null) {
	        CartItem cartItem = new CartItem();
	        cartItem.setProduct(product);
	        cartItem.setQuantity(req.getQuantity());
	        cartItem.setCart(cart);
	        cartItem.setUserId(userId);
	        cartItem.setSize(req.getSize());

	        CartItem createdCartItem = cartItemService.createCartItem(cartItem);
	        cart.getCartItem().add(createdCartItem);
	    }

	    // ✅ Update totals immediately
	    int totalPrice = 0;
	    int totalDiscountedPrice = 0;
	    int totalItem = 0;
	    for (CartItem item : cart.getCartItem()) {
	        totalPrice += item.getPrice();
	        totalDiscountedPrice += item.getDiscountedPrice();
	        totalItem += item.getQuantity();
	    }

	    cart.setTotalPrice(totalPrice);
	    cart.setTotalDiscountedPrice(totalDiscountedPrice);
	    cart.setTotalItem(totalItem);
	    cart.setDiscounte(totalPrice - totalDiscountedPrice);

	    cartRepo.save(cart); // Save cart after updating totals

	    return "Item added to cart";
	}

	@Override
	public Cart findUserCart(Long userId) {
	    Cart cart = cartRepo.findByUserId(userId);
	    return cart;
	}

}
