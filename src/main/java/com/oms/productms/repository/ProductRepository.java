package com.oms.productms.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.oms.productms.entity.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, String>{

	public List<Product> findByCategory(String category);

	public List<Product> findByProductName(String productName);

	public List<Product> findBySellerID(String sellerID);
	
}
