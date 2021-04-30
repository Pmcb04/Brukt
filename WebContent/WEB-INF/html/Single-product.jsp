<%@ page language="java" contentType="text/html; charset=UTF-8"     pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
<div class="col-md-3 single__pro col-lg-3 hidden-sm col-xs-12 cat--4">
    <div class="product foo">
    
        <div class="product__inner">
            <div class="pro__thumb">
                <a href="ProductDetailsServlet.do?id=${param.id}&favorites=${param.favorites}&favoriteUser=${param.favorite_user}&numEstrellas=Todas">
                   	<div class = "box">
	                   	<img class="image_shop" src="${pageContext.request.contextPath}/images/product/${param.image}" alt="product images">
                   	</div>
                </a>
                
            </div>
            
                <c:choose>
		    		<c:when test="${param.soldout=='1'}">
			            <div class="vendido_icon">
			            	<img alt="icono de vendido" src="${pageContext.request.contextPath}/images/icons/venta.png">
			            </div>
		    		</c:when>
		    	</c:choose>
        </div>
        
        <div class="product-details">
			<div class="favorites_prod">
            	<label class="num_fav">${param.favorites}</label>
                <c:choose>
		    		<c:when test="${param.favorite_user==true}">
		    			<span class="material-icons favorite_heart">favorite</span>
		    		</c:when>
		    		<c:otherwise>
		    		 	<span class="material-icons favorite_heart">favorite_border</span>
		    		</c:otherwise>	
		    	</c:choose>
            </div>
			<h4><a href="ProductDetailsServlet.do?id=${param.id}&favorites=${param.favorites}&favoriteUser=${param.favorite_user}&numEstrellas=Todas">${param.title}</a></h4>
				
			<div class="product-bottom-details">
				<div class="product-price"><fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${param.price}" /> ${param.currency}</div>
			<c:choose>
    			<c:when test="${param.idu==user.id}">
					<div class="icons_profile"> 
						<a href="DeleteProductServlet.do?id=${param.id}" >	<span class="material-icons">delete</span>
						</a>
						<a href="EditProductServlet.do?id=${param.id}"><span class="material-icons">edit</span></a> </div>
    			</c:when>
    		</c:choose>
			</div>
		</div>	

    </div>

</div>