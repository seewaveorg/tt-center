<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>



<!DOCTYPE html>
<html lang="en" class="body-full-height">
<head>

<title>TTS|FNH</title>

<!-- Meta-Tags -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="keywords"
	content="Existing Login Form Widget Responsive, Login Form Web Template, Flat Pricing Tables, Flat Drop-Downs, Sign-Up Web Templates, Flat Web Templates, Login Sign-up Responsive Web Template, Smartphone Compatible Web Template, Free Web Designs for Nokia, Samsung, LG, Sony Ericsson, Motorola Web Design">
<script type="application/x-javascript">
	
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 

</script>
<!-- //Meta-Tags -->


<link href="<c:url value='/resources/login/css/popuo-box.css' />"
	type="text/css" id="theme" rel="stylesheet" media="all">

<link href="<c:url value='/resources/login/css/style.css' />"
	type="text/css" id="theme" rel="stylesheet" media="all">

<link href="//fonts.googleapis.com/css?family=Quicksand:300,400,500,700"
	rel="stylesheet">

<!-- EOF CSS INCLUDE -->
</head>
<body>

	<h1></h1>

	<div class="w3layoutscontaineragileits">
		<h2>
			<strong>Welcome</strong>, Please login
		</h2>
		<form class="form-horizontal" id="target" name="loginForm"
			action="<c:url value='/j_spring_security_check' />" method="POST">

			<input type="text" Name="username" id="username"
				autofocus="autofocus" placeholder="USERNAME" required=""> <input
				type="password" Name="password" id="password" placeholder="PASSWORD"
				required="">

			<ul class="agileinfotickwthree">
				<li><input type="checkbox" id="brand1" value=""> <label
					for="brand1"><span></span>Remember me</label> <a href="#">Forgot
						password?</a></li>
			</ul>
			<div class="aitssendbuttonw3ls">
				<input type="submit" value="LOGIN"> <input type="hidden"
					name="${_csrf.parameterName}" value="${_csrf.token}" />
				<div class="clear"></div>
			</div>
		</form>
		<br>
		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg" style="color: #c62828">${msg}</div>
		</c:if>

	</div>

	<div class="w3footeragile">
		<p>
			&copy; 2017 FNH. All Rights Reserved | Design by <a
				href="http://futurenetwork.lk/" target="_blank">FNH</a>
		</p>
	</div>

	<script src="<c:url value='/resources/login/js/jquery-2.1.4.min.js' />"></script>

	<!-- pop-up-box-js-file -->
	<script
		src="<c:url value='/resources/login/js/jquery.magnific-popup.js' />"></script>
	<!--//pop-up-box-js-file -->


</body>
</html>