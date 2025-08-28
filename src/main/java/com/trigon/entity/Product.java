package com.trigon.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String title;
	private String description;
	private int price;
	@Column(name="discount_price")
	private int discountedPrice;
	@Column(name="discount_percent")
	private int discountPersent;
	private int quantity;
	private String brand;
	private String color;
	
	@Embedded
	@ElementCollection
	@Column(name="sizes")
	private List<Size> size=new ArrayList<Size>();
	
	@Column(name="img_url")
	private String imageUrl;
	
	@OneToMany(mappedBy = "product",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Rating> rating=new ArrayList<Rating>();
	
	@OneToMany(mappedBy = "product",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Review> review=new ArrayList();
	
	private int numRating;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="category_id")
	private Category category;
	
	private LocalDateTime createdAt;

	
}