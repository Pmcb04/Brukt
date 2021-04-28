<%@ page language="java" contentType="text/html; charset=UTF-8"     pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html class="no-js" lang="es">
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
        
        <!-- Start Banner Style -->
     	<jsp:include page="/WEB-INF/html/Banner.jsp">
     		<jsp:param value="${banner}" name="image"/>
     	</jsp:include>
        <!-- End Banner Style -->
        
        <!-- Start Our Store Area -->
        <section class="htc__store__area ptb--120 bg__white">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="section__title section__title--2 text-center">
                            <h2 class="title__line">Bienvenido a Brukt</h2>
                            <p>
                                Aqui encontrarás muchos productos de segunda mano, con el mejor precio que se puede pagar por ellos. 
                                Tenemos todo tipos de productos para satisfacerte. Gracias por confiar en nosotros. 
                            </p>
                        </div>
                        <div class="store__btn">
                            <a href="ContactServlet.do">Contactanos</a>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- End Our Store Area -->
        <!-- Start Choose Us Area -->
        <section class="htc__choose__us__ares bg__white">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-6 col-lg-6 col-sm-12 col-xs-12">
                        <div class="image_about">
                            <img src="${pageContext.request.contextPath}/images/about/elegir.jpg" alt="">
                        </div>
                    </div>
                    <div class="col-md-6 col-lg-6 col-sm-12 col-xs-12">
                        <div class="htc__choose__wrap bg__cat--4">
                            <h2 class="choose__title">¿Por que debes elegirnos?</h2>
                            <div class="choose__container">
                                <div class="single__chooose">
                                    <div class="choose__us">
                                        <div class="choose__icon">
                                            <span class="ti-heart"></span>
                                        </div>
                                        <div class="choose__details">
                                            <h4>Cuidados Especiales</h4>
                                            <p>Cualquiera que pase por nosotros, recibirÃ¡ los mejores cuidados que podemos ofrecer. </p>
                                        </div>
                                    </div>
                                    <div class="choose__us">
                                        <div class="choose__icon">
                                            <span class="ti-truck"></span>
                                        </div>
                                        <div class="choose__details">
                                            <h4>Envío gratuito</h4>
                                            <p>Tenemos miles de productos con el enví­o gratuito para tí­.</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="single__chooose">
                                    <div class="choose__us">
                                        <div class="choose__icon">
                                            <span class="ti-reload"></span>
                                        </div>
                                        <div class="choose__details">
                                            <h4>Dinero seguro</h4>
                                            <p>El pago al vendedor no se realiza hasta que no recibas el producto. </p>
                                        </div>
                                    </div>
                                    <div class="choose__us">
                                        <div class="choose__icon">
                                            <span class="ti-time"></span>
                                        </div>
                                        <div class="choose__details">
                                            <h4>Soporte 24/7</h4>
                                            <p>En el momento que quieras, nos tienes a tu disposición. </p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- End Choose Us Area -->
      
        <!-- Start brand Area -->
        <div class="htc__brand__area bg__white ptb--120">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <ul class="brand__list">
                            <li><a href="#">
                                <img src="${pageContext.request.contextPath}/images/brand/1.png" alt="brand images">
                            </a></li>
                            <li><a href="#">
                                <img src="${pageContext.request.contextPath}/images/brand/2.png" alt="brand images">
                            </a></li>
                            <li><a href="#">
                                <img src="${pageContext.request.contextPath}/images/brand/3.png" alt="brand images">
                            </a></li>
                            <li><a href="#">
                                <img src="${pageContext.request.contextPath}/images/brand/4.png" alt="brand images">
                            </a></li>
                            <li class="hidden-sm"><a href="#">
                                <img src="${pageContext.request.contextPath}/images/brand/5.png" alt="brand images">
                            </a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <!-- End brand Area -->
        
        <!-- Start Footer Area -->
     	<jsp:include page="/WEB-INF/html/Footer.jsp"></jsp:include>
        <!-- End Footer Area -->
        
    <!-- Body main wrapper end -->
    
       <!-- Start Footer Area -->
	   <jsp:include page="/WEB-INF/html/Foot.jsp"></jsp:include>
       <!-- End Footer Area -->

</body>

</html>