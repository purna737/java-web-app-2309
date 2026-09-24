package com.codegnan.app.javawebapp2309.Controller;

import java.io.IOException;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codegnan.app.javawebapp2309.dto.ProductDto;
import com.codegnan.app.javawebapp2309.service.ProductService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/viewallproduct")
public class ViewAllProductsController  {
	private ProductService productService;
	
	@Autowired 
	public ViewAllProductsController(ProductService productService) {
		this.productService = productService;
	}
	
	
	 @GetMapping
	public String ViewAllProductsOperation(HttpServletRequest req)  {
		List<ProductDto> productDtosList = productService.getAllProducts();
			
		req.setAttribute("PRODUCTSLIST", productDtosList);
		
		return "products-list.jsp";
		
	}
}
