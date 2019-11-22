package com.yoyo.ventas.domain;

import java.awt.Image;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Product {
	private int productId;
	private String productName;
	private String description;
	private float price;
	private int stockUnits;
	private String[] images;
	private Category category;
	
	public Product() {
		images = new String[3];
		category = new Category();
	}	

	public Product(String productName, String description, float price, int stockUnits,
			String[] images, Category category) {

		this.productName = productName;
		this.description = description;
		this.price = price;
		this.stockUnits = stockUnits;
		this.images = images;
		this.category = category;
	}
	
	public Product(int productId, String productName, String description, float price, int stockUnits,
			String[] images, Category category) {

		this.productId = productId;
		this.productName = productName;
		this.description = description;
		this.price = price;
		this.stockUnits = stockUnits;
		this.images = images;
		this.category = category;
	}	

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getStockUnits() {
		return stockUnits;
	}

	public void setStockUnits(int stockUnits) {
		this.stockUnits = stockUnits;
	}

	public String[] getImages() {
		return images;
	}

	public void setImages(String[] images) {
		this.images = images;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	
	
}
