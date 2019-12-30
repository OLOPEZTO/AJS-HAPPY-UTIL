<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
	<body>
		<h1>HTML email</h1>
		<form method="POST" action="/email/html">
			Email:<input type="text" name="email"/><br/>
			Título:<input type="text" name="titulo"/><br/>
		    Contenido HTML:<input type="text" name="contenido"/><br/><br/>
		    <input type="submit" value="Submit" />
		</form>
		<!-- 
		<html> <head> </head> <body> <table bgcolor='#008000' width='100%'> <tr> <td> <h2><font color='#ffffff' ><b>Test HTML email</b></font></h2> </td> </tr> </table> <br> <table bgcolor='#d4d4d4' width='100%'> <tr> <td> <b>This mail from  Author  , for the topic of <font color=\"#ff6600\">Topic </font> in  <font color='#800080'>Content In </font> .</b>  </td> </tr> </table> </body> </html>
		-->
	</body>
</html>