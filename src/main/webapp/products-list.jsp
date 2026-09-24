<%@page import="com.codegnan.app.javawebapp2309.dto.ProductDto"%>
<%@page import="java.util.List"%>

<html>
<body>

<h1>All Products</h1>

<%
List<ProductDto> productDtoList =
        (List<ProductDto>) request.getAttribute("PRODUCTSLIST");

if (productDtoList == null) {
%>

    <h2>Product list is NULL</h2>

<%
} else if (productDtoList.isEmpty()) {
%>

    <h2>No products found</h2>

<%
} else {

    for (ProductDto productDto : productDtoList) {
%>

        Product ID: <%= productDto.id() %><br>
        Name: <%= productDto.name() %><br>
        Brand: <%= productDto.brand() %><br>
        Description: <%= productDto.description() %><br>
        Status: <%= productDto.status() %><br>
        Created At: <%= productDto.createdAt() %><br>
        Updated At: <%= productDto.updatedAt() %><br>

        <hr>

<%
    }
}
%>

</body>
</html>