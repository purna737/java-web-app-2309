package com.codegnan.app.javawebapp2309.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.codegnan.app.javawebapp2309.dao.ProductDao;
import com.codegnan.app.javawebapp2309.dao.ProductDaoImpl;
import com.codegnan.app.javawebapp2309.dto.ProductDto;


@Component
public class ProductServiceImpl implements ProductService {
	private ProductDao productDao;
	
	@Autowired
	public ProductServiceImpl(ProductDao productDao) {
		this.productDao = productDao;
	}
	
	@Override
	public boolean addProduct(ProductDto productDto) {
		return productDao.save(productDto);
	}
	
	@Override
	public ProductDto searchProduct(int productId) {
		return productDao.findById(productId);
	}
	
	@Override
	public ProductDto searchProduct(String productName) {
		return productDao.findByName(productName);
	}
	
	@Override
	public List<ProductDto> getAllProducts() {
		return productDao.findAll();
	}
	
	@Override
	public boolean renameProduct(int productId, String updatedName) {
		return productDao.updateName(productId, updatedName);
	}
	
	@Override
	public boolean removeProduct(int productId) {
		return productDao.delete(productId);
	}
}