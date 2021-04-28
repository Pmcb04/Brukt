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

        <!-- Start Bradcaump area -->
        <div class="ht__bradcaump__area banner_white" style="background: rgba(0, 0, 0, 0) url(/images/slider/bg/paymment.jpg) no-repeat scroll center center / cover ;">
            <div class="ht__bradcaump__wrap">
                <div class="container">
                    <div class="row">
                        <div class="col-xs-12">
                            <div class="bradcaump__inner text-center">
                                <h2 class="bradcaump-title">Facturación</h2>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- End Bradcaump area -->
        <!-- Start Checkout Area -->
        <section class="our-checkout-area ptb--120 bg__white">
            <div class="container">
                <div class="row">
                    <div class="col-md-8 col-lg-8">
                        <div class="ckeckout-left-sidebar">
                            <!-- Start Checkbox Area -->
                            <div class="checkout-form">
                                <h2 class="section-title-3">detalles de facturaciÃ³n</h2>
                                <div class="checkout-form-inner">
                                    <div class="single-checkout-box">
                                        <input type="text" placeholder="Nombre*">
                                        <input type="text" placeholder="Apellidos*">
                                    </div>
                                    <div class="single-checkout-box">
                                        <input type="email" placeholder="Email*">
                                        <input type="text" placeholder="Telefono*">
                                    </div>
                                    <div class="single-checkout-box">
                                        <textarea name="message" placeholder="Mensaje*"></textarea>
                                    </div>
                                    <div class="single-checkout-box select-option mt--40">
                                        <select>
                                            <option>PaÃ­s*</option>
                                            <option>Bangladesh</option>
                                            <option>Bangladesh</option>
                                            <option>Bangladesh</option>
                                            <option>Bangladesh</option>
                                        </select>
                                        <input type="text" placeholder="Nombre de la compaÃ±ia*">
                                    </div>
                                    <div class="single-checkout-box">
                                        <input type="email" placeholder="Comunidad*">
                                        <input type="text" placeholder="Codigo postal*">
                                    </div>
                                    <div class="single-checkout-box checkbox">
                                        <input id="remind-me" type="checkbox">
                                        <label for="remind-me"><span></span>Create a Account ?</label>
                                    </div>
                                </div>
                            </div>
                            <!-- End Checkbox Area -->
                            <!-- Start Payment Box -->
                            <div class="payment-form">
                                <h2 class="section-title-3">Detalles de pago</h2>
                                <p>Lorem ipsum dolor sit amet, consectetur kgjhyt</p>
                                <div class="payment-form-inner">
                                    <div class="single-checkout-box">
                                        <input type="text" placeholder="Nombre de tarjeta*">
                                        <input type="text" placeholder="NÃºmero de tarjeta*">
                                    </div>
                                    <div class="single-checkout-box select-option">
                                        <select>
                                            <option>Date*</option>
                                            <option>Date</option>
                                            <option>Date</option>
                                            <option>Date</option>
                                            <option>Date</option>
                                        </select>
                                        <input type="text" placeholder="Codigo de seguridad*">
                                    </div>
                                </div>
                            </div>
                            <!-- End Payment Box -->
                            <!-- Start Payment Way -->
                            <div class="our-payment-sestem">
                                <h2 class="section-title-3">Tarjetas aceptadas :</h2>
                                <ul class="payment-menu">
                                    <li><a href="#"><img src="/images/payment/1.jpg" alt="payment-img"></a></li>
                                    <li><a href="#"><img src="/images/payment/2.jpg" alt="payment-img"></a></li>
                                    <li><a href="#"><img src="/images/payment/3.jpg" alt="payment-img"></a></li>
                                    <li><a href="#"><img src="/images/payment/4.jpg" alt="payment-img"></a></li>
                                    <li><a href="#"><img src="/images/payment/5.jpg" alt="payment-img"></a></li>
                                </ul>
                                <div class="checkout-btn">
                                    <a class="ts-btn btn-light btn-large hover-theme" href="#">REALIZAR PAGO</a>
                                </div>    
                            </div>
                            <!-- End Payment Way -->
                        </div>
                    </div>
                    <div class="col-md-4 col-lg-4">
                        <div class="checkout-right-sidebar">
                            <div class="our-important-note">
                                <h2 class="section-title-3">Note :</h2>
                                <p class="note-desc">Lorem ipsum dolor sit amet, consectetur adipisici elit, sed do eiusmod tempor incididunt ut laborekf et dolore magna aliqua.</p>
                                <ul class="important-note">
                                    <li><a href="#"><i class="zmdi zmdi-caret-right-circle"></i>Lorem ipsum dolor sit amet, consectetur nipabali</a></li>
                                    <li><a href="#"><i class="zmdi zmdi-caret-right-circle"></i>Lorem ipsum dolor sit amet</a></li>
                                    <li><a href="#"><i class="zmdi zmdi-caret-right-circle"></i>Lorem ipsum dolor sit amet, consectetur nipabali</a></li>
                                    <li><a href="#"><i class="zmdi zmdi-caret-right-circle"></i>Lorem ipsum dolor sit amet, consectetur nipabali</a></li>
                                    <li><a href="#"><i class="zmdi zmdi-caret-right-circle"></i>Lorem ipsum dolor sit amet</a></li>
                                </ul>
                            </div>
                            <div class="puick-contact-area mt--60">
                                <h2 class="section-title-3">Quick Contract</h2>
                                <a href="phone:+8801722889963">+88 01900 939 500</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- End Checkout Area -->

    <!-- Start Foot Style -->
  	<jsp:include page="/WEB-INF/html/Footer.jsp"></jsp:include>
     <!-- End Foot Style -->

    <!-- Body main wrapper end -->

       <!-- Start Footer Area -->
	   <jsp:include page="/WEB-INF/html/Foot.jsp"></jsp:include>
       <!-- End Footer Area -->

</body>

</html>