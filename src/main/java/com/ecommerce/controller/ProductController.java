package com.ecommerce.controller;

import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.model.Product;
import com.ecommerce.service.ProductService;

@RestController
@RequestMapping(value="/product",produces= {MediaType.APPLICATION_JSON_VALUE})
public class ProductController {
	@Autowired
	private ProductService productService;
	
	//all products
	@RequestMapping( value="",method=RequestMethod.GET)
	@ResponseBody
	public Iterable<Product> getProductsList() {
		return productService.getProductsList();
	}
	
	//product by id
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Optional<Product> productById(@PathVariable("id") int id){
		return productService.getProductById(id);
	}
	
	//add product
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public void addNewProduct(@RequestBody Product product) {
		productService.addNewProduct(product);
	}
	//remove product
	@RequestMapping(value="/remove/{id}",method=RequestMethod.DELETE) 
	public boolean removeProduct(@PathVariable("id") int id) {
		if(productService.deleteProduct(id)!=true) {
			return false;
		}else 
			return true;
	}
	//update Product
	@RequestMapping(value="/update/{id}",method=RequestMethod.PUT) 
	public boolean updateProductById(@PathVariable("id") int id,@RequestBody Product product) {
		
		if(productService.updateProductById(id,product)!=null ) {
			return true;
		}else {
			return false;
		}

	}
}
