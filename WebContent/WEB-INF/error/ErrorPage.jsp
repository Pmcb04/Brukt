<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html lang="es">
<head>
	<!-- Start Head Area -->
    <jsp:include page="/WEB-INF/html/Head.jsp">
    	<jsp:param value="Pagina de error en el sistema" name="title"/>
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
				<h2>Error en el sistema</h2>
				<p>Se ha causado un ${pageContext.exception} en el servidor</p>
				<a href="IndexServlet.do">Ir al inicio</a>
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