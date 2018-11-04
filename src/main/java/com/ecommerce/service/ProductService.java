package com.ecommerce.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.ecommerce.model.Product;
import com.ecommerce.repository.ProductRepository;

@Service
public class ProductService {
	private static final Logger LOG=LoggerFactory.getLogger(ProductService.class);
	@Autowired
	private ProductRepository productRepository;
	
	//find by id
	public Iterable<Product> getProductsList(){
		LOG.info("getProductsList()");
		return productRepository.findAll();
	}
	//find all productList
	public Optional<Product> getProductById(int id){
		if(productRepository.existsById(id)!=true) {
			LOG.info("product id : "+id+"doesn't exists");
		}
		return productRepository.findById(id);
	}
	//add product
	public Product addNewProduct(Product product) {
		LOG.info("new Product has been addded"+product);
		return productRepository.save(product);
	}
	//delete product
	public boolean deleteProduct(int id) {
		if(productRepository.existsById(id)) {
		 productRepository.deleteById(id);
		 LOG.info("product id : "+id+" has been deleted");
		 return true;
		}
		else {
			LOG.info("product id : "+id+"doesn't exists");
			return false;
		}
	}
	//update product
	public Product updateProductById(int id,Product product) {
			if(productRepository.existsById(id)){
				//id should't come from front end
				product.setId(id);
				LOG.info("product "+id+" has been updated with "+product);
				return productRepository.save(product);
			}else {
				LOG.info("product id : "+id+"doesn't exists");
				return null;
			}
		
	}
	
}
