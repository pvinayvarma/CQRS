package com.demo.cqrs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.demo.cqrs.dto.ProductEvent;
import com.demo.cqrs.entity.Product;
import com.demo.cqrs.repository.ProductRepository;

@Service
public class ProductCommandService {
	
	@Autowired
	private ProductRepository repository;
	
	@Autowired
	private KafkaTemplate<String , Object> kafkaTemplate;
	
	public Product CreateProduct(ProductEvent productEvent) {
		Product productDO = repository.save(productEvent.getProduct());
		ProductEvent event = new ProductEvent("CreateProduct", productDO);
		kafkaTemplate.send("product-event-kafka-topic",event);
		return productDO;
		
	}
	
	
	public Product UpdateProduct(long id,ProductEvent productEvent) {
		Product existingProduct = repository.findById(id).get();
		Product newProduct = productEvent.getProduct();
		existingProduct.setName(newProduct.getName());
		existingProduct.setPrice(newProduct.getPrice());
		existingProduct.setDescripton(newProduct.getDescripton());
		Product productDO = repository.save(existingProduct);
		ProductEvent event = new ProductEvent("UpdateProduct", productDO);
		kafkaTemplate.send("product-event-kafka-topic",event);
		return productDO;
		
	}

}
