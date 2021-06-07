<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
						<div class="col-md-3 col-lg-3 col-sm-4 col-xs-12">
                            <div class="best__product foo">
                                <div class="best__product__inner">
                                    <div class="best__product__thumb">
                                        <a href="#">
                                            <img src="${pageContext.request.contextPath}/images/category/${param.image}" alt="category image">
                                        </a>
                                    </div>
                                    <div class="bst__pro__hover__info">
                                        <div class="bst__pro__hover__action">
                                            <h2><a class="category_name" href="ShopServlet.do?id=${param.id}&name=${param.name}&estado=All&order=asc&search=no&busqueda=&moneda=All&price=All&premium=All">${param.name}</a></h2>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        