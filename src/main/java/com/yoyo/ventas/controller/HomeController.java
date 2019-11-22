package com.yoyo.ventas.controller;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping(value="/home")
	public String iniciar() {
		//Routing - enrutamiento
		Collection<? extends GrantedAuthority> authorities;
		Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
		authorities = authentication.getAuthorities();
		boolean isCostumer = false;
		boolean isManager = false;
		for (GrantedAuthority grantedAuthority : authorities) {
			if(grantedAuthority.getAuthority().equals("ROLE_COSTUMER")) {
				isCostumer = true;
				break;
			}else if(grantedAuthority.getAuthority().equals("ROLE_MANAGER")) {
				isManager = true;
				break;
			}
		}
		if(isCostumer)
			return "redirect:/store/products"; //redirect:/store/products
		else if(isManager)
			return "manageProducts";
		else
			throw new IllegalStateException();
	}
}
