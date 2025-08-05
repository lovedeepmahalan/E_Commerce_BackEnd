package com.trigon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trigon.authresponse.ApiResponse;
import com.trigon.entity.CartItem;
import com.trigon.entity.User;
import com.trigon.exception.CartItemException;
import com.trigon.exception.UserException;
import com.trigon.service.CartItemService;
import com.trigon.service.UserService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/cartItem")
public class CartItemController {


	@Autowired
	CartItemService cartItemService;
	
	@Autowired
	UserService userService;

    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable Long cartIemId,
    		@RequestHeader("Authorization")String jwt)throws UserException,CartItemException{
    	User user=userService.findUserProfileByjwt(jwt);
    	cartItemService.removeCartItem(user.getId(), cartIemId);
    	ApiResponse res=new ApiResponse("Item Deleted from cart",true);
    	return ResponseEntity.ok(res);
    }
	
    @PutMapping("/{cartItemId}")
    public ResponseEntity<CartItem> upadateCartItem(
    		@RequestBody CartItem cartItem,
    		@PathVariable Long cartItemId,
    		@RequestHeader("Authorization")String jwt) throws UserException,CartItemException{
    		User user=userService.findUserProfileByjwt(jwt);
    		CartItem updatedCartItem=cartItemService.updateCartItem(user.getId(), cartItemId, cartItem);
    		return ResponseEntity.ok(updatedCartItem);
    	}
    
}
