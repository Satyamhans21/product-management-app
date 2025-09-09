package com.gspann.product_service.service.impl;

import com.gspann.product_service.entity.Product;
import com.gspann.product_service.repository.ProductRepository;
import com.gspann.product_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
}
