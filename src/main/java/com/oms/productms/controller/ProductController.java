package com.oms.productms.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.oms.productms.dto.ProductDTO;
import com.oms.productms.dto.SubscribedProductDTO;
import com.oms.productms.exception.ProductException;
import com.oms.productms.service.ProductService;

@RestController
public class ProductController {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	ProductService productService;
	
	@Autowired
	Environment environment;
	
	@Value("${buyer.uri}")
	String buyerUri;
	
	// Fetches Products by Product Name
	@GetMapping(value = "/products/productname/{name}",  produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProductDTO> getAllProductsByName(@PathVariable String name) {
		
		logger.info("Product request by Visitor by name {}", name);
		return productService.getAllProductsByName(name);
	}
	
	// Fetches Products by Product category
	@GetMapping(value = "/products/category/{category}",  produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProductDTO> getAllProductsByCategory(@PathVariable String category) {
		
		logger.info("Product request by Visitor by Product Category {}", category);
		return productService.getAllProductsByCategory(category);
	}
	
	// Fetches All Products
	@GetMapping(value = "/products/allproducts",  produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProductDTO> getAllProducts() {
		logger.info("All Products request by Visitor");
		return productService.getAllProducts();
	}
	
	// Fetches Product by Product id
	@GetMapping(value = "/products/productbyid/{productID}",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ProductDTO getProductByID(@PathVariable String productID) {
		logger.info("Product request by Visitor by Product ID {}", productID);
		try {
			return productService.getProductByID(productID);
		} catch (ProductException e) {
			logger.info(e.getMessage());
			return null;
		}
	}
	
	// Fetches Product by Seller Id
	@GetMapping(value = "/products/productbysellerid/{sellerID}",  produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProductDTO> getProductBySellerID(@PathVariable String sellerID) {
		logger.info("Product request by Visitor by Product SellerID {}", sellerID);
		return productService.getProductBySellerID(sellerID);
	}
	
	// buyer subscribe to product, and updating stock accordingly
	@PostMapping(value = "/products/subscribeproduct",  consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> subscribeProduct(@RequestBody SubscribedProductDTO subscribedProductDTO) {
		logger.info("subscribing a product", subscribedProductDTO);
		String result;
		try 
		{
			int points = new RestTemplate().getForObject(buyerUri+"/buyer/getrewardpoints/{buyerID}", Integer.class, subscribedProductDTO.getBuyerID());
			if(points<0)
			{
				if( (points * (-1) ) < 10000)
				{
					throw new ProductException("Buyer has Reward Points less than 10000.");
				}
				else 
				{
					throw new ProductException("Buyer has Reward points more than 10000, But buyer is not Privileged");
				}
			}
			logger.info(String.valueOf(points));
			result = productService.subscribeProduct(subscribedProductDTO);
			return new ResponseEntity<String>(result, HttpStatus.OK);
		} catch (ProductException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
		}
		
	}
	
	//seller add product
	@PostMapping(value = "/products/addproduct",  consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addProduct(@RequestBody ProductDTO productDTO) {
		logger.info("adding a product", productDTO);
		String result;
		try {
			result = productService.addProduct(productDTO);
			return new ResponseEntity<String>(result, HttpStatus.OK);
		} catch (ProductException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
		}
	}
	
	//seller delete product
	@DeleteMapping(value = "/products/deleteproduct/{productID}")
	public ResponseEntity<String> deleteProduct(@PathVariable String productID) {
		logger.info("deleting a product with product ID {}", productID);
		String result;
		try {
			
			new RestTemplate().delete(buyerUri+"/buyer/cart/remove/{productID}", productID);
			new RestTemplate().delete(buyerUri+"/buyer/wishlist/remove/{productID}", productID);
			
			
			
			result = productService.deleteProduct(productID);
			return new ResponseEntity<String>(result, HttpStatus.OK);
		} catch (ProductException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
		}
		
	}
	
	//use product stock
	@GetMapping(value = "/products/updateStock/{productID}/{quantity}")
	public ResponseEntity<String> useStock(@PathVariable String productID, @PathVariable Integer quantity){	
		logger.info("stock update for product {} by quantity {}", productID, quantity);
		try {
			String status = productService.useStockOfProd(productID,quantity);
			return new ResponseEntity<>(status,HttpStatus.ACCEPTED);
		}
		catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,environment.getProperty(e.getMessage()), e);
		}		
	}
	//change product stock
	@PutMapping(value = "/products/changeStock/{productID}/{quantity}")
	public ResponseEntity<String> changeStock(@PathVariable String productID, @PathVariable Integer quantity){	
		logger.info("stock update for product {} by quantity {}", productID, quantity);
		try 
		{
			String status = productService.changeStockOfProd(productID,quantity);
			return new ResponseEntity<>(status,HttpStatus.ACCEPTED);
		}
		catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,environment.getProperty(e.getMessage()), e);
		}		
	}
	
	//delete buyer then subscriptions to product (if any) gets deleted
	@DeleteMapping(value = "/products/deletesubscription/{buyerID}")
	public ResponseEntity<String> deleteSubscriptionToProduct(@PathVariable String buyerID) {
		logger.info("deleting a subscription to product for the buyer {}", buyerID);
		String result;
		result = productService.deleteSubscriptionToProduct(buyerID);
		return new ResponseEntity<String>(result, HttpStatus.OK);
		
	}
}
