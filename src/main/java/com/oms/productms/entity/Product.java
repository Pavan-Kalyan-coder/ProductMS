package com.oms.productms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product {
	
	@Id
	@Column(name = "product_id", nullable = false)
	String productID;
	@Column(nullable = false, length = 20)
	String productName;
	@Column(nullable = false)
	int price;
	@Column(nullable = false)
	int stock;
	@Column(nullable = false, length = 50)
	String description;
	@Column(nullable = false)
	String image;
	@Column(name = "seller_id", nullable = false)
	String sellerID;
	@Column(nullable = false, length = 50)
	String category;
	@Column(name = "sub_category", nullable = false, length = 50)
	String subCategory;
	@Column(name = "product_rating", nullable = false, length = 30)
	float productRating;
	public String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getSellerID() {
		return sellerID;
	}
	public void setSellerID(String sellerID) {
		this.sellerID = sellerID;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}
	public float getProductRating() {
		return productRating;
	}
	public void setProductRating(float productRating) {
		this.productRating = productRating;
	}
	
//	public Product() {
//		super();
//	}
}
