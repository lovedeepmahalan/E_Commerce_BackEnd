package com.trigon.service;

import java.util.List;

import com.trigon.entity.Review;
import com.trigon.entity.User;
import com.trigon.exception.ProductException;
import com.trigon.request.ReviewRequest;

public interface ReviewService {

	public Review createReview(ReviewRequest req,User user) throws ProductException;
	public List<Review> getAllReiew(Long productId);
}
