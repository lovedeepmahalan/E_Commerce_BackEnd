package com.trigon.request;

import java.util.ArrayList;
import java.util.List;
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
	private int discountPercent;
	private int quantity;
	private String brand;
	private String color;
	private String imageUrl;
	private List<Size> size=new ArrayList<Size>();
	private String topLavelCategory;
	private String secondLavelCategory;
	private String thirdLavelCategory;
	
	@Override
	public String toString() {
		return "CreateProductRequest [title=" + title + ", description=" + description + ", discountedPrice="
				+ discountedPrice + ", price=" + price + ", discountPercent=" + discountPercent + ", quantity=" + quantity
				+ ", brand=" + brand + ", color=" + color + ", imageURL=" + imageUrl+ ", size=" + size
				+ ", topLavelCategory=" + topLavelCategory + ", secondLavelCategory=" + secondLavelCategory
				+ ", thirdLavelCategory=" + thirdLavelCategory + "]";
	}
	
	
}
