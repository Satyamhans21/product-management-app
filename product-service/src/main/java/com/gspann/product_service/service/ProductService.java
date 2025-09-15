package com.gspann.product_service.service;

import com.gspann.product_service.entity.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(Product product);
    List<Product> getAllProducts();
    Product findProductById(Long id);
    Product updateProduct(Long id, Product product);
    void deleteProduct(Long id);
}
