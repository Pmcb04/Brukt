<%@ page language="java" contentType="text/html; charset=UTF-8"     pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<%@ page import = 'java.text.DecimalFormat' %>
<!doctype html>
<html class="no-js" lang="es">
<head>
    <!-- Start Head Area -->
    <jsp:include page="/WEB-INF/html/Head.jsp">
    	<jsp:param value="${title}" name="title"/>
    </jsp:include>
    <!-- End Head Area -->
    
    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/starts.css">

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

        <!-- Start Product Details -->
        <section class="htc__product__details pt--120 pb--100 bg__white">
            <div class="container">
                <div class="row">
                    <div class="col-md-6 col-lg-6 col-sm-12 col-xs-12">
                        <div class="product__details__container">
                            <div class="product__big__images">
                                <div class="portfolio-full-image tab-content">
                                    <div role="tabpanel" class="tab-pane fade in active" id="img-tab-1">
                                        <img src="${pageContext.request.contextPath}/images/product/${product.image}" alt="full-image">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 col-lg-6 col-sm-12 col-xs-12 smt-30 xmt-30">
                        <div class="htc__product__details__inner">
                            <div class="pro__detl__title">
                                <h2>${product.title}</h2>
                            </div>
                            <div class="pro__favorites">
                            
                       	     <c:choose>
					    		<c:when test="${favorites=='0'}">
                                    <p>A ninguna persona le gusta este articulo</p>

					    		</c:when>
					    		<c:when test="${favorites=='1'}">
                                    <p>A ${favorites} persona le gusta este articulo</p>

					    		</c:when>
					    		<c:otherwise>
                                     <p>A ${favorites} personas les gusta este articulo</p>
					    		</c:otherwise>	
					    	</c:choose>
					    	
					    	<p class="prod_ven">Producto vendido por <a href="UserPublicServlet.do?username_user=${user_product}" class="user_ven">${user_product}</a></p>
                       
                            </div>
                           <c:choose>
					    	<c:when test="${product.rapido=='yes'}">
					    		<p class="rapido">Entrega rápida (24h)</p>
					    	</c:when>	
					    </c:choose>
                            
                            
                            <ul class="pro__dtl__prize">
                                <li><fmt:formatNumber type="number" maxFractionDigits="2"  minFractionDigits="2"  value="${product.price}" />  ${product.currency}</li>
                            </ul>
                            <div class="product-action-wrap">
                                <div class="prodict-statas"><span>Cantidad :</span></div>
                                <div class="product-quantity">
                                    <form id='myform' method='POST' action='#'>
                                        <div class="product-quantity">
                                            <div class="cart-plus-minus">
                                            	 <c:choose>
										    		<c:when test="${product.soldout=='1'}">
                                                		<input class="cart-plus-minus-box" type="text" name="qtybutton" min="0" max="0" value="0">
										    		</c:when>
										    		<c:otherwise>
                                                		<input class="cart-plus-minus-box" type="text" name="qtybutton" min="1" max="${product.stock}" value="1">
										    		</c:otherwise>	
										    	</c:choose>
                                            
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <ul class="pro__dtl__btn">
								<c:choose>
						    		<c:when test="${product.soldout=='1' && product.idu!=user.id}">
											<img alt="icono de vendido" style="width: 32px; height: 32px;" src="${pageContext.request.contextPath}/images/icons/venta.png">	
						    		</c:when>
						    		<c:when test="${product.soldout=='0' && product.idu!=user.id}">
	                                     <li class="buy__now__btn"><a href="#">Comprar</a></li>      		
						    		</c:when>	
						    	</c:choose>
                                
                                 <c:choose>
							    	<c:when test="${favorite_user==true && product.idu!=user.id}">
						    			 <li><a class ="favorite_link" href="DeleteFavoriteServlet.do?idu=${user.id}&idp=${product.id}&favorites=${favorites}&numEstrellas=Todas" title="Quitar de favoritos"><span class="material-icons favorite_heart">favorite</span></a></li>
						    		</c:when>
						    		<c:when test="${favorite_user==false && product.idu!=user.id}">
						    		 	<li><a class ="favorite_link" href="AddFavoriteServlet.do?idu=${user.id}&idp=${product.id}&favorites=${favorites}&numEstrellas=Todas" title="Añadir a favoritos"><span class="material-icons favorite_heart">favorite_border</span></a></li>
						    		</c:when>					    		

						    	</c:choose>
                            </ul>
							<c:choose>
								<c:when test="${product.idu==user.id}">
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
        </section>
        <!-- End Product Details -->
        <!-- Start Product tab -->
        <section class="htc__product__details__tab bg__white pb--120">
            <div class="container">
                <div class="row">
                    <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
                        <ul class="product__deatils__tab mb--60" role="tablist">
                            <li role="presentation" class="active">
                                <a href="#description" role="tab" data-toggle="tab">Descripcion</a>
                            </li>
                            <li role="presentation">
                                <a href="#reviews" role="tab" data-toggle="tab">Comentarios</a>
                            </li>
                            <li role="presentation">
                                <a href="#favorites" role="tab" data-toggle="tab">Favoritos</a>
                            </li>
                            <li role="presentation">
                                <a href="#relacionado" role="tab" data-toggle="tab">Relacionado</a>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <div class="product__details__tab__content">
                            <!-- Start Single Content -->
                            <div role="tabpanel" id="description" class="product__tab__content fade in active">
                                <div class="product__description__wrap">
                                    <div class="product__desc">
                                        <h2 class="title__6">Detalles</h2>
                                        <p>${product.description}</p>
                                    </div>
                                </div>
                            </div>
                            <!-- End Single Content -->
                            

                            <!-- Start Single Content -->
                            <div role="tabpanel" id="reviews" class="product__tab__content fade">
                            
										<p class="filtro_estrellas">Filtrar por numero de estrellas</p>
										
										
										<a href="ProductDetailsServlet.do?id=${product.id}&favorites=${favorites}&favoriteUser=${favorite_user}&numEstrellas=Todas">
											<p>Todos los comentarios</p>
										</a>
										
										
										
										<a href="ProductDetailsServlet.do?id=${product.id}&favorites=${favorites}&favoriteUser=${favorite_user}&numEstrellas=Cero">
											<ul class="rating">
			            	
								    		    <li><i class="zmdi zmdi-star-outline"></i></li>		                
								    		    <li><i class="zmdi zmdi-star-outline"></i></li>
								                <li><i class="zmdi zmdi-star-outline"></i></li>
								                <li><i class="zmdi zmdi-star-outline"></i></li>
								                <li><i class="zmdi zmdi-star-outline"></i></li>
								            </ul>
										</a>
										
										
										<a href="ProductDetailsServlet.do?id=${product.id}&favorites=${favorites}&favoriteUser=${favorite_user}&numEstrellas=Una">
											<ul class="rating">
			            	
								    		    <li><i class="zmdi zmdi-star"></i></li>		                
								    		    <li><i class="zmdi zmdi-star-outline"></i></li>
								                <li><i class="zmdi zmdi-star-outline"></i></li>
								                <li><i class="zmdi zmdi-star-outline"></i></li>
								                <li><i class="zmdi zmdi-star-outline"></i></li>
								            </ul>
										</a>
										
										
										<a href="ProductDetailsServlet.do?id=${product.id}&favorites=${favorites}&favoriteUser=${favorite_user}&numEstrellas=Dos">
							 				<ul class="rating">
								                <li><i class="zmdi zmdi-star"></i></li>
								                <li><i class="zmdi zmdi-star"></i></li>
								                <li><i class="zmdi zmdi-star-outline"></i></li>
								                <li><i class="zmdi zmdi-star-outline"></i></li>
								                <li><i class="zmdi zmdi-star-outline"></i></li> 
								            </ul>
							            </a>
							            
							            
							            <a href="ProductDetailsServlet.do?id=${product.id}&favorites=${favorites}&favoriteUser=${favorite_user}&numEstrellas=Tres">
							 				<ul class="rating">
								                <li><i class="zmdi zmdi-star"></i></li>
								                <li><i class="zmdi zmdi-star"></i></li>
								                <li><i class="zmdi zmdi-star"></i></li>
								                <li><i class="zmdi zmdi-star-outline"></i></li>
								                <li><i class="zmdi zmdi-star-outline"></i></li> 
								            </ul>
							            </a>
							            
							            <a href="ProductDetailsServlet.do?id=${product.id}&favorites=${favorites}&favoriteUser=${favorite_user}&numEstrellas=Cuatro">
							 				<ul class="rating">
								                <li><i class="zmdi zmdi-star"></i></li>
								                <li><i class="zmdi zmdi-star"></i></li>
								                <li><i class="zmdi zmdi-star"></i></li>
								                <li><i class="zmdi zmdi-star"></i></li>
								                <li><i class="zmdi zmdi-star-outline"></i></li> 
								            </ul>
							            </a>
							            
							            <a href="ProductDetailsServlet.do?id=${product.id}&favorites=${favorites}&favoriteUser=${favorite_user}&numEstrellas=Cinco">
							 				<ul class="rating">
								                <li><i class="zmdi zmdi-star"></i></li>
								                <li><i class="zmdi zmdi-star"></i></li>
								                <li><i class="zmdi zmdi-star"></i></li>
								                <li><i class="zmdi zmdi-star"></i></li>
								                <li><i class="zmdi zmdi-star"></i></li>
			                            	</ul>
		                            	</a>
                        
   
                            
                                <div class="review__address__inner">
                                     	<c:forEach items="${comments}" var="comment">
				          		        	<jsp:include page="/WEB-INF/html/Single-comment.jsp">
				                				<jsp:param value="${comment.username}" name="username"/>
				                				<jsp:param value="${comment.date}" name="date"/>
				                				<jsp:param value="${comment.rating}" name="rating"/>
				                				<jsp:param value="${comment.description}" name="comment"/>
				                				<jsp:param value="${product.id}" name="idp"/>
				                				<jsp:param value="${favorites}" name="favorites"/>
				                				<jsp:param value="${favorite_user}" name="favorite_user"/>
				                			</jsp:include>
										</c:forEach>
                                </div>
  							
  						<form id="review-form" method="post">
  						
  						
  							<!-- Start RAting Area -->
                                <div class="rating__wrap">
                                    <h2 class="rating-title">Escribe un comentario</h2>
                                    <h4 class="rating-title-2">Tu puntuación</h4>
                                    <div class="rating__list">
                                         <p class="clasificacion">
									    <input id="radio1" type="radio" name="estrellas" value="5">
									    <label for="radio1">★</label>
									    <input id="radio2" type="radio" name="estrellas" value="4">
									    <label for="radio2">★</label>
									    <input id="radio3" type="radio" name="estrellas" value="3">
									    <label for="radio3">★</label>
									    <input id="radio4" type="radio" name="estrellas" value="2">
									    <label for="radio4">★</label>
									    <input id="radio5" type="radio" name="estrellas" value="1">
									    <label for="radio5">★</label>
									  </p>
                                    </div>
                                </div>
                                <!-- End RAting Area -->
                                <div class="review__box">
                                        <div class="single-review-form">
                                            <div class="review-box message">
                                                <textarea id="comentario" name="comentario" placeholder="Escriba un comentario"></textarea>
                                            </div>
                                        </div>
                                        
                                        <div class="review-btn ">
                                         	<input class="fv-btn" type="submit" value="Comentar">
	                               		 </div>     
	                               		 
	                               		 <p class="errorComment">${errorComment}</p>
	                               		                
                               		 </div>
                                 </form> 
                            </div>
                            <!-- End Single Content -->
                            
                            
                            <!-- Start Single Content -->
                            <div role="tabpanel" id="favorites" class="product__tab__content fade">
                                   <c:choose>
						    		<c:when test="${favorites=='0'}">
						    			 <p>Ninguna persona tiene como favorito este articulo. ¡Se tú el primero en darle!</p>
						    		</c:when>
						    		<c:otherwise>
		                            	<c:forEach items="${favoritesUsers}" var="userFav">
		                            		<div class="userFavorite">
		                            			<a href="UserPublicServlet.do?username_user=${userFav.username}"><img alt="image user favorite" src="${pageContext.request.contextPath}/images/user/${userFav.image}"></a>
		                            			<a href="UserPublicServlet.do?username_user=${userFav.username}">${userFav.username}</a>
		                            		</div>
		                            	</c:forEach>						    		
	                            	</c:otherwise>	
						    	</c:choose>
                            </div>
                            <!-- End Single Content -->
                            
                            <!-- Start Single Content -->
                            <div role="tabpanel" id="relacionado" class="product__tab__content fade">
                                
                                
	                           	<c:forEach items="${productsRelated}" var="product">
                                
									<!-- Start Single Product -->                           		 
		          		        	<jsp:include page="/WEB-INF/html/Single-product.jsp">
		          		        		  	<jsp:param value="${product.first.image}" name="image"/>
			                				<jsp:param value="${product.first.price}" name="price"/>
			                				<jsp:param value="${product.first.title}" name="title"/>
			                				<jsp:param value="${product.first.currency}" name="currency"/>
			                				<jsp:param value="${product.first.id}" name="id"/>
			                				<jsp:param value="${product.first.soldout}" name="soldout"/>
			                				<jsp:param value="${product.second.size()}" name="favorites"/>
			                				<jsp:param value="${product.second.contains(user)}" name="favorite_user"/>
			                				<jsp:param value="${product.first.rapido}" name="rapido"/>
		          		        	</jsp:include>
	                            	<!-- End Single Product -->
	                            
	                           	</c:forEach>	

                            </div>
                            <!-- End Single Content -->
                            
                            
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- End Product tab -->

    <!-- Start Footer Style -->
  	<jsp:include page="/WEB-INF/html/Footer.jsp"></jsp:include>
     <!-- End Footer Style -->

    <!-- Start Foot Style -->
  	<jsp:include page="/WEB-INF/html/Foot.jsp"></jsp:include>
     <!-- End Foot Style -->
</body>

</html>