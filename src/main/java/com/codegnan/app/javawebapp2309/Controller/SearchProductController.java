package com.codegnan.app.javawebapp2309.Controller;

import java.io.IOException;

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
@RequestMapping("/searchproduct")
public class SearchProductController {
	private ProductService productService;
	
	@Autowired 
	public SearchProductController(ProductService productService) {
		this.productService = productService;
	}
	
	
	 @GetMapping
	public String getSearchProductForm()
	{
		 return "search-product-form.jsp";
		
	}

	 @PostMapping
	public String SearchProductOperation(HttpServletRequest req) {
		ProductDto productDto = null;
		
		var productId = req.getParameter("pid");
		
		if(productId.equals("")) {
			var productName = req.getParameter("pname");
			
			productDto = productService.searchProduct(productName);	
			
		} else {
			productDto = productService.searchProduct(Integer.parseInt(productId));
		}
		
		req.setAttribute("PRODUCT", productDto);
		
		return "product-info.jsp";
		
	}
}
