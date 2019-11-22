package com.yoyo.ventas.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yoyo.ventas.data.ShoppingCartData;
import com.yoyo.ventas.domain.ShoppingCart;

@Service
public class ShoppingCartBusiness {
	@Autowired
	private ShoppingCartData shoppingCartData;
	
	public int saveShoppingCart(ShoppingCart shoppingCart) {
		return shoppingCartData.saveShoppingCart(shoppingCart);
	}
	
	public List<ShoppingCart> getShoppingCart(){
		return shoppingCartData.getShoppingCart();
	}
	
	public void removeProductCart(int productId) {
		shoppingCartData.removeProductCart(productId);
	}
	
	public float getTotalPrice(List<ShoppingCart> carts) {
		return shoppingCartData.getTotalPrice(carts);
	}
}
