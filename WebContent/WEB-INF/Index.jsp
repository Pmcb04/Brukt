<%@ page language="java" contentType="text/html; charset=UTF-8"     pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="es">
<head>
	<!-- Start Head Area -->
    <jsp:include page="/WEB-INF/html/Head.jsp">
    	<jsp:param value="${title}" name="title"/>
    </jsp:include>
    <!-- End Head area -->
</head>

<body>

    <!-- Body main wrapper start -->
    <div class="wrapper fixed__footer">
        <!-- Start Header Style -->
        <header id="header" class="htc-header" >
            <!-- Start Mainmenu Area -->
            <div id="sticky-header-with-topbar" class="mainmenu__area sticky__header header--2">
                <div class="container">
                    <div class="row">
                        <div class="col-md-2 col-lg-2 col-sm-3 col-xs-3">
                            <div class="logo">
                                <a href="${pageContext.request.contextPath}/IndexServlet.do">
                                    <img src="${pageContext.request.contextPath}/images/logo/icon.png" alt="logo">
                                </a>
                            </div>
                        </div>
                        <!-- Start Mainmenu Ares -->
                        <div class="col-md-8 col-lg-8 col-sm-6 col-xs-6">
                            <nav class="mainmenu__nav hidden-xs hidden-sm">
                                <ul class="main__menu">
                                    <li class="drop"><a href="${pageContext.request.contextPath}/IndexServlet.do">Home</a></li>
                                    <li><a href="${pageContext.request.contextPath}/AboutServlet.do">Sobre nosotros</a></li>
                                    <li><a href="${pageContext.request.contextPath}/ContactServlet.do">Contacto</a></li>
                                </ul>
                            </nav>
                            <div class="mobile-menu clearfix visible-xs visible-sm">
                                <nav id="mobile_dropdown">
                                    <ul>
                                        <li><a href="${pageContext.request.contextPath}/IndexServlet.do">Home</a></li>
                                        <li><a href="${pageContext.request.contextPath}/AboutServlet.do">Sobre nosotros</a></li>
                                        <li><a href="${pageContext.request.contextPath}/ContactServlet.do">Contacto</a></li>
                                    </ul>
                                </nav>
                            </div>                          
                        </div>
                        <!-- End MAinmenu Ares -->
                        <div class="col-md-2 col-sm-4 col-xs-3">  
                            <ul class="menu-extra">
                                <li class="search search__open hidden-xs"><span class="ti-search"></span></li>
                                <li><a href="LoginServlet.do"><span class="ti-user"> ${user.username}</span></a></li>

                            </ul>
                        </div>
                    </div>
                    <div class="mobile-menu-area"></div>
                </div>
            </div>
            <!-- End Mainmenu Area -->
        </header>
        <!-- End Header Style -->
       
		<!-- Start Search Style -->
     	<jsp:include page="/WEB-INF/html/Search.jsp"></jsp:include>
        <!-- End Search Style -->

        <!-- Start Slider Area -->
        <div class="slider__container slider--two">
            <div class="slider__activation__wrap--2">
                <!-- Start Single Slide -->
                <div class="slider__full--screen" style="background: rgba(0, 0, 0, 0) url(${pageContext.request.contextPath}/images/banner/background.jpg) no-repeat scroll center center / cover ;">
                    <div class="container">
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="slider__content--2 text-center">
                                    <div class="slider__inner--2 text-center">
                                        <h1>No pierdas tiempo ni dinero</h1>
                                        <div class="slider__btn">
                                            <a class="htc__btn" href="ShopServlet.do?id=Todas&order=asc&estado=All&moneda=All&price=All&search=no&premium=All">comprar ahora</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- End Single Slide -->
            </div>
        </div>
        <!-- Start Banner Area -->
        <!-- Start Our Product Area -->
        <section class="htc__best__product__area pb--100 bg__white">
            <div class="container">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="section__title text-center">
                            <h2 class="title__line">Escoja su nuevo producto</h2>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="best__product__list clearfix mt--30">
                		<c:forEach items="${categories}" var="category">
          		        	<jsp:include page="/WEB-INF/html/Single-category.jsp">
                				<jsp:param value="${category.name}" name="name"/>
                				<jsp:param value="${category.image}" name="image"/>
                				<jsp:param value="${category.id}" name="id"/>
                			</jsp:include>
						</c:forEach>
                    </div>
                </div>
            </div>
        </section>
        <!-- End Our Product Area -->

        <!-- Start Footer Area -->
		<jsp:include page="/WEB-INF/html/Footer.jsp"></jsp:include>
        <!-- End Footer Area -->
    </div>
    <!-- Body main wrapper end -->
 
       <!-- Start Footer Area -->
	   <jsp:include page="/WEB-INF/html/Foot.jsp"></jsp:include>
       <!-- End Footer Area -->

</body>

</html>
