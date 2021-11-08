package com.oms.productms.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.oms.productms.entity.SubscribedProduct;
import com.oms.productms.entity.SubscribedProductPK;

@Repository
public interface SubscribedProductRepository extends CrudRepository<SubscribedProduct, SubscribedProductPK>{

	List<SubscribedProduct> findByBuyerID(String buyerID);

	List<SubscribedProduct> findByProductID(String productID);
	
}
