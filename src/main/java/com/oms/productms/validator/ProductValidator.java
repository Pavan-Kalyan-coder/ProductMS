package com.oms.productms.validator;

import com.oms.productms.dto.ProductDTO;
import com.oms.productms.exception.ProductException;

public class ProductValidator {
	
	public static void validateProduct(ProductDTO product) throws ProductException {
		
		if(!validateName(product.getProductName()))
			throw new ProductException("Enter Valid Product name that contains Only Letters");
		
		if(!validateDescription(product.getDescription()))
			throw new ProductException("Max Limit for Description is 500 characters");
			
		if(!validatePrice(product.getPrice()))
			throw new ProductException("Minimum price of a product should be 200");
		
		if(!validateStock(product.getStock()))
			throw new ProductException("Stock should be 10 atleast");
		
		if(!validateImage(product.getImage()))
			throw new ProductException("Image should be of either png or jpeg format");
		
	}
	
	public static boolean validateName(String name)
	{
		String regex = "([A-Za-z]+([ ][A-Za-z]+)*){1,100}";
		
		if(name.matches(regex))
		{
			return true;
		}
		return false;
	}
	
	public static boolean validateDescription(String desc)
	{
		String regex = "([A-Za-z0-9]+([ ][A-Za-z0-9]+)*){1,500}";
		
		if(desc.matches(regex))
		{
			return true;
		}
		return false;
	}
	
	public static boolean validatePrice(int price)
	{
		if(price >=200)
		{
			return true;
		}
		
		return false;
	}
	
	public static boolean validateStock(Integer stock)
	{
		if(stock>=10)
		{
			return true;
		}
		return false;
	}
	
	public static boolean validateImage(String image)
	{
		String regex = "[A-Za-z0-9]+[\\.](png|jpeg)";
		
		if(image.matches(regex))
			return true;

		return false;
	}

}