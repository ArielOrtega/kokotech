package com.yoyo.ventas.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.yoyo.ventas.business.CategoryBusiness;
import com.yoyo.ventas.business.ProductBusiness;
import com.yoyo.ventas.domain.Category;
import com.yoyo.ventas.domain.Product;
import com.yoyo.ventas.form.ProductForm;
import com.yoyo.ventas.form.ShoppingCartForm;

@Controller
public class ProductController {
	@Autowired
	private ProductBusiness productBusiness;
	@Autowired
	private CategoryBusiness categoryBusiness;
	
	public static String uploadDirectory = System.getProperty("user.dir");
	
	@RequestMapping(value="/store/products", method=RequestMethod.GET)
	public String initiate(Model model) {
		model.addAttribute("products", productBusiness.findById(""));
		return "findProducts";
	}
	
	@RequestMapping(value="/store/about", method=RequestMethod.GET)
	public String about(Model model) {
		return "aboutYoyo";
	}
	
	@RequestMapping(value="/store/products", method=RequestMethod.POST)
	public String initiate(Model model, @RequestParam("nameProduct") String nameProduct) {
		model.addAttribute("products", productBusiness.findById(nameProduct));
		return "findProducts";
	}	
	
	@RequestMapping(value="/store/product/details", method=RequestMethod.GET)
	public String details(Model model, @RequestParam("productId") int productId) {
		Product product = productBusiness.findProductById(productId);
		model.addAttribute("product", product);
		model.addAttribute("shoppingCartForm", new ShoppingCartForm());
		model.addAttribute("products", productBusiness.findById(product.getProductName()));
		return "productDetails";
	}
	
	@RequestMapping(value="/home/register", method=RequestMethod.GET)
	public String registerProductForm(Model model, HttpServletRequest request) {
		
		model.addAttribute("productForm", new ProductForm());
		model.addAttribute("categories", categoryBusiness.findAll());
		
		return "registerProduct";
	}
	
	//@RequestParam("images") MultipartFile[] images
	@RequestMapping(value="/home/register", method=RequestMethod.POST)
	public String registerProduct(@Valid ProductForm productForm,BindingResult br, Model model) throws SerialException, SQLException, IOException {
		//StringBuilder fileNames = new StringBuilder();

		if(br.hasErrors()) {
			model.addAttribute("productForm", productForm);
			model.addAttribute("categories", categoryBusiness.findAll());
			return "registerProduct";
			
		} else {
		
		Category category = new Category();
		category.setCategoryId(productForm.getCategoryId());
		
		MultipartFile[] files = productForm.getImages();
		String[] images= new String[files.length];
		int i= files.length - 1;
		for (MultipartFile file : files) {
			String imgString = writeToDisk(file);
			images[i]= imgString;
			if(i==0)
				break;
			i--;
		}

		Product product = new Product(productForm.getProductName(),
				productForm.getDescription(), productForm.getPrice(), productForm.getStockUnits(), 
				images, category);
		
		productBusiness.registerProduct(product);
		
		return "redirect:/home/maintenance";
		}
	}

	@RequestMapping(value="/home/maintenance", method=RequestMethod.GET)
	public String seeProducts( Model model) throws SQLException {
		model.addAttribute("products", new ArrayList<Product>());

		
		return "productMaintenance";
		
		
	}
	
	@RequestMapping(value="/home/maintenance", method=RequestMethod.POST)
	public String seeProducts( Model model, @RequestParam("productName") String productName) throws SQLException, UnsupportedEncodingException {
		model.addAttribute("products", productBusiness.findById(productName));
		return "productMaintenance";
		
		
	}	
	
	public String writeToDisk(MultipartFile file) {
		
	
		Path resourceDirectory = Paths.get("src","main","resources","static","images");
		String imageToBd = "";
		
		File dir = new File(resourceDirectory.toString());
		if(!dir.exists()) {
			dir.mkdir();
		}
		
		File serverFile = new File(dir.getAbsolutePath()+File.separator+file.getOriginalFilename());
		imageToBd = file.getOriginalFilename();
		
        try {
            try (InputStream is = file.getInputStream(); BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile))) {
                int i;
                while ((i = is.read()) != -1) {
                    stream.write(i);
                }
                stream.flush();
            }
        } catch (IOException e) {
            System.out.println("error : " + e.getMessage());
        }
		return imageToBd;
	}

	@RequestMapping(value="/home/maintenance/deleteProduct", method=RequestMethod.GET)
	public String delete(Model model,  @RequestParam("id") int idProduct,
			@RequestParam("product") String productName) {
		model.addAttribute("idProduct", idProduct);
		model.addAttribute("products", productBusiness.findById(productName));
		return "deleteProduct";
	}
	
	@RequestMapping(value="/home/maintenance/deleteProduct", method=RequestMethod.POST)
	public String delete(Model model, @RequestParam("idProduct") int idProduct){
		
		productBusiness.delete(idProduct);
		return "redirect:/home/maintenance";
	}	
		
	@RequestMapping(value="/home/maintenance/editProduct", method=RequestMethod.GET)
	public String edit(Model model,  @RequestParam("id") int idProduct,
			@RequestParam("product") String productName) {
		model.addAttribute("idProduct", idProduct);
		model.addAttribute("products", productBusiness.findById(productName));
		model.addAttribute("productForm", new ProductForm());
		model.addAttribute("categories", categoryBusiness.findAll());
		return "editProduct";
	}
	
	@RequestMapping(value="/home/maintenance/editProduct", method=RequestMethod.POST)
	public String edit(@Valid ProductForm productForm,BindingResult br, Model model){
		
		if(br.hasErrors()) {
			model.addAttribute("productForm", productForm);
			model.addAttribute("categories", categoryBusiness.findAll());
			return "registerProduct";
			
		} 
		else {
		String[] images =  productBusiness.findById(productForm.getProductName()).get(0).getImages();
		int id = productBusiness.findById(productForm.getProductName()).get(0).getProductId();
		
		
		System.out.println("ALOO "+productForm.getCategoryId());
		
		Category category = new Category();
		category.setCategoryId(productForm.getCategoryId());
		Product product = new Product(id, productForm.getProductName(),
				productForm.getDescription(), productForm.getPrice(), productForm.getStockUnits(), 
				images, category);
		
		productBusiness.edit(product);
		return "redirect:/home/maintenance";
		}
	}	
						
}
