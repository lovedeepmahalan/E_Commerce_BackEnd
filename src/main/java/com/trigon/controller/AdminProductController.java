package com.trigon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trigon.authresponse.ApiResponse;
import com.trigon.entity.Product;
import com.trigon.exception.ProductException;
import com.trigon.request.CreateProductRequest;
import com.trigon.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/admin/products")
public class AdminProductController {

	
	@Autowired
	private ProductService productService;
	
	@PostMapping("/")
	public ResponseEntity<Product> createProduct(@RequestBody CreateProductRequest req){
		System.out.println("Object coming is :: "+req);
		Product product=productService.createProduct(req);
		return ResponseEntity.ok(product);
	}
	
	@PostMapping("/{productId}/delete")
	public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long productId) throws ProductException{
		productService.deleteProduct(productId);
		ApiResponse response=new ApiResponse();
		response.setMessage("product deleted successfuly");
		response.setStatus(true);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("path")
	public ResponseEntity<List<Product>> findAllProduct() {
	
		List<Product> list=productService.findAllProducts();
		
		return ResponseEntity.ok(list);
	}
	
	@PutMapping("/{productId}/update")
	public ResponseEntity<Product> updateProduct(@RequestBody Product req,@PathVariable Long productId) throws ProductException{
		Product product=productService.updateProduct(productId, req);
		return ResponseEntity.ok(product);
	}
	
	@PostMapping("/creates")
	public ResponseEntity<ApiResponse> createMultipleProduct(@RequestBody CreateProductRequest[] req){
		for(CreateProductRequest product:req) {
			productService.createProduct(product);
		}
		ApiResponse response=new ApiResponse();
		response.setMessage("product added successfuly");
		response.setStatus(true);
		return ResponseEntity.ok(response);
	}
	
}
