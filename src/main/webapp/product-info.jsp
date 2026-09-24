<%@page import="com.codegnan.app.javawebapp2309.dto.ProductDto"%>
<html>
<body>
	<%
	ProductDto productDto = (ProductDto) request.getAttribute("PRODUCT");
	
	if (productDto != null) {
	%>
	
	Product ID: <%= productDto.id() %><br>
	Name: <%= productDto.name() %><br>
	Brand: <%= productDto.brand() %><br>
	Description: <%= productDto.description() %><br>
	Status: <%= productDto.status() %><br>
	Created At: <%= productDto.createdAt() %><br>
	Updated At: <%= productDto.updatedAt() %>
	<% 
	} else {
	%>
		Product not found by the given id.
	<% 
	}
	%>

</body>
</html>