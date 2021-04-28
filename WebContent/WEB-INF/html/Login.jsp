<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html class="no-js" lang="es">
<head>
	<!-- Start Head Area -->
    <jsp:include page="/WEB-INF/html/Head.jsp">
    	<jsp:param value="${title}" name="title"/>
    </jsp:include>
    
     <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
     <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css">
    
    
    <!-- End Head Area -->
</head>

<body>
    <!-- Body main wrapper start -->
    
     	<!-- Start Header Style -->
     	<jsp:include page="/WEB-INF/html/Header.jsp"></jsp:include>
        <!-- End Header Style -->
        
        <!-- Start Search Style -->
     	<jsp:include page="/WEB-INF/html/Search.jsp"></jsp:include>
        <!-- End Search Style -->
        
        <!-- Start Login Register Area -->
        <div class="htc__login__register bg__white ptb--150">
            <div class="container">
                <div class="row">
                    <div class="col-md-6 col-md-offset-3">
                        <ul class="login__register__menu" role="tablist">
                            <li role="presentation" class="login active"><p role="tab" data-toggle="tab">Login</p></li>
                        </ul>
                    </div>
                </div>
                <!-- Start Login Register Content -->
                <div class="row">
                    <div class="col-md-6 col-md-offset-3">
                        <div class="htc__login__register__wrap">
                            <!-- Start Single Content -->
                            <div id="login" role="tabpanel" class="single__tabs__panel tab-pane fade in active">
	                                <form class="login" method="post">
                                    	<input class="input" type="text" placeholder="Usuario" name="username">
										 <input class="input" type="password" name="password" id="password" placeholder="Enter the password">
        								
        								<div class="eye">
        									<i class="far fa-eye" id="togglePassword"></i>
        								</div>
        								
        							
                                    	<div class="tabs__checkbox">
                                    		<span class="register"><a href="RegisterUserServlet.do">Registrar</a></span>
                                    		<span class="forget"><a href="#">Recuperar contraseña</a></span>
                                		</div>
                                    
	                                    <div class="htc__login__btn ">
	                                    	<input type="submit" value="Entrar">
	                               		 </div>
                                    
                                	</form>

                            </div>
                            <!-- End Single Content -->
                        </div>
                    </div>
                </div>
                <!-- End Login Register Content -->
            </div>
        </div>
        <!-- End Login Register Area -->
        
        
    <!-- Start Footer Style -->
  	<jsp:include page="/WEB-INF/html/Footer.jsp"></jsp:include>
     <!-- End Footer Style -->

    <!-- Body main wrapper end -->

    <!-- Start Foot Style -->
  	<jsp:include page="/WEB-INF/html/Foot.jsp"></jsp:include>
     <!-- End Foot Style -->

</body>

</html>