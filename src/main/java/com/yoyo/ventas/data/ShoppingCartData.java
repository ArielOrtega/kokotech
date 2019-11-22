package com.yoyo.ventas.data;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.yoyo.ventas.domain.ShoppingCart;

@Repository
public class ShoppingCartData {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcCall simpleJdbcCall;
	@Autowired
	private DataSource dataSource;
	@Autowired
	private UserData userData;
	
	public int saveShoppingCart(ShoppingCart shoppingCart) {
		//`yoyoInsertShoppingCartById`(id_client_param int, id_product_param int, quantity_param int)
		Connection conexion = null;
		int productId;
		try {
			conexion = dataSource.getConnection();
			conexion.setAutoCommit(false);
			CallableStatement csInsertShoppingCart = conexion.prepareCall("{call yoyoInsertShoppingCartById(?, ?, ?)}");
			
			csInsertShoppingCart.setInt(2, shoppingCart.getProduct().getProductId());
			csInsertShoppingCart.setInt(3, shoppingCart.getQuantity());
			
			//obtener la info del cliente, si esta loggeado
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			int clientId = userData.findByEmail(auth.getName()).getClienteId();
			
			csInsertShoppingCart.setInt(1, clientId);
			
			csInsertShoppingCart.execute();
			
			conexion.commit();
			
			productId = shoppingCart.getProduct().getProductId();
			
		} catch (SQLException e) {
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				throw new RuntimeException(e1);
			}
			throw new RuntimeException(e);
		}finally {
			if(conexion != null) {
				try {
					conexion.close();
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
			}
		} 
		return productId;
	}
	
	public List<ShoppingCart> getShoppingCart() {
		//obtener la info del cliente, si esta loggeado
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		int idClient = userData.findByEmail(auth.getName()).getClienteId();
		//yoyoShoppingCartGetByClient
		Connection conexion = null;
		List<ShoppingCart> shoppingCarts;
		try {
			conexion = dataSource.getConnection();
			conexion.setAutoCommit(false);
			CallableStatement csGetShoppingCart = conexion.prepareCall("{call yoyoShoppingCartGetByClient(?)}");
			csGetShoppingCart.setInt(1, idClient);
			csGetShoppingCart.execute();
			
			conexion.commit();
			
			ResultSet rs = csGetShoppingCart.getResultSet(); 
			
			shoppingCarts = new ArrayList<>();
			ShoppingCart shoppingCart = new ShoppingCart();
			while(rs.next()) {
				//Select p.id_product, p.product_name, p.price, s.quantity
				shoppingCart.getProduct().setProductId(rs.getInt("id_product"));
				shoppingCart.getProduct().setProductName(rs.getString("product_name"));
				shoppingCart.getProduct().setPrice(rs.getFloat("price"));
				shoppingCart.setQuantity(rs.getInt("quantity"));
				shoppingCarts.add(shoppingCart);
				shoppingCart = new ShoppingCart();
			}
		} catch (SQLException e) {
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				throw new RuntimeException(e1);
			}
			throw new RuntimeException(e);
		}finally {
			if(conexion != null) {
				try {
					conexion.close();
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
			}
		}  
		return shoppingCarts;
	}
	
	public void removeProductCart(int productId) {
		//yoyoShoppingCartRemoveProduct
		Connection conexion = null;
		try {
			conexion = dataSource.getConnection();
			conexion.setAutoCommit(false);
			CallableStatement csRemoveProduct = conexion.prepareCall("{call yoyoShoppingCartRemoveProduct(?)}");
			
			csRemoveProduct.setInt(1, productId);
			
			csRemoveProduct.execute();
			
			conexion.commit();
			
		} catch (SQLException e) {
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				throw new RuntimeException(e1);
			}
			throw new RuntimeException(e);
		}finally {
			if(conexion != null) {
				try {
					conexion.close();
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
			}
		} 
	}
	
	public float getTotalPrice(List<ShoppingCart> carts) {
		float total = 0;
		for (int i = 0; i < carts.size(); i++) {
			
			total += (carts.get(i).getProduct().getPrice() * carts.get(i).getQuantity());
			
		}
		return total;
	}
}
