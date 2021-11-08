package com.oms.productms.entity;

import java.io.Serializable;

public class SubscribedProductPK implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String buyerID;
    private String productID;

    public SubscribedProductPK() {}

    public SubscribedProductPK(String buyerID, String productID) {
        this.setBuyerID(buyerID);
        this.setProductID(productID);
    }
    // equals, hashCode

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
}
