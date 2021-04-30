<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
	<!-- Start Head Area -->
    <jsp:include page="/WEB-INF/html/Head.jsp">
    	<jsp:param value="${title}" name="title"/>
    </jsp:include>
    <!-- End Head Area -->
    
    	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/user.css"/>

	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/product.css"/>
	<script src="${pageContext.request.contextPath}/js/product.js"></script>
    
</head>
<body>

   	<!-- Start Header Style -->
   	<jsp:include page="/WEB-INF/html/Header.jsp"></jsp:include>
     <!-- End Header Style -->
     
    <!-- Start Search Style -->
   	<jsp:include page="/WEB-INF/html/Search.jsp"></jsp:include>
     <!-- End Search Style -->
     
	<div class="container mt-5 d-flex justify-content-center">
	    <div class="card p-3">
	        <div class="d-flex align-items-center">
	            <div class="image"> <img src="${pageContext.request.contextPath}/images/user/${user_public.image}" class="rounded" width="180"> </div>
	            <div class="ml-3 w-100">
	                <h4 class="mb-0 mt-0 username_profile">${user_public.username}</h4> <span class="email_profile">${user_public.email}</span>
	                <div class="p-2 mt-2 d-flex justify-content-between rounded text-white stats">
	                    <div class="d-flex flex-column"> <span class="articles">En venta</span> <span class="number_articles">${user_venta}</span> </div>
	                    <div class="d-flex flex-column"> <span class="vendidos">Vendidos</span> <span class="number_vendidos">${user_vendido}</span> </div>
	                </div>
	           </div>
	        </div>
	    </div>
	</div>
	
	<div class="htc__login__register bg__white ptb--150">
    	<div class="container">
    		<div class="row">
				<h2 class="titles_user">Articulos a la venta</h2>
				
				<c:forEach items="${products_user}" var="product">
                 	<!-- Start Single Product -->
 		        	<jsp:include page="/WEB-INF/html/Single-product.jsp">
           				<jsp:param value="${product.first.price}" name="price"/>
           				<jsp:param value="${product.first.title}" name="title"/>
           				<jsp:param value="${product.first.currency}" name="currency"/>
           				<jsp:param value="${product.first.id}" name="id"/>
           				<jsp:param value="${product.first.idu}" name="idu"/>
						<jsp:param value="${product.first.rapido}" name="rapido"/>
           				<jsp:param value="${product.first.soldout}" name="soldout"/>
						<jsp:param value="${product.first.image}" name="image"/>
           				<jsp:param value="${product.second.size()}" name="favorites"/>
						<jsp:param value="${product.second.contains(user)}" name="favorite_user"/>
       				</jsp:include>
                  	<!-- End Single Product -->
				</c:forEach>
				
			</div>			
		</div>
	</div>
				
	
	<!-- Start Footer Area -->
    <jsp:include page="/WEB-INF/html/Footer.jsp"></jsp:include>
    <!-- End Footer Area -->
     
     
    <!-- Start Foot Style -->
  	<jsp:include page="/WEB-INF/html/Foot.jsp"></jsp:include>
     <!-- End Foot Style -->
    
</body>
</html>