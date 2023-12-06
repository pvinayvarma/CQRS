package com.demo.cqrs.dto;

import com.demo.cqrs.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductEvent {

	private String eventType; 
	private Product product;
}
