<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
	<body>
		<h1>Simple email</h1>
		<form method="POST" action="/email/simple">
			Email:<input type="text" name="email"/><br/>
			Título:<input type="text" name="titulo"/><br/>
		    Contenido:<input type="text" name="contenido"/><br/><br/>
		    <input type="submit" value="Submit" />
		</form>
	</body>
</html>