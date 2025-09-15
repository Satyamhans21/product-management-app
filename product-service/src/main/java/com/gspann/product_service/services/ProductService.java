package com.gspann.product_service.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import com.gspann.product_service.model.Product;
import com.gspann.product_service.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;

	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	// CREATE
	@CacheEvict(value = { "productsAll", "products" }, allEntries = true)
	public Product save(Product product) {
		return repository.save(product); // Always hits DB
	}

	// READ - All Products (CACHED)
	@Cacheable(value = "productsAll")
	public List<Product> getAllProducts() {
		return repository.findAll();
	}

	// ✅ READ - Single Product by ID (CACHED)
	@Cacheable(value = "products", key = "#id")
	public Product getOneProductById(int id) {
		return repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Product not found with id " + id));
	}

	// ✅ UPDATE (Evict cache to force fresh read next time)
	@CacheEvict(value = { "products", "productsAll" }, key = "#id", allEntries = true)
	public Product updateOrSaveProduct(int id, Product product) {
		product.setId(id);
		return repository.save(product); // Always hits DB
	}

	// SOFT DELETE (Evict cache)
	@CacheEvict(value = { "products", "productsAll" }, key = "#id", allEntries = true)
	public Product deleteOneProduct(int id) {
		Product product = repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Product not found with id " + id));
		product.setDeleted(true);
		product.setAvailable(false);
		product.setDeletedDate(LocalDateTime.now().format(FORMATTER));
		return repository.save(product);
	}

	// PERMANENT DELETE (Evict cache)
	@CacheEvict(value = { "products", "productsAll" }, key = "#id", allEntries = true)
	public void deletePermanentOneProduct(int id) {
		repository.findById(id).orElseThrow(() -> new RuntimeException("Product not found with id " + id));
		repository.deleteById(id);
	}

	// MANUAL CLEAR
	@CacheEvict(value = { "products", "productsAll" }, allEntries = true)
	public void clearAllCachesManually() {
		// Call this if you bulk modify data
	}
}
