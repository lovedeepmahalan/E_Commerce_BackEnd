package com.trigon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.trigon.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>{

	
	@Query("SELECT c FROM Cart c LEFT JOIN FETCH c.cartItem WHERE c.user.id = :userId")
	Cart findByUserId(Long userId);

}
