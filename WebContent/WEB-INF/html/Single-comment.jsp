<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <div class="pro__review ans">
    <div class="review__details">
        <div class="review__info">
            <h4><a href="UserPublicServlet.do?username_user=${param.username}">${param.username}</a></h4>
            <ul class="rating">
            	<c:choose>
		    		<c:when test="${param.rating=='1'}">
		    		    <li><i class="zmdi zmdi-star"></i></li>		                
		    		    <li><i class="zmdi zmdi-star-outline"></i></li>
		                <li><i class="zmdi zmdi-star-outline"></i></li>
		                <li><i class="zmdi zmdi-star-outline"></i></li>
		                <li><i class="zmdi zmdi-star-outline"></i></li>
		    		</c:when>
		    		<c:when test="${param.rating=='2'}">	
		                <li><i class="zmdi zmdi-star"></i></li>
		                <li><i class="zmdi zmdi-star"></i></li>
		                <li><i class="zmdi zmdi-star-outline"></i></li>
		                <li><i class="zmdi zmdi-star-outline"></i></li>
		                <li><i class="zmdi zmdi-star-outline"></i></li>
		    		</c:when>
		    		<c:when test="${param.rating=='3'}">
		                <li><i class="zmdi zmdi-star"></i></li>
		                <li><i class="zmdi zmdi-star"></i></li>
		                <li><i class="zmdi zmdi-star"></i></li>
		                <li><i class="zmdi zmdi-star-outline"></i></li>
		                <li><i class="zmdi zmdi-star-outline"></i></li>
		    		</c:when>
		    		<c:when test="${param.rating=='4'}">
		                <li><i class="zmdi zmdi-star"></i></li>
		                <li><i class="zmdi zmdi-star"></i></li>
		                <li><i class="zmdi zmdi-star"></i></li>
		                <li><i class="zmdi zmdi-star"></i></li>
		                <li><i class="zmdi zmdi-star-outline"></i></li>
		    		</c:when>
		    		<c:when test="${param.rating=='5'}">
		                <li><i class="zmdi zmdi-star"></i></li>
		                <li><i class="zmdi zmdi-star"></i></li>
		                <li><i class="zmdi zmdi-star"></i></li>
		                <li><i class="zmdi zmdi-star"></i></li>
		                <li><i class="zmdi zmdi-star"></i></li>
		    		</c:when>
		    		
		    		<c:otherwise>
		                <li><i class="zmdi zmdi-star-outline"></i></li>
		                <li><i class="zmdi zmdi-star-outline"></i></li>
		                <li><i class="zmdi zmdi-star-outline"></i></li>		             
		                <li><i class="zmdi zmdi-star-outline"></i></li>
		                <li><i class="zmdi zmdi-star-outline"></i></li>
		    		</c:otherwise>	
		    	</c:choose>
            </ul>
            <c:choose>
            		<c:when test="${param.username==user.username}">
		            <div class="rating__send">
               			 <a href="DeleteCommentServlet.do?idp=${param.idp}&favorites=${param.favorites}&favoriteUser=${param.favorite_user}"><i class="zmdi zmdi-close"></i></a>
		            </div>
		    		</c:when>
            </c:choose>
        </div>
        <div class="review__date">
            <span>${param.date}</span>
        </div>
        <p>${param.comment}</p>
    </div>
</div>