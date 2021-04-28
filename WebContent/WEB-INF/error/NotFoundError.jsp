<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><!DOCTYPE html>
<html lang="es">

<head>
	<!-- Start Head Area -->
    <jsp:include page="/WEB-INF/html/Head.jsp">
    	<jsp:param value="Pagina de error 404" name="title"/>
    </jsp:include>
    <!-- End Head Area -->

</head>

<body>

   	<!-- Start Header Style -->
   	<jsp:include page="/WEB-INF/html/Header.jsp"></jsp:include>
    <!-- End Header Style -->
    
     <!-- Start Search Style -->
   	<jsp:include page="/WEB-INF/html/Search.jsp"></jsp:include>
    <!-- End Search Style -->
    
	 <div class="htc__login__register  bg__white">
		<div id="notfound">
			<div class="notfound">
				<div class="notfound-404">
					<h1>4<span></span>4</h1>
				</div>
				<h2>Oops! Pagina no encontrada</h2>
				<p>Lo sentimos pero la pagina que esta buscando no existe, ha sido eliminada, cambiada de nombre o se encuentra termporalmente inaccesible</p>
				<a href="IndexServlet.do">Volver al inicio</a>
			</div>
		</div>
	</div>
			
    <!-- Start Header Style -->
   	<jsp:include page="/WEB-INF/html/Footer.jsp"></jsp:include>
    <!-- End Header Style -->

	<!-- Start Foot Area -->
    <jsp:include page="/WEB-INF/html/Foot.jsp"></jsp:include>
    <!-- End FootArea -->

</body><!-- This templates was made by Colorlib (https://colorlib.com) -->

</html>
