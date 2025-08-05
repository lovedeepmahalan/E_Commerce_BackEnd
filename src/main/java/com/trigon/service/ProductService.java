package com.trigon.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.trigon.entity.Product;
import com.trigon.exception.ProductException;
import com.trigon.request.CreateProductRequest;

public interface ProductService {

	public Product createProduct(CreateProductRequest crp);
	
	public String deleteProduct(Long id) throws ProductException;
	
	public Product updateProduct(Long id ,Product product)throws ProductException;
	
	public Product getProductById(Long id) throws ProductException;
	
	public List<Product> findProductByCategory(String category);
	
	public Page<Product> getAllProduct(String category,List<String> colors, List<String>size,Integer minPrice,Integer maxPrice,Integer minDiscount,
			String sort,String stock,Integer pageNumber,Integer pageSize);

	public List<Product> findAllProducts();
}
