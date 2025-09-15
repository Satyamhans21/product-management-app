package com.gspann.product_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gspann.product_service.model.Product;
import com.gspann.product_service.services.ProductService;

@RestController
@RequestMapping("/api")
public class API {

	@Autowired
	private ProductService service;

	@PostMapping("/product")
	public ResponseEntity<Product> handleCreate(@RequestBody Product product) {
		Product saved = this.service.save(product);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(saved);
	}

	@GetMapping("/product/{id}")
	public ResponseEntity<Product> handleGetOneProduct(@PathVariable int id) {
		Product readOne = this.service.getOneProductById(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(readOne);
	}

	@GetMapping("/products")
	public ResponseEntity<List<Product>> handleAllProducts() {
		List<Product> allProducts = this.service.getAllProducts();
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(allProducts);
	}

	@PutMapping("/product/{id}")
	public ResponseEntity<Product> handleUpdate(@PathVariable int id, @RequestBody Product product) {
		Product updateOrSaveProduct = this.service.updateOrSaveProduct(id, product);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(updateOrSaveProduct);
	}

	@DeleteMapping("/product/{id}")
	public ResponseEntity<?> handleDelete(@PathVariable int id) {
		this.service.deleteOneProduct(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Deleted");
	}

	@DeleteMapping("/product/permanent/{id}")
	public ResponseEntity<?> handlePermanentDelete(@PathVariable int id) {
		this.service.deletePermanentOneProduct(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Deleted");
	}

}
