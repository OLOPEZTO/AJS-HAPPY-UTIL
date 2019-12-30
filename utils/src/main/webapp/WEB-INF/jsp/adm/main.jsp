<%@ page errorPage = "showError.jsp" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Consola de prueba</title>
        <!-- Bootstrap Core CSS -->
        <link rel="stylesheet" href="_bootstrap-3.3.7/css/bootstrap.min.css">
        <!-- Custom CSS -->
        <link href="_sbadmin/css/sb-admin-2.css" rel="stylesheet">
        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
        <link rel="stylesheet" href="_theme/Admin.css">
		<!-- BUSY-01 CSS -->
        <link href="_busy/busy-01.css" rel="stylesheet">
    </head>
    <body>
    	<!-- BUSY FRAME -->
    	<div id="busyDIV" class="container">
		    <div class="row">
		        <div class="animationload">
		            <div class="osahanloading"></div>
		        </div>
		    </div>
		</div>
		<div class="row hidden-xs hidden-sm">
			<div class="col-xs-2">
				?
			</div>
			<div class="col-xs-10">
				<span class="text-left"><h2><font color="navy">Consola de prueba</font></h2></span>
			</div>
		</div>
		<% session.setAttribute("SYS", "FYF"); %>
		<%@include file="menu.jsp" %>
		<div id="content" class="Content">
			<div class="row" style="height:100%;margin-left:1px;margin-right:1px;">
				<div id="testDiv" class="col-xs-12" style="height:100%;overflow-y:auto;">
					<div id="cont-01">
						<!--Where external page will load into-->
					</div>
				</div>
			</div>
		</div>
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
		<script src="_jquery/jquery-1.11.0.min.js"></script>
		<script src="_bootstrap-3.3.7/js/bootstrap.min.js"></script>
		<script type="text/javascript">
			$(document).ready(function() {
				root = "${pageContext.request.contextPath}";
				<%
				if(request.getParameter("page")==null) {
					out.println( "loadPage(root+'/loadText?cmd=appEstatus');" );
				} else {
					out.println( "loadPage(root+'/" + request.getParameter("page") + "');" );
				}
				%>
			});
			function loadPage(page) {
				$('#busyDIV').show();
				$('#cont-01').load( page, function(response, status, xhr) {
					if (status == "error") {
						var msg = "Error al intentar obtener el contenido ";
						alert(msg);
					}
					if (status == "success") {
						$('#busyDIV').hide();
					}
				});
			}
		</script>
	</body>
</html>