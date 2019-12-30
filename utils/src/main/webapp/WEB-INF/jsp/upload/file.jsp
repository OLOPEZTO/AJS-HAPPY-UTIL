<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
	<body>
		<h1>File upload example</h1>
		<form method="POST" action="/upload/file" enctype="multipart/form-data">
		    Año:<input type="text" name="carpeta"/><br/>
		    Archivo:<input type="file" name="file" /><br/><br/>
		    <input type="submit" value="Submit" />
		</form>
	</body>
</html>