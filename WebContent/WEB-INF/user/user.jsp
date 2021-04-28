<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
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
        
             	<!-- Start Header Style -->
     	<jsp:include page="/WEB-INF/html/Search.jsp"></jsp:include>
        <!-- End Header Style -->


  <div class="main-content">

    <!-- Page content -->
    <div class="container-fluid pd--150 bg__white">
      <div class="row">
        <div class="col-xl-4 order-xl-2 mb-5 mb-xl-0">
          <div class="card card-profile shadow">
            <div class="row justify-content-center">
              <div class="col-lg-3 order-lg-2">
                 <div class="card-profile-image image-upload">
                 <form action="EditUserServlet.do" method="post">
				    <label for="file-input">
				        <img id="user-image" src="${pageContext.request.contextPath}/images/user/${user.image}" class="rounded-circle"> 			        
				    </label>
				    
				    <input id="file-input" name="foto" type="file"/>
				    
				    <script type="text/javascript">
				    
			           $("#file-input").change(function () {
				            if (this.files && this.files[0]) {
				                var reader = new FileReader();
				                reader.onload = function (e) {
				                    $('#user-image').attr('${pageContext.request.contextPath}/images/user/', e.target.result);
				                }
				                reader.readAsDataURL(this.files[0]);
				            }
				        });
			         
			        </script>
				    
	            </form>
                </div>
              </div>
            </div>
            <div class="card-header text-center border-0 pt-8 pt-md-4 pb-0 pb-md-4">
              <div class="d-flex justify-content-between icons_profile">
                <a href="#myModal"  data-toggle="modal"><span class="material-icons">delete</span></a>
                <a href="ChatServlet.do"><span class="material-icons">question_answer</span></a>
              </div>
            </div>
            <div class="card-body pt-0 pt-md-4">
              <div class="row">
                <div class="col">
                  <div class="card-profile-stats d-flex justify-content-center mt-md-5">
                    <div>
                      <span class="heading">${user_venta}</span>
                      <span class="description">En venta</span>
                    </div>
                    <div>
                      <span class="heading">${user_vendido}</span>
                      <span class="description">Vendidos</span>
                    </div>
                    <div>
                      <span class="heading">${favorites_user.size()}</span>
                      <span class="description">Favoritos</span>
                    </div>
                  </div>
                </div>
              </div>
              <div class="text-center">
                <h3>
                  ${user.username}<span class="font-weight-light"></span>
                </h3>
                <div class="h5 font-weight-300">
                  <i class="ni location_pin mr-2"></i>${user.email}
                </div>
                
                <hr class="my-4">
                	<form action="UserServlet.do?action=close" method="post"><button class="btn btn-sm btn-close float-center">Cerrar sesión</button></form>
              </div>
            </div>
          </div>
        </div>
        <div class="col-xl-8 order-xl-1 shadow">
         
            <div class="card-body">
              <form action="UserServlet.do?action=data" method="post">
                <h6 class="heading-small text-muted mb-4">Información del usuario</h6>
                <div class="pl-lg-4">
                  <div class="row">
                  
                    <div class="col-lg-6">
                      <div class="form-group focused">
                        <label class="form-control-label" for="input-username">Nombre de usuario</label>
                        <input type="text" id="username" name="username" class="form-control form-control-alternative" placeholder="Username" value="${user.username}">
                      </div>
                    </div>
                    
                    <div class="col-lg-6">
                      <div class="form-group">
                        <label class="form-control-label" for="input-email">Dirección de email</label>
                        <input type="email" id="email" name="email" class="form-control form-control-alternative" placeholder="Email" value="${user.email}">
                      </div>
                    </div>
                    
                    <div class="col-lg-6">
                      <div class="form-group">
                        <label class="form-control-label" for="input-password">Contraseña</label>
                        <input type="password" id="password" name="password" class="form-control form-control-alternative" placeholder="Contraseña" value="${user.password}">                      
                        <i class="far fa-eye" id="togglePassword"></i>								
                      </div>
                    </div>
                    
                     <div class="col-lg-6">
                      <div class="form-group">
                        <label class="form-control-label" for="input-genero">Genero</label>
							<select class="form-control form-control-alternative" name="genero">
								<option>Hombre</option>
								<option>Mujer</option>
							</select>						
                      </div>
                    </div>
                    
                  </div>
                </div>
                <hr class="my-4">
                
               	<button type="submit" class="btn btn-sm">Guardar Cambios</button>
                
              </form>
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
           				<jsp:param value="${product.first.soldout}" name="soldout"/>
						<jsp:param value="${product.first.image}" name="image"/>
						<jsp:param value="${product.first.rapido}" name="rapido"/>
           				<jsp:param value="${product.second.size()}" name="favorites"/>
						<jsp:param value="${product.second.contains(user)}" name="favorite_user"/>
       				</jsp:include>
                  	<!-- End Single Product -->
				</c:forEach>
				
				
				<div class="col-md-3 single__pro col-lg-3 hidden-sm col-xs-12 cat--3">
				    <div class="product foo">
				        <div class="product__inner">
				            <div class="pro__thumb">
				                <a href="${pageContext.request.contextPath}/AddProductServlet.do"> 
				                    <img src="${pageContext.request.contextPath}/images/product/add_product.png" alt="product images">
				                </a>
				            </div>
				        </div>
				    </div>
				</div>
				
			</div>
			
			
           <c:choose>
    		<c:when test="${favorites_user.size() > '0'}">
    		
				<div class="row">
					<h2 class="titles_user">Articulos favoritos</h2>
					
					<c:forEach items="${favorites_user}" var="product">
	                 	<!-- Start Single Product -->
	 		        	<jsp:include page="/WEB-INF/html/Single-product.jsp">
	           				<jsp:param value="${product.first.price}" name="price"/>
	           				<jsp:param value="${product.first.title}" name="title"/>
	           				<jsp:param value="${product.first.currency}" name="currency"/>
	           				<jsp:param value="${product.first.id}" name="id"/>
	           				<jsp:param value="${product.first.idu}" name="idu"/>
	           				<jsp:param value="${product.first.soldout}" name="soldout"/>
	 		        		<jsp:param value="${product.first.image}" name="image"/>
							<jsp:param value="${product.first.rapido}" name="rapido"/>
	           				<jsp:param value="${product.second.size()}" name="favorites"/>
							<jsp:param value="${product.second.contains(user)}" name="favorite_user"/>
	           				
	       				</jsp:include>
	                  	<!-- End Single Product -->
					</c:forEach>
				</div>
    			 
    		</c:when>	
    		</c:choose>
			
			
			
		</div>
	</div>

       <!-- Start Footer Style -->
  	   <jsp:include page="/WEB-INF/html/Footer.jsp"></jsp:include>
       <!-- End Footer Style -->

       <!-- Start Footer Area -->
	   <jsp:include page="/WEB-INF/html/Foot.jsp"></jsp:include>
       <!-- End Footer Area -->
       
       
	<!-- QUICKVIEW DELETE -->
	<div id="myModal" class="modal fade">
		<div class="modal-dialog modal-confirm">
			<div class="modal-content">
				<div class="modal-header flex-column">
					<div class="icon-box">
						<i class="material-icons">&#xE5CD;</i>
					</div>						
					<h4 class="modal-title w-100">¿Estas seguro?</h4>	
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				</div>
				<div class="modal-body">
					<p>¿Realmente desea borrar su perfil? Este proeso no tendrá vuelta atras.</p>
				</div>
				<div class="modal-footer justify-content-center">
					<button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
					<form action="UserServlet.do?action=delete" method="post"><button type="submit" class="btn btn-danger">Eliminar</button></form>
				</div>
			</div>
		</div>
	</div>     
    <!-- END QUICKVIEW DELETE -->
       

</body>


