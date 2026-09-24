package com.codegnan.app.javawebapp2309.Controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codegnan.app.javawebapp2309.service.ProductService;
import com.codegnan.app.javawebapp2309.service.ProductServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/updateproductname")
public class UpdateProductNameController  {
	private ProductService productService;
	
	@Autowired 
	public UpdateProductNameController(ProductService productService) {
		this.productService = productService;
	}
	
	 @GetMapping
		public String getUpdateProductNameForm()
		{
			 return "update-product-name-form.jsp";
			
		}

	

	@PostMapping
	public String  UpdateProductNameOperation(HttpServletRequest req){
		var productId = Integer.parseInt(req.getParameter("pid"));
		var productName = req.getParameter("pname");
		
		var isProductRenamed = productService.renameProduct(productId, productName);
		if (isProductRenamed) {
			return "add-product-success.jsp";
		} else {
			return "add-product-failure.jsp";
		}
	}
}
