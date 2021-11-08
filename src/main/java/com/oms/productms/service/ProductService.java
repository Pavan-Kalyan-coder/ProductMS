package com.oms.productms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oms.productms.dto.ProductDTO;
import com.oms.productms.dto.SubscribedProductDTO;
import com.oms.productms.entity.Product;
import com.oms.productms.entity.SubscribedProduct;
import com.oms.productms.exception.ProductException;
import com.oms.productms.repository.ProductRepository;
import com.oms.productms.repository.SubscribedProductRepository;
import com.oms.productms.validator.ProductValidator;

@Service
public class ProductService {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ProductRepository productRepo;

	@Autowired
	SubscribedProductRepository subscribedProductRepo;
	
	// Fetches Products by Product Name
	public List<ProductDTO> getAllProductsByName(String name) {
		List<ProductDTO> productdtos = new ArrayList<>();
		List<Product> products =  productRepo.findByProductName(name);
		for(Product product : products)
		{
			productdtos.add(ProductDTO.entityToDTO(product));
		}
		return productdtos;
	}

	// Fetches Products by Product category
	public List<ProductDTO> getAllProductsByCategory(String category) {
		List<ProductDTO> productdtos = new ArrayList<>();
		List<Product> products =  productRepo.findByCategory(category);
		for(Product product : products)
		{
			productdtos.add(ProductDTO.entityToDTO(product));
		}
		return productdtos;
	}
	
	// Fetches All Products
	public List<ProductDTO> getAllProducts() {
		List<ProductDTO> productdtos = new ArrayList<>();
		List<Product> products =  (List<Product>) productRepo.findAll();
		for(Product product : products)
		{
			productdtos.add(ProductDTO.entityToDTO(product));
		}
		return productdtos;
	}
	
	//fetch a product by productID
	public ProductDTO getProductByID(String productID) throws ProductException {
		Optional<Product> optionalProduct;
		optionalProduct = productRepo.findById(productID);
		Product product=null;
		product = optionalProduct.orElse(null);
		if(product == null)
		throw new ProductException("Product doesn't exists");
		return ProductDTO.entityToDTO(product);
	}

	// saves product to repository
	public String addProduct(ProductDTO productDTO) throws ProductException {
		
		ProductValidator.validateProduct(productDTO);
		
		Product product = ProductDTO.dtoToEntity(productDTO);
		productRepo.save(product);
		return "Product successfully created";
	}

	// buyer subscribe to product, and updating stock accordingly
	public String subscribeProduct(SubscribedProductDTO subscribedProductDTO) throws ProductException {
		
		ProductDTO productDTO = getProductByID(subscribedProductDTO.getProductID());
		if(productDTO.getStock() >= subscribedProductDTO.getQuantity())
		{
			SubscribedProduct subscribedProduct;
			subscribedProduct = SubscribedProductDTO.dtoToEntity(subscribedProductDTO);
			updateStock(subscribedProductDTO.getProductID(), subscribedProductDTO.getQuantity());
			subscribedProductRepo.save(subscribedProduct);
			return "subscription done";
		}
		else
		{
			throw new ProductException("Product stock is less than Quantity");
		}
	}

	public void updateStock(String productID, int quantity) throws ProductException {

		ProductDTO productDTO;
		productDTO = getProductByID(productID);
		Product product;
		product = ProductDTO.dtoToEntity(productDTO);
		product.setStock(product.getStock() - quantity);
		productRepo.save(product);
	}

	// delete product
	public String deleteProduct(String productID) throws ProductException {
		
		List<SubscribedProduct> subscriptions = subscribedProductRepo.findByProductID(productID);
		for(SubscribedProduct subscribedProduct : subscriptions)
		{
			subscribedProductRepo.delete(subscribedProduct);
		}
		
		Optional<Product> optionalProduct = productRepo.findById(productID);
		
		Product product = optionalProduct.orElse(null);
		if(product == null)
		{
			throw new ProductException("Product doesn't exists");
		}
		productRepo.deleteById(productID);
		return "Product deleted";
	}

	public String useStockOfProd(String productID, Integer quantity) {
		Product product = productRepo.findById(productID).orElse(null);
		if(product.getStock()>=quantity) {
			product.setStock(product.getStock()-quantity);
			productRepo.save(product);
			return "Stock used";
		}
		return "LessStock";
	}

	public String changeStockOfProd(String productID, Integer quantity) {
		Product product = productRepo.findById(productID).orElse(null);
		if(product==null)
		{
			return "product not found";
		}
		product.setStock(quantity);
		productRepo.save(product);
		return "stock is set successfully";
	}

	public List<ProductDTO> getProductBySellerID(String sellerID) {
		List<ProductDTO> productdtos = new ArrayList<>();
		List<Product> products =  productRepo.findBySellerID(sellerID);
		for(Product product : products)
		{
			productdtos.add(ProductDTO.entityToDTO(product));
		}
		return productdtos;
	}

	public String deleteSubscriptionToProduct(String buyerID) {
		
		List<SubscribedProduct> subscriptions = subscribedProductRepo.findByBuyerID(buyerID);
		for(SubscribedProduct subscribedProduct : subscriptions)
		{
			subscribedProductRepo.delete(subscribedProduct);
		}
		return "deleted subscriptions";
	}
	
}
