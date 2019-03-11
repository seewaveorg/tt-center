<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<%@ page import="com.ngs.service.SysUser"%>
<%@ page import="java.io.*,java.util.*"%>
<%@ page import="javax.servlet.*,java.text.*"%>

<!DOCTYPE html>
<html lang="en">
<head>
<!-- META SECTION -->
<title>TT-system | Welcome</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />

<link rel="icon" href="favicon.ico" type="image/x-icon" />
<!-- END META SECTION -->

<!-- CSS INCLUDE -->
<link
	href="<c:url value='/resources/ttSystemTemplate/css/theme-default.css' />"
	rel="stylesheet" media="screen">



</head>
<body>
	<!-- START PAGE CONTAINER -->
	<div class="page-container">

		<!-- START PAGE SIDEBAR -->
		<div class="page-sidebar">
			<!-- START X-NAVIGATION -->
			<ul class="x-navigation">
				<li class="xn-logo"><a href="welcome.do">TT-System</a> <a
					href="#" class="x-navigation-control"></a></li>

				<li class="active"><a href="welcome.do"><span
						class="fa fa-desktop"></span> <span class="xn-text">Dashboard</span></a>
				</li>
				<li id='menu'>
			</ul>
			<!-- END X-NAVIGATION -->
		</div>
		<!-- END PAGE SIDEBAR -->

		<!-- PAGE CONTENT -->
		<div class="page-content">

			<!-- START X-NAVIGATION VERTICAL -->
			<ul class="x-navigation x-navigation-horizontal x-navigation-panel">
				<!-- TOGGLE NAVIGATION -->
				<li class="xn-icon-button"><a href="#"
					class="x-navigation-minimize"><span class="fa fa-dedent"></span></a>
				</li>
				<!-- END TOGGLE NAVIGATION -->
				<!-- SEARCH -->
				<li class="xn-search">
					<form role="form">
						<input type="text" name="search" placeholder="Search..." />
					</form>
				</li>
				<!-- END SEARCH -->
				<!-- SIGN OUT -->
				<li class="xn-icon-button pull-right"><a href="#"
					class="mb-control" data-box="#mb-signout"><span
						class="fa fa-sign-out"></span></a></li>
				<!-- END SIGN OUT -->


			</ul>
			<!-- END X-NAVIGATION VERTICAL -->

			<!-- START BREADCRUMB -->
			<ul class="breadcrumb">
				<li><a href="#">Home</a></li>
				
				
				
			</ul>
			<!-- END BREADCRUMB -->

			<!-- PAGE CONTENT WRAPPER -->
			<div class="page-content-wrap">

				<!-- START WIDGETS -->
				<div class="row">

					<!-- page boady -->
					<div id="viewpagecont"></div>
					<!-- end page body -->

				</div>
				<!-- END WIDGETS -->
			</div>
			<!-- END PAGE CONTENT WRAPPER -->
		</div>
		<!-- END PAGE CONTENT -->
	</div>
	<!-- END PAGE CONTAINER -->

	<!-- MESSAGE BOX-->
	<div class="message-box animated fadeIn" data-sound="alert"
		id="mb-signout">
		<div class="mb-container">
			<div class="mb-middle">
				<div class="mb-title">
					<span class="fa fa-sign-out"></span> Log <strong>Out</strong> ?
				</div>
				<div class="mb-content">
					<p>Are you sure you want to log out?</p>
					<p>Press No if youwant to continue work. Press Yes to logout
						current user.</p>
				</div>
				<div class="mb-footer">
					<div class="pull-right">
						<a href="logout.do" class="btn btn-success btn-lg">Yes</a>
						<button class="btn btn-default btn-lg mb-control-close">No</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- END MESSAGE BOX-->

	<!-- START SCRIPTS -->
	<!-- START PLUGINS -->

	<!-- START SCRIPTS -->
	<!-- START PLUGINS -->
	<script
		src="<c:url value='/resources/ttSystemTemplate/js/plugins/jquery/jquery.min.js' />"></script>
	<script
		src="<c:url value='/resources/ttSystemTemplate/js/plugins/jquery/jquery-ui.min.js' />"></script>
	<script
		src="<c:url value='/resources/ttSystemTemplate/js/plugins/bootstrap/bootstrap.min.js' />"></script>
	<!-- END PLUGINS -->

	<!-- START THIS PAGE PLUGINS-->
	<script
		src="<c:url value='/resources/ttSystemTemplate/js/plugins/icheck/icheck.min.js' />"></script>
	<script
		src="<c:url value='/resources/ttSystemTemplate/js/plugins/mcustomscrollbar/jquery.mCustomScrollbar.min.js' />"></script>
	<script
		src="<c:url value='/resources/ttSystemTemplate/js/plugins/scrolltotop/scrolltopcontrol.js' />"></script>

	<script
		src="<c:url value='/resources/ttSystemTemplate/js/plugins/morris/raphael-min.js' />"></script>
	<script
		src="<c:url value='/resources/ttSystemTemplate/js/plugins/morris/morris.min.js' />"></script>
	<script
		src="<c:url value='/resources/ttSystemTemplate/js/plugins/rickshaw/d3.v3.js' />"></script>
	<script
		src="<c:url value='/resources/ttSystemTemplate/js/plugins/rickshaw/rickshaw.min.js' />"></script>
	<script
		src="<c:url value='/resources/ttSystemTemplate/js/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js' />"></script>
	<script
		src="<c:url value='/resources/ttSystemTemplate/js/plugins/jvectormap/jquery-jvectormap-world-mill-en.js' />"></script>
	<script
		src="<c:url value='/resources/ttSystemTemplate/js/plugins/bootstrap/bootstrap-datepicker.js' />"></script>
	<script
		src="<c:url value='/resources/ttSystemTemplate/js/plugins/owl/owl.carousel.min.js' />"></script>

	<script
		src="<c:url value='/resources/ttSystemTemplate/js/plugins/moment.min.js' />"></script>
	<script
		src="<c:url value='/resources/ttSystemTemplate/js/plugins/daterangepicker/daterangepicker.js' />"></script>
	<!-- END THIS PAGE PLUGINS-->

	<!-- START TEMPLATE -->
	<script
		src="<c:url value='/resources/ttSystemTemplate/js/settings.js' />"></script>

	<script
		src="<c:url value='/resources/ttSystemTemplate/js/plugins.js' />"></script>
	<script
		src="<c:url value='/resources/ttSystemTemplate/js/actions.js' />"></script>


	<!-- END TEMPLATE -->

	<script type="text/javascript">
		$(document).ready(function() {

			window.open('showDboard.do');
			$.ajax({
				type : "get",
				url : "showSideBar.do",
				success : function(msg) {
					$('#menu').html(msg);
				}
			});

			$.ajax({
				type : "get",
				url : "dashboard.do",
				success : function(msg) {
					$('#viewpagecont').html(msg);
				}
			});

		});

		function test(id) {
			var value = $('#' + id).attr('value');
			$.ajax({

				type : "get",
				url : value,
				success : function(msg) {
					$('#viewpagecont').html(msg);

				}
			});
		}
		function dboard(id) {
					window.open('showDboard.do');
		}
		function logout() {
			alert("test");
		}
	</script>



</body>
</html>