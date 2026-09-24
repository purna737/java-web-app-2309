package com.codegnan.app.javawebapp2309.Controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codegnan.app.javawebapp2309.service.ProductService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/deleteproduct")
public class DeleteProductNameController extends HttpServlet {
	
private ProductService productService;
	
	public DeleteProductNameController(ProductService productService) {
		this.productService = productService;
	}
	
	
	 @GetMapping
		public String getDeleteProductForm()
		{
			 return "delete-product-form.jsp";
			
		}

	@PostMapping
	public String DeleteProductOperation(HttpServletRequest req) {
		var productId = Integer.parseInt(req.getParameter("pid"));
				
		var isProductRemoved = productService.removeProduct(productId);
		if (isProductRemoved) {
			return "add-product-success.jsp";
		} else {
			return "add-product-failure.jsp";
		}
	}
}