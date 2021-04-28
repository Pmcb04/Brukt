<%@ page language="java" contentType="text/html; charset=UTF-8"     pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <!-- Body main wrapper start -->
 <div class="wrapper fixed__footer">
<header id="header" class="htc-header header--3 bg__white">
    <!-- Start Mainmenu Area -->
    <div id="sticky-header-with-topbar" class="mainmenu__area sticky__header">
        <div class="container">
            <div class="row">
                <div class="col-md-2 col-lg-2 col-sm-3 col-xs-3">
                    <div class="logo">
                        <a href="${pageContext.request.contextPath}/IndexServlet.do">
                            <img src="${pageContext.request.contextPath}/images/logo/icon.png" alt="logo">
                        </a>
                    </div>
                </div>
                <!-- Start MAinmenu Ares -->
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
                                <li><a href="${pageContext.request.contextPath}/AboutServlet">Sobre nosotros</a></li>
                                <li><a href="${pageContext.request.contextPath}/ContactServlet.do">Contacto</a></li>
                            </ul>
                        </nav>
                    </div>                          
                </div>
                <!-- End MAinmenu Ares -->
                <div class="col-md-2 col-sm-4 col-xs-3">  
                    <ul class="menu-extra">
                        <li class="search search__open hidden-xs"><span class="ti-search"></span></li>
                        <li><a href="${pageContext.request.contextPath}/LoginServlet.do"><span class="ti-user"> ${user.username}</span></a></li>
                    </ul>
                </div>
            </div>
            <div class="mobile-menu-area"></div>
        </div>
    </div>
    <!-- End Mainmenu Area -->
</header>
