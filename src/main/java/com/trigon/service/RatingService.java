package com.trigon.service;

import java.text.ParseException;
import java.util.List;

import com.trigon.entity.Rating;
import com.trigon.entity.User;
import com.trigon.exception.ProductException;
import com.trigon.request.RatingRequest;

public interface RatingService {

	public Rating createRating(RatingRequest req,User user) throws ParseException, ProductException;
	
	public List<Rating> getProductsRating(Long productId);
	
}
