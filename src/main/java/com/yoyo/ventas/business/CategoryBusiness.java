package com.yoyo.ventas.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yoyo.ventas.data.CategoryData;
import com.yoyo.ventas.domain.Category;

@Service
public class CategoryBusiness {
	@Autowired
	private CategoryData categoryData;
	
	public List<Category> findAll(){
		return categoryData.findAll();
	}
	public void addCategory(Category category) {
		categoryData.addCategory(category);
	}
	
	public void editCategory(Category category) {
		categoryData.editCategory(category);
	}
	
	public void deleteCategory(Category category) {
		categoryData.deleteCategory(category);
	}
	
	public List<Category> findByName(String name){
		return categoryData.findByName(name);
	}
}
