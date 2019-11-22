package com.yoyo.ventas.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ShoppingCartForm {
	@NotNull
	private int productId;
	@Min(1)
	private int quantity;
	
	public ShoppingCartForm() {
		
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
