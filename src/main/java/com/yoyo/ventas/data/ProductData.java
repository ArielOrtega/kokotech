package com.yoyo.ventas.data;

import java.io.IOException;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;
import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.yoyo.ventas.domain.Category;
import com.yoyo.ventas.domain.Product;

@Repository
public class ProductData {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcCall simpleJdbcCall;
	@Autowired
	private DataSource dataSource;
	
	public List<Product> findTop(){
		Connection conexion = null;
		List<Product> products;
		try {
			conexion = dataSource.getConnection();
			conexion.setAutoCommit(false);
			CallableStatement csProducts = conexion.prepareCall("{call yoyoGetTop12()}");
			csProducts.execute();
			
			conexion.commit();
			
			ResultSet rs = csProducts.getResultSet(); 
			
			products = new ArrayList<>();
			Product product = new Product();
			while(rs.next()) {
				product.setProductId(rs.getInt(1));
				product.getCategory().setCategoryId(rs.getInt(2));
				product.setDescription(rs.getString(3));
				product.setPrice(rs.getFloat(4));
				product.setStockUnits(rs.getInt(5));
				product.setProductName(rs.getString(6));
				products.add(product);
				product = new Product();
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
		return products;
	}

	public void registerProduct(Product product) {
		//CALL `ecommerceyoyodb`.`yoyoProductRegister`
				//(3, 'NOT DELL INSP 15 3567 Ci3-7020U 4Gb 1TB DVD 15.6 HD Fog Grey', 500.000, 3, 'Dell Inspiron 15',@id_product );

				Connection conexion = null;
				try {
					conexion = dataSource.getConnection();
					conexion.setAutoCommit(false);
					
					CallableStatement csInsert = conexion.prepareCall("{CALL yoyoProductRegister(?,?,?,?,?,?)};");

					csInsert.setInt(1, product.getCategory().getCategoryId());
					csInsert.setString(2, product.getDescription());
					csInsert.setFloat(3, product.getPrice());
					csInsert.setFloat(4, product.getStockUnits());	
					csInsert.setString(5, product.getProductName());
					
					csInsert.registerOutParameter("product_id", java.sql.Types.INTEGER);
					csInsert.executeUpdate();
					int idProduct = csInsert.getInt("product_id");
					
					System.out.println("ID");
					System.out.println(idProduct);
					
					if(product.getImages().length>0 && product.getImages() != null) {
						for(String img: product.getImages()) {
							CallableStatement csInsertImage = conexion.prepareCall("{CALL yoyoImageRegister(?,?)};");
							csInsertImage.setInt(2, idProduct);
							csInsertImage.setString(1, img);
							csInsertImage.executeUpdate();
						}//for
					}//if
					
					conexion.commit();
				}  catch (SQLException e) {
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
			public List<Product> findById(String title){
				Connection conexion = null;
				List<Product> products;
				try {
					conexion = dataSource.getConnection();
					conexion.setAutoCommit(false);
					CallableStatement csProducts = conexion.prepareCall("{call yoyoProductFindAll(?)}");
					csProducts.setString(1, title);
					csProducts.execute();
					
					conexion.commit();
					
					ResultSet rs = csProducts.getResultSet(); 
					
					products = new ArrayList<>();
					Product product = new Product();
					while(rs.next()) {
						product.setProductId(rs.getInt(1));
						product.getCategory().setCategoryId(rs.getInt(2));
						product.getCategory().setCategoryName(rs.getString(8));
						product.setDescription(rs.getString(3));
						product.setPrice(rs.getFloat(4));
						product.setStockUnits(rs.getInt(5));
						product.setProductName(rs.getString(6));
				        product.setImages(getImagesProduct(product.getProductId())); 
						products.add(product);
						product = new Product();
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
				return products;
			}

			public String[] getImagesProduct(int idProduct) {
				List<String> images = new ArrayList<>();
				
				String sqlSelect = "SELECT `product`.`id_product`, " + 
						"	`productimage`.`id_ product_image`, " + 
						"    `productimage`.`product_image` " + 
						"	FROM `product` , `productimage` " + 
						"	WHERE `product`.`id_product` = `productimage`.`produc_id` " + 
						"    AND `product`.`id_product` = " + idProduct;
				
				//reemplaza el extractor cuando hay pocos parametros//
				jdbcTemplate.query(sqlSelect, new Object[] {}, (rs, row)-> rs.getString(3)).forEach(entry -> images.add(entry));
				
				String[] staticArray = new String[images.size()];
				
				if (!images.isEmpty()) {
				for (int i = 0; i < staticArray.length; i++) {
					staticArray[i]= images.get(i);
				}
				}
		        
				return staticArray;
			}
			
			public void delete(int idProduct) {
				Connection conexion = null;
				try {
					conexion = dataSource.getConnection();
					conexion.setAutoCommit(false); //El desarrollador/a se encargaria de los commits
					
					CallableStatement csDelete = conexion.prepareCall("{CALL yoyoProductDelete(?)};");

					csDelete.setInt(1, idProduct);
					csDelete.executeUpdate();			
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

			public void edit(Product product) {
				Connection conexion = null;
				try {
					conexion = dataSource.getConnection();
					conexion.setAutoCommit(false);
					
					CallableStatement csInsert = conexion.prepareCall("{CALL yoyoProductEdit(?,?,?,?,?,?)};");

					
					csInsert.setInt(1, product.getCategory().getCategoryId());
					csInsert.setString(2, product.getDescription());
					csInsert.setFloat(3, product.getPrice());
					csInsert.setFloat(4, product.getStockUnits());	
					csInsert.setString(5, product.getProductName());
					csInsert.setInt(6, product.getProductId());
					
					csInsert.executeUpdate();
					
					conexion.commit();
				}  catch (SQLException e) {
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

	public Product findProductById(int productId) {
		//yoyoGetProductById
		Connection conexion = null;
		Product product;
		try {
			conexion = dataSource.getConnection();
			conexion.setAutoCommit(false);
			CallableStatement csProduct = conexion.prepareCall("{call yoyoGetProductById(?)}");
			csProduct.setInt(1, productId);
			csProduct.execute();
			
			conexion.commit();
			
			ResultSet rs = csProduct.getResultSet(); 
			
			product = new Product();
			while(rs.next()) {
				product.setProductId(rs.getInt(1));
				product.getCategory().setCategoryId(rs.getInt(2));
				product.setDescription(rs.getString(3));
				product.setPrice(rs.getFloat(4));
				product.setStockUnits(rs.getInt(5));
				product.setProductName(rs.getString(6));
				product.getCategory().setCategoryName(rs.getString(7));
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
		return product;
	}

}