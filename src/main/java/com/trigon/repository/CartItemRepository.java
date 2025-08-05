package com.trigon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.trigon.entity.Cart;
import com.trigon.entity.CartItem;
import com.trigon.entity.Product;

public interface CartItemRepository extends JpaRepository<CartItem, Long>{

	@Query("select ci from CartItem ci where ci.cart=:cart and ci.product=:product and ci.size=:size and ci.userId=:userId")
	public CartItem isCartItemExist(Cart cart,Product product,String size,Long userId);
}
