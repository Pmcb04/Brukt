<%@ page language="java" contentType="text/html; charset=UTF-8"     pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-md-3 single__pro col-lg-3 hidden-sm col-xs-12 cat--3">
    <div class="product foo">
        <div class="product__inner">
            <div class="pro__thumb">
                <a href="ProductDetailsServlet.do?id=${param.id}&favorites=${param.favorites}&favoriteUser=${param.favorite_user}&numEstrellas=Todas">
                   	<img class="image_shop" src="${pageContext.request.contextPath}/images/product/${param.image}" alt="product images">
                </a>
            </div>
            <div class="add__to__wishlist">
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
        </div>
        <div class="product__details">
            <h2><a href="ProductDetailsServlet.do?id=${param.id}&favorites=${param.favorites}&favoriteUser=${param.favorite_user}&numEstrellas=Todas">${param.title}</a></h2>
            <label class="new__price">${param.price} ${param.currency}</label>
       		<c:choose>
		    	<c:when test="${param.soldout=='1'}">
		    		<p class="product_vendido">Vendido</p>
		    	</c:when>
		    	<c:otherwise>
		    		<p class="product_en_venta">En venta</p>
		    	</c:otherwise>	
		    </c:choose>
		    <div class="icons_profile"> <a href="DeleteProductServlet.do?id=${param.id}"><span class="material-icons">delete</span></a> <a href="EditProductServlet.do?id=${param.id}"><span class="material-icons">edit</span></a> </div>
        </div>
    </div>
</div>