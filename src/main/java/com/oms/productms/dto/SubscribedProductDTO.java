package com.oms.productms.dto;

import com.oms.productms.entity.SubscribedProduct;

public class SubscribedProductDTO {
	String buyerID;
	String productID;
	int quantity;
	
	public String getBuyerID() {
		return buyerID;
	}
	public void setBuyerID(String buyerID) {
		this.buyerID = buyerID;
	}
	public String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	// Converts Entity into DTO
	public static SubscribedProductDTO entityToDTO(SubscribedProduct subscribedProduct) {
		SubscribedProductDTO subscribedProductDTO = new SubscribedProductDTO();
		subscribedProductDTO.setBuyerID(subscribedProduct.getBuyerID());
		subscribedProductDTO.setProductID(subscribedProduct.getProductID());
		subscribedProductDTO.setQuantity(subscribedProduct.getQuantity());
		
		return subscribedProductDTO;
	}
	// Converts Entity into DTO
	public static SubscribedProduct dtoToEntity(SubscribedProductDTO subscribedProductDTO) {
		SubscribedProduct subscribedProduct = new SubscribedProduct();
		subscribedProduct.setBuyerID(subscribedProductDTO.getBuyerID());
		subscribedProduct.setProductID(subscribedProductDTO.getProductID());
		subscribedProduct.setQuantity(subscribedProductDTO.getQuantity());
		
		return subscribedProduct;
	}
	
	public SubscribedProductDTO() {
		super();
	}
}
