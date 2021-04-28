<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    <!-- Body main wrapper start -->
    
     	<!-- Start Header Style -->
     	<jsp:include page="/WEB-INF/html/Header.jsp"></jsp:include>
        <!-- End Header Style -->
        
        <!-- Start Header Style -->
     	<jsp:include page="/WEB-INF/html/Search.jsp"></jsp:include>
        <!-- End Header Style -->
        
        <!-- Start Login Register Area -->
        <div class="htc__login__register ptb--120 bg__white">
            <div class="container" >
                <div class="row">
                    <div class="col-md-6 col-md-offset-3">
                        <ul class="login__register__menu" role="tablist">
                            <li role="presentation" class="register active"><p role="tab" data-toggle="tab">Registro</p></li>
                        </ul>
                    </div>
                </div>
                <!-- Start Login Register Content -->
                <div class="row">
                    <div class="col-md-6 col-md-offset-3">
                        <div class="htc__login__register__wrap">
                            <!-- Start Single Content -->
                           	<div id="login" role="tabpanel" class="single__tabs__panel tab-pane fade in active">
                                <form class="login" method="post" enctype="multipart/form-data">
                                
                                	<input type="file" name="file" />	
                                    <input class="input" type="text" placeholder="Nombre de usuario*" name="username">
                                    <input class="input" type="email" placeholder="Email*" name="email">
                                    <input class="input" type="password" placeholder="Contraseña*" name="password">
                                    <input class="input" type="password" placeholder="Introduzca contraseña de nuevo*" name="password_again">

                                     <p>Genero : </p>
										<select class="input" name="genero">
										<option>Hombre</option>
										<option>Mujer</option>
									</select>
									
									<div class="tabs__checkbox">
                                    	<span class="register active"><a href="LoginServlet.do">Login</a></span>
                                	</div>
                                    
									<c:forEach items="${messages}" var="message">
									    <p class="error_message">${message.value}</p>
									</c:forEach>
                                    
                                    <div class="htc__login__btn">
                                    	<input type="submit" value="Registro">
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