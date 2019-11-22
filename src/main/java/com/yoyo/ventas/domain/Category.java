package com.yoyo.ventas.domain;

public class Category {
	private int categoryId;
	private String categoryName;
	private Tax tax;
	
	public Category() {
		tax = new Tax();
	}
		
	public Category(int categoryId, String categoryName) {

		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Tax getTax() {
		return tax;
	}

	public void setTax(Tax tax) {
		this.tax = tax;
	}
	
	
}
