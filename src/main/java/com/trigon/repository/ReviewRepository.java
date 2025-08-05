package com.trigon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.trigon.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long>{

	@Query("select r from Review r where r.product.id=:productId")
	public List<Review> getAllProductReview(Long productId);
}
