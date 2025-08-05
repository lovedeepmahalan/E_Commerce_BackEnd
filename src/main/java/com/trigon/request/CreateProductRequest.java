package com.trigon.request;

import java.util.HashSet;
import java.util.Set;

import com.trigon.entity.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductRequest {

	
	
	private String title;
	private String description;
	private int discountedPrice;
	private int price;
	private int discountPersent;
	private int quantity;
	private String brand;
	private String color;
	private String imageUrl;
	private Set<Size> size=new HashSet<Size>();
	private String topLavelCategory;
	private String secondLavelCategory;
	private String thirdLavelCategory;
	
	@Override
	public String toString() {
		return "CreateProductRequest [title=" + title + ", description=" + description + ", discountPrice="
				+ discountedPrice + ", price=" + price + ", discountPercent=" + discountPersent + ", quantity=" + quantity
				+ ", brand=" + brand + ", color=" + color + ", imgURL=" + imageUrl+ ", size=" + size
				+ ", topLavelCategory=" + topLavelCategory + ", secondLavelCategory=" + secondLavelCategory
				+ ", thirdLavelCategory=" + thirdLavelCategory + "]";
	}
	
	
}
