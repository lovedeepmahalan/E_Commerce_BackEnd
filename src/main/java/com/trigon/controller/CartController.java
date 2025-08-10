package com.trigon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trigon.authresponse.ApiResponse;
import com.trigon.entity.Cart;
import com.trigon.entity.User;
import com.trigon.exception.ProductException;
import com.trigon.exception.UserException;
import com.trigon.request.AddItemRequest;
import com.trigon.service.CartService;
import com.trigon.service.UserService;

@RestController
@RequestMapping("/api/cart")
public class CartController {

	@Autowired
	CartService cartService;
	
	@Autowired
	UserService userService;
	
	@GetMapping("/")
	public ResponseEntity<Cart> findUserCart(@RequestHeader("Authorization") String jwt) throws UserException{
	
		User user=userService.findUserProfileByjwt(jwt);
		Cart cart=cartService.findUserCart(user.getId());
		System.out.println("cartis ::"+ cart);
		return ResponseEntity.ok(cart);
	}
	
	@PutMapping("/add")
	public ResponseEntity<ApiResponse> addItemToCart(@RequestBody AddItemRequest req,
			@RequestHeader("Authorization") String jwt) throws UserException, ProductException{
	
		User user=userService.findUserProfileByjwt(jwt);
		cartService.addCartItem(user.getId(), req);
		ApiResponse response=new ApiResponse("item added to cart",true);
		return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
	}	
}
