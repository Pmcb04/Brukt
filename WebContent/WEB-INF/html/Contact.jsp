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

        <!-- Start Contact Area -->
        <section class="htc__contact__area ptb--120 bg__white">
            <div class="container">
                <div class="row">
                    <div class="col-md-6 col-lg-6 col-sm-12 col-xs-12">
                        <div class="htc__contact__container">
                            <div class="htc__contact__address">
                                <h2 class="contact__title">informacion de contacto</h2>
                                <div class="contact__address__inner">
                                    <!-- Start Single Adress -->
                                    <div class="single__contact__address">
                                        <div class="contact__icon">
                                            <span class="ti-location-pin"></span>
                                        </div>
                                        <div class="contact__details">
                                            <p>Localizacion : <br> Edificio Cadagua, Paseo de la Castellana,  89-91, <br> 28046 Madrid.</p>
                                        </div>
                                    </div>
                                    <!-- End Single Adress -->
                                </div>
                                <div class="contact__address__inner">
                                    <!-- Start Single Adress -->
                                    <div class="single__contact__address">
                                        <div class="contact__icon">
                                            <span class="ti-mobile"></span>
                                        </div>
                                        <div class="contact__details">
                                            <p> Telefono : <br><a href="#">+34 912 222 333 44</a></p>
                                        </div>
                                    </div>
                                    <!-- End Single Adress -->
                                </div>
                                <div class="contact__address__inner">
                                    <!-- Start Single Adress -->
                                    <div class="single__contact__address">
                                        <div class="contact__icon">
                                            <span class="ti-email"></span>
                                        </div>
                                        <div class="contact__details">
                                            <p> Correo :<br><a href="#">clientes@brukt.com</a></p>
                                        </div>
                                    </div>
                                    <!-- End Single Adress -->
                                </div>
                            </div>
                            <div class="contact-form-wrap">
                                <div class="contact-title">
                                    <h2 class="contact__title">Ponerse en contacto</h2>
                                </div>
                                <form id="contact-form" action="" method="">
                                    <div class="single-contact-form">
                                        <div class="contact-box name">
                                            <input type="text" name="name" placeholder="Tu nombre*">
                                            <input type="email" name="email" placeholder="Correo*">
                                        </div>
                                    </div>
                                    <div class="single-contact-form">
                                        <div class="contact-box subject">
                                            <input type="text" name="subject" placeholder="Concepto*">
                                        </div>
                                    </div>
                                    <div class="single-contact-form">
                                        <div class="contact-box message">
                                            <textarea name="message"  placeholder="Mensaje*"></textarea>
                                        </div>
                                    </div>
                                    <div class="contact-btn">
                                        <button type="submit" class="fv-btn">ENVIAR</button>
                                    </div>
                                </form>
                            </div> 
                        </div>
                    </div>
                    <div class="col-md-6 col-lg-6 col-sm-12 col-xs-12 smt-30 xmt-30">
                        <div class="map-contacts">
                            <div id="googleMap">
                                <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d602.4400716568435!2d-3.6919621093864086!3d40.45075943215268!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0xd4228e33c3c219b%3A0x632577090b4577f7!2sEdificio%20Cadagua%2C%20Paseo%20de%20la%20Castellana%2C%2089-91%2C%2028046%20Madrid!5e0!3m2!1ses!2ses!4v1615564414672!5m2!1ses!2ses" width="600" height="600" style="border:0;" allowfullscreen="" loading="lazy"></iframe>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- End Contact Area -->
        
        
        <!-- Start Footer Area -->
	    <jsp:include page="/WEB-INF/html/Footer.jsp"></jsp:include>
        <!-- End Footer Area -->
        
    <!-- Body main wrapper end -->
    
       <!-- Start Footer Area -->
	   <jsp:include page="/WEB-INF/html/Foot.jsp"></jsp:include>
       <!-- End Footer Area -->

</body>

</html>