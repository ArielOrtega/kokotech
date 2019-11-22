package com.yoyo.ventas.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yoyo.ventas.business.ProductBusiness;
import com.yoyo.ventas.business.ShoppingCartBusiness;
import com.yoyo.ventas.domain.Product;
import com.yoyo.ventas.domain.ShoppingCart;
import com.yoyo.ventas.form.ShoppingCartForm;

@Controller
public class ShoppingCartController {
	@Autowired
	private ShoppingCartBusiness shoppingCartBusiness;
	@Autowired
	private ProductBusiness productBusiness;
	
	@RequestMapping(value="/store/shoppingCart/add", method=RequestMethod.GET)
	public String addToCartKeep(Model model, @Valid ShoppingCartForm shoppingCartForm, BindingResult br) {
		ShoppingCart shoppingCart = new ShoppingCart();
		BeanUtils.copyProperties(shoppingCartForm, shoppingCart);
		if(br.hasErrors()) {
			model.addAttribute("product", productBusiness.findProductById(shoppingCartForm.getProductId()));
			model.addAttribute("shoppingCartForm", new ShoppingCartForm());
			return "productDetails";
		}else {
			shoppingCart.getProduct().setProductId(shoppingCartForm.getProductId());
			shoppingCartBusiness.saveShoppingCart(shoppingCart);
			model.addAttribute("product", productBusiness.findProductById(shoppingCartForm.getProductId()));
			return "successProductAdded";
		}
	}
	
	@RequestMapping(value="/store/shoppingCart/check", method=RequestMethod.GET)
	public String checkShoppingCart(Model model) {
		List<ShoppingCart> carts = shoppingCartBusiness.getShoppingCart();
		model.addAttribute("carts", carts);
		float totalPrice = 0;
		for(int i = 0; i < carts.size(); i++) {
			totalPrice += (carts.get(i).getProduct().getPrice() * carts.get(i).getQuantity());
		}
		model.addAttribute("totalPrice", totalPrice);
		return "shoppingCart";
	}
	
	@RequestMapping(value="/store/shoppingCart/remove", method=RequestMethod.GET)
	public String removeProduct(Model model, @RequestParam("productId") int productId) {
		shoppingCartBusiness.removeProductCart(productId);
		List<ShoppingCart> carts = shoppingCartBusiness.getShoppingCart();
		model.addAttribute("carts", carts);
		model.addAttribute("totalPrice", shoppingCartBusiness.getTotalPrice(carts));
		return "shoppingCart";
	}
}
