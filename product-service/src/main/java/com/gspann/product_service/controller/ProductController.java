package com.gspann.product_service.controller;

import com.gspann.product_service.entity.Product;
import com.gspann.product_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/product")
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        Product savedProduct=productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);

    }
}
