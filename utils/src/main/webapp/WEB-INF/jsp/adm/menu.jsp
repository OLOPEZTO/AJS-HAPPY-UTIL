<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
if(session.getAttribute("SYS")==null) return;
session.removeAttribute("SYS");
%>
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand hidden-md hidden-lg hidden-xl" href="#">Consola de administración</a>
    </div>
    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li><a data-toggle="collapse" data-target=".navbar-collapse" href="#" onclick="loadPage('loadText?cmd=appEstatus');return false;">Estatus</a></li>
        
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Email<span class="caret"></span></a>
          <ul class="dropdown-menu">
          	<li><a data-toggle='collapse' data-target=".navbar-collapse" href='#' onclick="loadPage('loadPage?cmd=email/simple');return false;" >Simple</a></li>
          	<li><a data-toggle='collapse' data-target=".navbar-collapse" href='#' onclick="loadPage('loadPage?cmd=email/html');return false;" >Html</a></li>
          </ul>
        </li>
        
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Store<span class="caret"></span></a>
          <ul class="dropdown-menu">
          	<li><a data-toggle='collapse' data-target=".navbar-collapse" href='#' onclick="loadPage('loadPage?cmd=upload/file');return false;" >Upload</a></li>
          	<li><a data-toggle='collapse' data-target=".navbar-collapse" href='#' onclick="loadPage('loadPage?cmd=upload/fileDown');return false;" >Download</a></li>
          </ul>
        </li>
        
      </ul>
      <ul class="nav navbar-nav navbar-right">
      	<li><a href="${pageContext.request.contextPath}">Salir</a></li>
      </ul>
	</div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>