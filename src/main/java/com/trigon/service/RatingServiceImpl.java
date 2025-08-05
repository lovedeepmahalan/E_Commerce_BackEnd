package com.trigon.service;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trigon.entity.Product;
import com.trigon.entity.Rating;
import com.trigon.entity.User;
import com.trigon.exception.ProductException;
import com.trigon.repository.RatingRepository;
import com.trigon.request.RatingRequest;

@Service
public class RatingServiceImpl implements RatingService{

	@Autowired
	ProductService prodService;
	
	@Autowired
	RatingRepository ratingRepo;
	
	@Override
	public Rating createRating(RatingRequest req, User user) throws  ProductException {
		Product product=prodService.getProductById(req.getId());
		
		Rating rating =new Rating();
		rating.setProduct(product);
		rating.setUser(user);
		rating.setRating(req.getRating());
		rating.setCreatedAt(LocalDateTime.now());
		
		return ratingRepo.save(rating);
	}

	@Override
	public List<Rating> getProductsRating(Long productId) {
		// TODO Auto-generated method stub
		return ratingRepo.getAllProductsRating(productId);
	}

}
