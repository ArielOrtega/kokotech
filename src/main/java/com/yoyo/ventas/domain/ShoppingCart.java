package com.yoyo.ventas.domain;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
	private int cartId;
	private Product product;
	private int quantity;
	private Client client;
	
	public ShoppingCart() {
		product = new Product();
		client = new Client();
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
}