<%@ page language="java" contentType="text/html; charset=UTF-8"     pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html class="no-js" lang="es">
<head>

    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>${title}</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Place favicon.ico in the root directory -->
    <link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}images/icons/favicon.jpg">
    <link rel="apple-touch-icon" href="${pageContext.request.contextPath}images/icons/apple-touch-icon.png">
    
     <link href="https://fonts.googleapis.com/icon?family=Material+Icons"rel="stylesheet"> 
     <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js">
     <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/css/bootstrap.min.css">
     <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
     <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,600,700' rel='stylesheet' type='text/css'>
     

    <!-- All css files are included here. -->
    <!-- Bootstrap fremwork main css -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <!-- Owl Carousel main css -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/owl.carousel.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/owl.theme.default.min.css">
    <!-- This core.css file contents all plugings css file. -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/core.css">
    <!-- Theme shortcodes/elements style -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/shortcode/shortcodes.css">
    <!-- Theme main style -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <!-- Responsive css -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/responsive.css">
    <!-- User style -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/custom.css">


    <!-- Modernizr JS -->
    <script src="${pageContext.request.contextPath}/js/vendor/modernizr-2.8.3.min.js"></script>


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
        
        <!-- Start Banner Style -->
     	<jsp:include page="/WEB-INF/html/Banner.jsp">
     		<jsp:param value="${banner}" name="image"/>
     	</jsp:include>
        <!-- End Banner Style -->

 
        <!-- Start Our Product Area -->
        <section class="htc__product__area shop__page ptb--130 bg__white">
            <div class="container" style="width: 3000px;">
                <div class="htc__product__container">
                    <!-- Start Product MEnu -->
                    <div class="row">
                        <div class="col-md-12">
                            <div class="filter__menu__container">
                                <div class="product__menu">
                                    <c:choose>
							    		<c:when test="${search == 'yes'}">
                                    		<a href="ShopServlet.do?id=Todas&name=Todas las categorias&estado=All&order=asc&search=no&busqueda=&moneda=All&price=All">X</a>
							    		</c:when>
							    	</c:choose>
                                    <button data-filter="*"  class="is-checked">${name_category}</button>
                                </div>
                                <div class="filter__box">
                                    <a class="filter__menu" href="#">Filtrar</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- Start Filter Menu -->
                    <div class="filter__wrap">
                        <div class="filter__cart">
                            <div class="filter__cart__inner">
                                <div class="filter__menu__close__btn">
                                    <a href="#"><i class="zmdi zmdi-close"></i></a>
                                </div>
                                <div class="filter__content">
                                    <!-- Start Single Content -->                                    
                                    <div class="fiter__content__inner">
                                        <div class="single__filter">
                                            <h2>Ordenar por</h2>
                                            <ul class="filter__list">
                                            	
												<c:choose>
										    		<c:when test="${order=='asc'}">
                                                		<li class="filter_act"><a href="ShopServlet.do?id=${id}&name=${name}&estado=${estado}&order=asc&search=${search}&busqueda=${busqueda}&moneda=${moneda}&price=${price}">Nombre ascendente</a></li>
										    		</c:when>
										    		<c:otherwise>
                                                		<li><a href="ShopServlet.do?id=${id}&name=${name}&estado=${estado}&order=asc&search=${search}&busqueda=${busqueda}&moneda=${moneda}&price=${price}">Nombre ascendente</a></li>
										    		</c:otherwise>	
										    	</c:choose>
										    	
										    	<c:choose>
										    		<c:when test="${order=='desc'}">
										    			 <li class="filter_act"><a href="ShopServlet.do?id=${id}&name=${name}&estado=${estado}&order=desc&search=${search}&busqueda=${busqueda}&moneda=${moneda}&price=${price}">Nombre descendente</a></li>
										    		</c:when>
										    		<c:otherwise>
										    		 	 <li><a href="ShopServlet.do?id=${id}&name=${name}&estado=${estado}&order=desc&search=${search}&busqueda=${busqueda}&moneda=${moneda}&price=${price}">Nombre descendente</a></li>
										    		</c:otherwise>	
										    	</c:choose>
										    	
										    	<c:choose>
										    		<c:when test="${order=='ascF'}">
										    			 <li class="filter_act"><a href="ShopServlet.do?id=${id}&name=${name}&estado=${estado}&order=ascF&search=${search}&busqueda=${busqueda}&moneda=${moneda}&price=${price}">Número favoritos ascendente</a></li>
										    		</c:when>
										    		<c:otherwise>
										    		 	 <li><a href="ShopServlet.do?id=${id}&name=${name}&estado=${estado}&order=ascF&search=${search}&busqueda=${busqueda}&moneda=${moneda}&price=${price}">Número favoritos ascendente</a></li>
										    		</c:otherwise>	
										    	</c:choose>
										    	
										    	<c:choose>
										    		<c:when test="${order=='descF'}">
										    			 <li class="filter_act"><a href="ShopServlet.do?id=${id}&name=${name}&estado=${estado}&order=descF&search=${search}&busqueda=${busqueda}&moneda=${moneda}&price=${price}">Número favoritos descendente</a></li>
										    		</c:when>
										    		<c:otherwise>
										    		 	 <li><a href="ShopServlet.do?id=${id}&name=${name}&estado=${estado}&order=descF&search=${search}&busqueda=${busqueda}&moneda=${moneda}&price=${price}">Número favoritos descendente</a></li>
										    		</c:otherwise>	
										    	</c:choose>
										    	
										    	<c:choose>
										    		<c:when test="${order=='ascC'}">
										    			 <li class="filter_act"><a href="ShopServlet.do?id=${id}&name=${name}&estado=${estado}&order=ascC&search=${search}&busqueda=${busqueda}&moneda=${moneda}&price=${price}">Número comentarios ascendente</a></li>
										    		</c:when>
										    		<c:otherwise>
										    		 	 <li><a href="ShopServlet.do?id=${id}&name=${name}&estado=${estado}&order=ascC&search=${search}&busqueda=${busqueda}&moneda=${moneda}&price=${price}">Número comentarios ascendente</a></li>
										    		</c:otherwise>	
										    	</c:choose>
										    	
										    	<c:choose>
										    		<c:when test="${order=='descC'}">
										    			 <li class="filter_act"><a href="ShopServlet.do?id=${id}&name=${name}&estado=${estado}&order=descC&search=${search}&busqueda=${busqueda}&moneda=${moneda}&price=${price}">Número comentarios descendente</a></li>
										    		</c:when>
										    		<c:otherwise>
										    		 	 <li><a href="ShopServlet.do?id=${id}&name=${name}&estado=${estado}&order=descC&search=${search}&busqueda=${busqueda}&moneda=${moneda}&price=${price}">Número comentarios descendente</a></li>
										    		</c:otherwise>	
										    	</c:choose>
                                            
                                            </ul>
                                        </div>
                                        <div class="single__filter">
                                            <h2>Estado del producto</h2>
                                            <ul class="filter__list">
										    	<c:choose>
										    		<c:when test="${estado=='All'}">
										    			 <li class="filter_act"><a href="ShopServlet.do?id=${id}&name=${name}&estado=All&order=${order}&search=${search}&busqueda=${busqueda}&moneda=${moneda}&price=${price}">Todos</a></li>
										    		</c:when>
										    		<c:otherwise>
										    		 	 <li><a href="ShopServlet.do?id=${id}&name=${name}&estado=All&order=${order}&search=${search}&busqueda=${busqueda}&moneda=${moneda}&price=${price}">Todos</a></li>
										    		</c:otherwise>	
										    	</c:choose>

										    	<c:choose>
										    		<c:when test="${estado=='En Venta'}">
										    			 <li class="filter_act"><a href="ShopServlet.do?id=${id}&name=${name}&estado=En Venta&order=${order}&search=${search}&busqueda=${busqueda}&moneda=${moneda}&price=${price}">En venta</a></li>
										    		</c:when>
										    		<c:otherwise>
										    		 	 <li><a href="ShopServlet.do?id=${id}&name=${name}&estado=En Venta&order=${order}&search=${search}&busqueda=${busqueda}&moneda=${moneda}&price=${price}">En venta</a></li>
										    		</c:otherwise>	
										    	</c:choose>
										    	
										    	<c:choose>
										    		<c:when test="${estado=='Vendidos'}">
										    			 <li class="filter_act"><a href="ShopServlet.do?id=${id}&name=${name}&estado=Vendidos&order=${order}&search=${search}&busqueda=${busqueda}&moneda=${moneda}&price=${price}">Vendidos</a></li>
										    		</c:when>
										    		<c:otherwise>
										    		 	 <li><a href="ShopServlet.do?id=${id}&name=${name}&estado=Vendidos&order=${order}&search=${search}&busqueda=${busqueda}&moneda=${moneda}&price=${price}">Vendidos</a></li>
										    		</c:otherwise>	
										    	</c:choose>
                                            </ul>
                                        </div>
                                       <div class="single__filter">
                                            <h2>Moneda</h2>
                                            <ul class="filter__list">
										    	<c:choose>
										    		<c:when test="${moneda=='All'}">
										    			 <li class="filter_act"><a href="ShopServlet.do?id=${id}&name=${name}&estado=${estado}&order=${order}&search=${search}&busqueda=${busqueda}&moneda=All&price=${price}">Todas</a></li>
										    		</c:when>
										    		<c:otherwise>
										    		 	 <li><a href="ShopServlet.do?id=${id}&name=${name}&estado=${estado}&order=${order}&search=${search}&busqueda=${busqueda}&moneda=All&price=${price}">Todas</a></li>
										    		</c:otherwise>	
										    	</c:choose>

										    	<c:choose>
										    		<c:when test="${moneda=='euro'}">
										    			 <li class="filter_act"><a href="ShopServlet.do?id=${id}&name=${name}&estado=${estado}&order=${order}&search=${search}&busqueda=${busqueda}&moneda=euro&price=${price}">Euro(&euro;)</a></li>
										    		</c:when>
										    		<c:otherwise>
										    		 	 <li><a href="ShopServlet.do?id=${id}&name=${name}&estado=${estado}&order=${order}&search=${search}&busqueda=${busqueda}&moneda=euro&price=${price}">Euro(&euro;)</a></li>
										    		</c:otherwise>	
										    	</c:choose>
										    	
										    	<c:choose>
										    		<c:when test="${moneda=='dolar'}">
										    			 <li class="filter_act"><a href="ShopServlet.do?id=${id}&name=${name}&estado=${estado}&order=${order}&search=${search}&busqueda=${busqueda}&moneda=dolar&price=${price}">Dolar($)</a></li>
										    		</c:when>
										    		<c:otherwise>
										    		 	 <li><a href="ShopServlet.do?id=${id}&name=${name}&estado=${estado}&order=${order}&search=${search}&busqueda=${busqueda}&moneda=dolar&price=${price}">Dolar($)</a></li>
										    		</c:otherwise>	
										    	</c:choose>

										    	<c:choose>
										    		<c:when test="${moneda=='pound'}">
										    			 <li class="filter_act"><a href="ShopServlet.do?id=${id}&name=${name}&estado=${estado}&order=${order}&search=${search}&busqueda=${busqueda}&moneda=pound&price=${price}">Libra(&pound;)</a></li>
										    		</c:when>
										    		<c:otherwise>
										    		 	 <li><a href="ShopServlet.do?id=${id}&name=${name}&estado=${estado}&order=${order}&search=${search}&busqueda=${busqueda}&moneda=pound&price=${price}">Libra(&pound;)</a></li>
										    		</c:otherwise>	
										    	</c:choose>

										    	<c:choose>
										    		<c:when test="${moneda=='yen'}">
										    			 <li class="filter_act"><a href="ShopServlet.do?id=${id}&name=${name}&estado=${estado}&order=${order}&search=${search}&busqueda=${busqueda}&moneda=yen&price=${price}">Yen(&yen;)</a></li>
										    		</c:when>
										    		<c:otherwise>
										    		 	 <li><a href="ShopServlet.do?id=${id}&name=${name}&estado=${estado}&order=${order}&search=${search}&busqueda=${busqueda}&moneda=yen&price=${price}">Yen(&yen;)</a></li>
										    		</c:otherwise>	
										    	</c:choose>
                                            </ul>
                                        </div>
                                        
                                        <div class="single__filter">
                                            <h2>Precio</h2>
                                            <ul class="filter__list">
                                            
                                            
										    	<c:choose>
										    		<c:when test="${price=='All'}">
										    			 <li class="filter_act"><a href="ShopServlet.do?id=${id}&name=${name}&estado=${estado}&order=${order}&search=${search}&busqueda=${busqueda}&moneda=${moneda}&price=All">Todos</a></li>
										    		</c:when>
										    		<c:otherwise>
										    		 	 <li><a href="ShopServlet.do?id=${id}&name=${name}&estado=${estado}&order=${order}&search=${search}&busqueda=${busqueda}&moneda=${moneda}&price=All">Todos</a></li>
										    		</c:otherwise>	
										    	</c:choose>
										    	
										    	<c:choose>
										    		<c:when test="${price=='1'}">
										    			 <li class="filter_act"><a href="ShopServlet.do?id=${id}&name=${name}&estado=${estado}&order=${order}&search=${search}&busqueda=${busqueda}&moneda=${moneda}&price=1">0-50</a></li>
										    		</c:when>
										    		<c:otherwise>
										    		 	 <li><a href="ShopServlet.do?id=${id}&name=${name}&estado=${estado}&order=${order}&search=${search}&busqueda=${busqueda}&moneda=${moneda}&price=1">0-50</a></li>
										    		</c:otherwise>	
										    	</c:choose>
										    	
										    	<c:choose>
										    		<c:when test="${price=='2'}">
										    			 <li class="filter_act"><a href="ShopServlet.do?id=${id}&name=${name}&estado=${estado}&order=${order}&search=${search}&busqueda=${busqueda}&moneda=${moneda}&price=2">50-100</a></li>
										    		</c:when>
										    		<c:otherwise>
										    		 	 <li><a href="ShopServlet.do?id=${id}&name=${name}&estado=${estado}&order=${order}&search=${search}&busqueda=${busqueda}&moneda=${moneda}&price=2">50-100</a></li>
										    		</c:otherwise>	
										    	</c:choose>
										    	
										    	<c:choose>
										    		<c:when test="${price=='3'}">
										    			 <li class="filter_act"><a href="ShopServlet.do?id=${id}&name=${name}&estado=${estado}&order=${order}&search=${search}&busqueda=${busqueda}&moneda=${moneda}&price=3">100-500</a></li>
										    		</c:when>
										    		<c:otherwise>
										    		 	 <li></li>
										    		</c:otherwise>	
										    	</c:choose>
										    	
										    	<c:choose>
										    		<c:when test="${price=='4'}">
										    			 <li class="filter_act"><a href="ShopServlet.do?id=${id}&name=${name}&estado=${estado}&order=${order}&search=${search}&busqueda=${busqueda}&moneda=${moneda}&price=3">100-500</a></li>
										    		</c:when>
										    		<c:otherwise>
										    		 	 <li><a href="ShopServlet.do?id=${id}&name=${name}&estado=${estado}&order=${order}&search=${search}&busqueda=${busqueda}&moneda=${moneda}&price=4">+500</a></li>
										    		</c:otherwise>	
										    	</c:choose>
                                            </ul>
                                        </div>
                                        <div class="single__filter">
                                            <h2>Categoria</h2>
                                            <ul class="filter__list">
                                            	<c:forEach items="${categories}" var="category">
                                            	
										    	<c:choose>
										    		<c:when test="${category.id==id}">
										    			 <li class="filter_act"><a href="ShopServlet.do?id=${category.id}&name=${category.name}&estado=${estado}&order=${order}&search=${search}&busqueda=${busqueda}&moneda=${moneda}&price=${price}">${category.name}</a></li>
										    		</c:when>
										    		<c:otherwise>
										    		 	 <li><a href="ShopServlet.do?id=${category.id}&name=${category.name}&estado=${estado}&order=${order}&search=${search}&busqueda=${busqueda}&moneda=${moneda}&price=${price}">${category.name}</a></li>
										    		</c:otherwise>	
										    	</c:choose>
                                            	</c:forEach>
                                            </ul>
                                        </div>
                                        
                                    </div>
                                    <!-- End Single Content -->
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- End Filter Menu -->
                    <!-- End Product MEnu -->
                    <div class="row">
                        <div class="product__list">
							<c:set var="user" scope="application" value="${user}"/>
							<c:forEach items="${products}" var="product">																					
                           		 <!-- Start Single Product -->                           		 
	          		        	<jsp:include page="/WEB-INF/html/Single-product.jsp">
	          		        		<jsp:param value="${product.first.image}" name="image"/>
	                				<jsp:param value="${product.first.price}" name="price"/>
	                				<jsp:param value="${product.first.title}" name="title"/>
	                				<jsp:param value="${product.first.currency}" name="currency"/>
	                				<jsp:param value="${product.first.id}" name="id"/>
	                				<jsp:param value="${product.first.idu}" name="idu"/>
	                				<jsp:param value="${product.first.soldout}" name="soldout"/>
	                				<jsp:param value="${product.second.size()}" name="favorites"/>
	                				<jsp:param value="${product.second.contains(user)}" name="favorite_user"/>
	                				<jsp:param value="${product.first.rapido}" name="rapido"/>
	                			</jsp:include>
                            	<!-- End Single Product -->
							</c:forEach>
                        </div>
                    </div>
                    
                    <%
                    
                    /*
                    <!-- Start Load More BTn -->
                    <div class="row mt--60">
                        <div class="col-md-12">
                            <div class="htc__loadmore__btn">
                                <a href="#">Más productos</a>
                            </div>
                        </div>
                    </div>
                    <!-- End Load More BTn -->*/

                    %>
                    

                </div>
            </div>
        </section>
        <!-- End Our Product Area -->
        
        <!-- Start Footer Area -->
     	<jsp:include page="/WEB-INF/html/Footer.jsp"></jsp:include>
        <!-- End Footer Area -->
        
    <!-- Body main wrapper end -->

       <!-- Start Footer Area -->
	   <jsp:include page="/WEB-INF/html/Foot.jsp"></jsp:include>
       <!-- End Footer Area -->

</body>

</html>