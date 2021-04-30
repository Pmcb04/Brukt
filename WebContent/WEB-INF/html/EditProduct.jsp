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
        <div class="htc__login__register bg__white ptb--150">
            <div class="container">
                <div class="row">
                    <div class="col-md-6 col-md-offset-3">
                        <ul class="login__register__menu" role="tablist">
                            <li role="presentation" class="register active"><p role="tab" data-toggle="tab">Editar usuario</p></li>
                        </ul>
                    </div>
                </div>
                <!-- Start Login Register Content -->
                <div class="row">
                    <div class="col-md-6 col-md-offset-3">
                        <div class="htc__login__register__wrap">
                            <!-- Start Single Content -->
                           	<div id="login" role="tabpanel" class="single__tabs__panel tab-pane fade in active">
                                <form id="target" class="login" method="post" enctype="multipart/form-data">
                                
                                        <label for="input">
                                            <img id="img" src="${pageContext.request.contextPath}/images/product/${product.image}" class="rounded-circle"> 			        
                                        </label>
                                        
                                        <input onchange="file_changed();" id="input" name="file" type="file" style="display: none;"/>
                                        
                                        <script>
                                        
                                        function file_changed(){
                                            var selectedFile = document.getElementById('input').files[0];
                                            var img = document.getElementById('img')
                    
                                            var reader = new FileReader();
                                            reader.onload = function(){
                                                img.src = this.result
                                            }
                                            reader.readAsDataURL(selectedFile);
                                        }
                                         
                                        </script>
                                        

                                    <input class="input" type="text" placeholder="${product.title}" name="name">
                                    <input class="input" type="text" placeholder="${product.description}" name="description">
                                    <input class="input" type="number" step="1"    min="1" placeholder="${product.stock}" name="numero">
                                    <input class="input" type="number" step="0.01" min="0.01" placeholder="${product.price}" name="precio">
									
									<p>Moneda : </p>
										<select class="input" name="moneda">
										<option selected>Euro</option>
										<option>Dolar</option>
										<option>Libra</option>
										<option>Yen</option>
									</select>
									
									<p>Categoria: </p>
										<select class="input" name="categoria">
											<c:forEach items="${categories}" var="category">
			          		        			<option>${category.name}</option>
											</c:forEach>
									</select>
									
									<p>Estado : </p>
										<select class="input" name="estado">
										<option>En venta</option>
										<option>Vendido</option>
									</select>
									
									<p>Envio rápido (24h)</p>
									<label for="si">Si</label><br>
									<input class="radio" type="radio" id="si" name="rapido" value="Si">
									<label for="no">No</label><br>
									<input class="radio" type="radio" id="no" name="rapido" value="No">
									
									<c:forEach items="${messages}" var="message">
									    <p class="error_message">${message.value}</p>
									</c:forEach>
									
                                    <div class="htc__login__btn">
                                    	<input type="submit" value="Guardar">
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