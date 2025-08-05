package com.trigon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.trigon.entity.Rating;

public interface RatingRepository extends JpaRepository<Rating, Long>{

	@Query("select r from Rating r where r.product.id=:productId")
	public List<Rating> getAllProductsRating(Long productId);
}
