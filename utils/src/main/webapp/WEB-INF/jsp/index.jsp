<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="es">
	<head>
	  	<title>Consola de prueba</title>
	  	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	  	<meta name="viewport" content="width=device-width, initial-scale=1">
	  	<link rel="stylesheet" href="_bootstrap-3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet" href="_theme/Master.css">
	   	<script src="_jquery/jquery-1.11.0.min.js"></script>
	   	<script src="_bootstrap-3.3.7/js/bootstrap.min.js"></script>
	</head>
	<body>
		<div class="container">
			<div class="home" style="margin-top:0;">
				<h1>Consola de prueba <small>${version}</small></h1>
			</div>
			<hr>
		</div>
		<div class="container" style="margin-top:5%;">
		    <div class="col-md-4 col-md-offset-4">
		        <div class="panel panel-primary">
		            <div class="panel-heading">Acceso</div>
		            <div class="panel-body">
		            
		            <!-- Login Form -->
		            <form method="post">
		            
		            	<!-- Username Field -->
		                <div class="row">
		                    <div class="form-group col-xs-12">
		                    <label for="username"><span class="text-danger" style="margin-right:5px;">*</span>Usuario:</label>
		                        <div class="input-group">
		                            <input class="form-control" id="username" type="text" name="username" placeholder="Firma" value="${username}" required autofocus />
		                            <span class="input-group-btn">
		                                <label class="btn btn-primary"><span class="glyphicon glyphicon-user" aria-hidden="true"></label>
		                            </span>
		                            </span>
		                        </div>
		                    </div>
		                </div>
		                
		                <!-- Content Field -->
		                <div class="row">
		                    <div class="form-group col-xs-12">
		                        <label for="password"><span class="text-danger" style="margin-right:5px;">*</span>Contraseña:</label>
		                        <div class="input-group">
		                            <input class="form-control" id="password" type="password" name="password" placeholder="Password" value="${password}" required/>
		                            <span class="input-group-btn">
		                                <label class="btn btn-primary"><span class="glyphicon glyphicon-lock" aria-hidden="true"></label>
		                            </span>
		                            </span>
		                        </div>
		                    </div>
		                </div>
		                
		                <!-- Login Button -->
		                <div class="row">
		                    <div class="form-group col-xs-4">
		                        <button class="btn btn-primary" type="submit">Ingresar</button>
		                    </div>
		                </div>
		                
		            </form>
		            <c:if test="${error!=null}">
						<div class="modal fade" id="myModal" data-backdrop="static" role="dialog">
					    	<div class="modal-dialog">
						      	<div class="modal-content panel-danger">
						        <div class="modal-header panel-heading">
						          <button type="button" class="close" data-dismiss="modal">&times;</button>
						          <h4 class="modal-title">Error</h4>
						        </div>
						        <div class="modal-body">
						          <p><B>${error}</B></p>
						        </div>
						        <div class="modal-footer">
						          <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
						        </div>
						      	</div>
						    </div>
					  	</div>
					  	<script>
							$(document).ready(function(){
							    $("#myModal").modal();
							});
						</script>
		            </c:if>

		            </div>
		        </div>
		    </div>
		</div>
	</body>
</html>