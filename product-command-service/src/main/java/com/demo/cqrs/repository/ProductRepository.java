package com.demo.cqrs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.cqrs.entity.Product;

public interface ProductRepository  extends JpaRepository<Product, Long>{

}
