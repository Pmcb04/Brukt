<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
	<!-- Start Head Area -->
    <jsp:include page="/WEB-INF/html/Head.jsp">
    	<jsp:param value="${title}" name="title"/>
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

	<div class="container">
     	<div class="row">
         	
         	<form class="delete" method="post">
         	<div class="col-md-6 col-md-offset-3">
             	<h1>¿Desea borrar el producto ${product.title}?</h1>
		 		<div class="htc__login__btn delete_user">
		   			 <a><input type="submit" value="Eliminar"></a>
		 		</div>
    		</div>
    		</form>
		</div>
		
	 </div>
	 
	<!-- Start Footer Style -->
  	<jsp:include page="/WEB-INF/html/Footer.jsp"></jsp:include>
     <!-- End Footer Style -->
	 

    <!-- Start Foot Style -->
  	<jsp:include page="/WEB-INF/html/Foot.jsp"></jsp:include>
     <!-- End Foot Style -->
</body>
</html>