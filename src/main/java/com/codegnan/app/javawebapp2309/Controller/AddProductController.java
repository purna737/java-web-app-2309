package com.codegnan.app.javawebapp2309.Controller;

import java.io.IOException;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codegnan.app.javawebapp2309.dto.ProductDto;
import com.codegnan.app.javawebapp2309.service.ProductService;
import com.codegnan.app.javawebapp2309.service.ProductServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/addproduct")
public class AddProductController extends HttpServlet {
	private ProductService productService;
	
	@Autowired
	public AddProductController(ProductService productService) {
	this.productService = productService;
	}
	
	
     @GetMapping
	public String getAddProductForm() {
		return "add-product-form.jsp";
		
	}

	@PostMapping
	public String AddProductOperation(HttpServletRequest req) {
		var productName = req.getParameter("pname");
		var brand = req.getParameter("pbrand");
		var description = req.getParameter("pdesc");
		var status = req.getParameter("pstatus");
		var currentDateTime = LocalDateTime.now();

		var productDto = new ProductDto(0, productName, brand, description, status, currentDateTime, currentDateTime);
		
		var isProductAdded = productService.addProduct(productDto);
		if (isProductAdded) {
			return "add-product-success.jsp";
		} else {
			return "add-product-failure.jsp";
		}
	}
}