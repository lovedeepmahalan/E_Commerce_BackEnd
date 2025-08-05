package com.trigon.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trigon.entity.Product;
import com.trigon.entity.Review;
import com.trigon.entity.User;
import com.trigon.exception.ProductException;
import com.trigon.repository.ProductRepository;
import com.trigon.repository.ReviewRepository;
import com.trigon.request.ReviewRequest;

@Service
public class ReviewServiceImpl implements ReviewService{

	@Autowired
	private ProductService productService;
	
	@Autowired
	private ReviewRepository reviewRepo;
	
	@Autowired
	private ProductRepository productRepo;
	@Override
	public Review createReview(ReviewRequest req, User user) throws ProductException {
		Product product=productService.getProductById(req.getProductId());
		Review review=new Review();
		review.setCreatedAt(LocalDateTime.now());
		review.setProduct(product);
		review.setUser(user);
		review.setReview(req.getReview());
		return reviewRepo.save(review);
	}

	@Override
	public List<Review> getAllReiew(Long productId) {
		// TODO Auto-generated method stub
		return reviewRepo.getAllProductReview(productId);
	}

}
