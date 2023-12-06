package com.demo.cqrs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.demo.cqrs.dto.ProductEvent;
import com.demo.cqrs.entity.Product;
import com.demo.cqrs.repository.ProductRepository;

@Service
public class ProductQueryService {
	
	@Autowired
	private ProductRepository repository;
	
	
	public List<Product> getProducts(){
		return repository.findAll();
	}
	
	@KafkaListener(topics = "product-event-kafka-topic",groupId = "product-event-group")
	public void processProductEvents(ProductEvent productEvent) {
		
		Product product = productEvent.getProduct();
		System.out.println("event : " +productEvent.getEventType());
		if(productEvent.getEventType().equals("CreateProduct")) {
			repository.save(product);
		}
		if(productEvent.getEventType().equals("UpdateProduct")) {
			Product existingProduct = repository.findById((long) product.getId()).get();
			existingProduct.setName(product.getName());
			existingProduct.setPrice(product.getPrice());
			existingProduct.setDescripton(product.getDescripton());
			repository.save(existingProduct);
		}
	}
}
