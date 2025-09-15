package com.gspann.product_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gspann.product_service.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
