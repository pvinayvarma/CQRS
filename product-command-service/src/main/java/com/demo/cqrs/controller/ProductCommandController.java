package com.demo.cqrs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.cqrs.dto.ProductEvent;
import com.demo.cqrs.entity.Product;
import com.demo.cqrs.service.ProductCommandService;

@RestController
@RequestMapping("/products")
public class ProductCommandController {
	
	@Autowired
	private ProductCommandService commandService;
	
	@PostMapping
	public Product createProduct(@RequestBody ProductEvent productEvent) {
		return commandService.CreateProduct(productEvent);
	}
	
	@PutMapping("/{id}")
	public Product UpdateProduct(@PathVariable long id,@RequestBody ProductEvent productEvent) {
		return commandService.UpdateProduct(id,productEvent);
	}

}
