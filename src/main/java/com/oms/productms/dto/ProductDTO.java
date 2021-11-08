package com.oms.productms.dto;

import com.oms.productms.entity.Product;

public class ProductDTO {
	String productID;
	String productName;
	int price;
	int stock;
	String description;
	String image;
	String sellerID;
	String category;
	String subCategory;
	float productRating;
	
	// Converts Entity into DTO
	public static ProductDTO entityToDTO(Product product) {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setProductID(product.getProductID());
		productDTO.setProductName(product.getProductName());
		productDTO.setPrice(product.getPrice());
		productDTO.setStock(product.getStock());
		productDTO.setDescription(product.getDescription());
		productDTO.setImage(product.getImage());
		productDTO.setSellerID(product.getSellerID());
		productDTO.setCategory(product.getCategory());
		productDTO.setSubCategory(product.getSubCategory());
		productDTO.setProductRating(product.getProductRating());
		
		return productDTO;
	}
	//Converts DTO into Entity
	public static Product dtoToEntity(ProductDTO productDTO) {
		Product product = new Product();
		product.setProductID(productDTO.getProductID());
		product.setProductName(productDTO.getProductName());
		product.setPrice(productDTO.getPrice());
		product.setStock(productDTO.getStock());
		product.setDescription(productDTO.getDescription());
		product.setImage(productDTO.getImage());
		product.setSellerID(productDTO.getSellerID());
		product.setCategory(productDTO.getCategory());
		product.setSubCategory(productDTO.getSubCategory());
		product.setProductRating(productDTO.getProductRating());
		
		return product;
	}
	
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
	public ProductDTO() {
		super();
	}
}
