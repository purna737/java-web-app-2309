<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="addproduct" method="post">
		Name<input type="text" name="pname"><br>
		Brand<input type="text" name="pbrand"><br>
		Description<input type="text" name="pdesc"><br>
		Status<select name="pstatus">
		<option>Active</option>
		<option>Inactive</option>
		<option>Draft</option>
		<option>Discontinued</option>
		</select><br>
		<button type="submit">Add</button>
	</form>
</body>
</html>