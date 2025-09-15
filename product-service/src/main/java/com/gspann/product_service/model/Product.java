package com.gspann.product_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String pName;
	private int pQuantity;
	private double pPrice;
	private double pDiscount;
	private Boolean available;
	private Boolean deleted;
	private String deletedDate;
}
