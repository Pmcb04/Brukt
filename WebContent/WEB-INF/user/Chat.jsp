<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="en">
<head>
  
  	<!-- Start Head Area -->
    <jsp:include page="/WEB-INF/html/Head.jsp">
    	<jsp:param value="${title}" name="title"/>
    </jsp:include>
    <!-- End Head Area -->
  
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  
  <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
  <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>  
  <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  
  <link href="${pageContext.request.contextPath}/css/chat.css" rel="stylesheet">
  
  
</head>
<body>

  	<!-- Start Header Area -->
    <jsp:include page="/WEB-INF/html/Header.jsp"></jsp:include>
    <!-- End Header Area -->


<div class="messaging">
  <div class="inbox_msg">
	<div class="inbox_people">
	  <div class="headind_srch">
		<div class="recent_heading">
		  <h4>Recent</h4>
		</div>
		<div class="srch_bar">
		  <div class="stylish-input-group">
			<input type="text" class="search-bar"  placeholder="Search" >
			</div>
		</div>
	  </div>
	  <div class="inbox_chat scroll">
		<div class="chat_list active_chat">
		  <div class="chat_people">
			<div class="chat_img"> <img src="https://ptetutorials.com/images/user-profile.png" alt="sunil"> </div>
			<div class="chat_ib">
			  <h5>Sunil Rajput <span class="chat_date">Dec 25</span></h5>
			  <p>Test, which is a new approach to have all solutions 
				astrology under one roof.</p>
			</div>
		  </div>
		</div>
		<div class="chat_list">
		  <div class="chat_people">
			<div class="chat_img"> <img src="https://ptetutorials.com/images/user-profile.png" alt="sunil"> </div>
			<div class="chat_ib">
			  <h5>Sunil Rajput <span class="chat_date">Dec 25</span></h5>
			  <p>Test, which is a new approach to have all solutions 
				astrology under one roof.</p>
			</div>
		  </div>
		</div>
		<div class="chat_list">
		  <div class="chat_people">
			<div class="chat_img"> <img src="https://ptetutorials.com/images/user-profile.png" alt="sunil"> </div>
			<div class="chat_ib">
			  <h5>Sunil Rajput <span class="chat_date">Dec 25</span></h5>
			  <p>Test, which is a new approach to have all solutions 
				astrology under one roof.</p>
			</div>
		  </div>
		</div>
		<div class="chat_list">
		  <div class="chat_people">
			<div class="chat_img"> <img src="https://ptetutorials.com/images/user-profile.png" alt="sunil"> </div>
			<div class="chat_ib">
			  <h5>Sunil Rajput <span class="chat_date">Dec 25</span></h5>
			  <p>Test, which is a new approach to have all solutions 
				astrology under one roof.</p>
			</div>
		  </div>
		</div>
		<div class="chat_list">
		  <div class="chat_people">
			<div class="chat_img"> <img src="https://ptetutorials.com/images/user-profile.png" alt="sunil"> </div>
			<div class="chat_ib">
			  <h5>Sunil Rajput <span class="chat_date">Dec 25</span></h5>
			  <p>Test, which is a new approach to have all solutions 
				astrology under one roof.</p>
			</div>
		  </div>
		</div>
		<div class="chat_list">
		  <div class="chat_people">
			<div class="chat_img"> <img src="https://ptetutorials.com/images/user-profile.png" alt="sunil"> </div>
			<div class="chat_ib">
			  <h5>Sunil Rajput <span class="chat_date">Dec 25</span></h5>
			  <p>Test, which is a new approach to have all solutions 
				astrology under one roof.</p>
			</div>
		  </div>
		</div>
		<div class="chat_list">
		  <div class="chat_people">
			<div class="chat_img"> <img src="https://ptetutorials.com/images/user-profile.png" alt="sunil"> </div>
			<div class="chat_ib">
			  <h5>Sunil Rajput <span class="chat_date">Dec 25</span></h5>
			  <p>Test, which is a new approach to have all solutions 
				astrology under one roof.</p>
			</div>
		  </div>
		</div>
	  </div>
	</div>
	<div class="mesgs">
	  <div class="msg_history">
		<div class="incoming_msg">
		  <div class="incoming_msg_img"> <img src="https://ptetutorials.com/images/user-profile.png" alt="sunil"> </div>
		  <div class="received_msg">
			<div class="received_withd_msg">
			  <p>Test which is a new approach to have all
				solutions</p>
			  <span class="time_date"> 11:01 AM    |    June 9</span></div>
		  </div>
		</div>
		<div class="outgoing_msg">
		  <div class="sent_msg">
			<p>Test which is a new approach to have all
			  solutions</p>
			<span class="time_date"> 11:01 AM    |    June 9</span> </div>
		</div>
		<div class="incoming_msg">
		  <div class="incoming_msg_img"> <img src="https://ptetutorials.com/images/user-profile.png" alt="sunil"> </div>
		  <div class="received_msg">
			<div class="received_withd_msg">
			  <p>Test, which is a new approach to have</p>
			  <span class="time_date"> 11:01 AM    |    Yesterday</span></div>
		  </div>
		</div>
		<div class="outgoing_msg">
		  <div class="sent_msg">
			<p>Apollo University, Delhi, India Test</p>
			<span class="time_date"> 11:01 AM    |    Today</span> </div>
		</div>
		<div class="incoming_msg">
		  <div class="incoming_msg_img"> <img src="https://ptetutorials.com/images/user-profile.png" alt="sunil"> </div>
		  <div class="received_msg">
			<div class="received_withd_msg">
			  <p>We work directly with our designers and suppliers,
				and sell direct to you, which means quality, exclusive
				products, at a price anyone can afford.</p>
			  <span class="time_date"> 11:01 AM    |    Today</span></div>
		  </div>
		</div>
	  </div>
	  <div class="type_msg">
		<div class="input_msg_write">
		  <input type="text" class="write_msg" placeholder="Type a message" />
		  <button class="msg_send_btn" type="button"><i class="fa fa-paper-plane" aria-hidden="true"></i></button>
		</div>
	  </div>
	</div>
  </div>
</div>


  	<!-- Start Footer Area -->
    <jsp:include page="/WEB-INF/html/Footer.jsp"></jsp:include>
    <!-- End Footer Area -->
    
     <!-- Start Foot Area -->
    <jsp:include page="/WEB-INF/html/Foot.jsp"></jsp:include>
    <!-- End Foot Area -->


</body>
</html>





 