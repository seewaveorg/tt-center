<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<%@ page import="com.ngs.service.SysUser"%>
<%@ page import="java.io.*,java.util.*"%>
<%@ page import="javax.servlet.*,java.text.*"%>
<%
	SysUser sys = (SysUser) request.getSession().getAttribute("user");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description"
	content="A fully featured admin theme which can be used to build CRM, CMS, etc.">
<meta name="author" content="Coderthemes">

<link
	href="<c:url value='/resources/ttsystem/assets/images/favicon.ico' />"
	rel="shortcut icon">

<title>TT-center</title>

<!--Morris Chart CSS -->
<link
	href="<c:url value='/resources/ttsystem/assets/plugins/morris/morris.css'/>"
	rel="stylesheet" media="screen">

<!-- App css -->
<link
	href="<c:url value='/resources/ttsystem/assets/css/bootstrap.min.css'/>"
	rel="stylesheet" media="screen">
<link href="<c:url value='/resources/ttsystem/assets/css/core.css'/>"
	rel="stylesheet" media="screen">
<link
	href="<c:url value='/resources/ttsystem/assets/css/components.css'/>"
	rel="stylesheet" media="screen">
<link href="<c:url value='/resources/ttsystem/assets/css/icons.css'/>"
	rel="stylesheet" media="screen">
<link href="<c:url value='/resources/ttsystem/assets/css/pages.css'/>"
	rel="stylesheet" media="screen">
<link href="<c:url value='/resources/ttsystem/assets/css/menu.css'/>"
	rel="stylesheet" media="screen">

<!-- dark theme
<link href="<c:url value='/resources/ttsystem/assets/css/menu_dark.css'/>"
	rel="stylesheet" media="screen">
 -->

<link
	href="<c:url value='/resources/ttsystem/assets/css/responsive.css'/>"
	rel="stylesheet" media="screen">


<!-- DataTables -->
<link
	href="<c:url value='/resources/ttSystemTemplate/dashboard/assets/plugins/datatables/jquery.dataTables.min.css' />"
	rel="stylesheet" media="screen">
<link
	href="<c:url value='/resources/ttSystemTemplate/dashboard/assets/plugins/datatables/buttons.bootstrap.min.css' />"
	rel="stylesheet" media="screen">
<link
	href="<c:url value='/resources/ttSystemTemplate/dashboard/assets/plugins/datatables/fixedHeader.bootstrap.min.css' />"
	rel="stylesheet" media="screen">
<link
	href="<c:url value='/resources/ttSystemTemplate/dashboard/assets/plugins/datatables/responsive.bootstrap.min.css' />"
	rel="stylesheet" media="screen">
<link
	href="<c:url value='/resources/ttSystemTemplate/dashboard/assets/plugins/datatables/scroller.bootstrap.min.css' />"
	rel="stylesheet" media="screen">

<!-- HTML5 Shiv and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->

<link
	href="<c:url value='/resources/ttSystemTemplate/dashboard/assets/plugins/bootstrap-sweetalert/sweet-alert.css' />"
	rel="stylesheet" media="screen">


<!-- Notification css (Toastr) -->
<link
	href="<c:url value='/resources/ttsystem/assets/plugins/toastr/toastr.min.css'/>"
	rel="stylesheet" media="screen">


<script
	src="<c:url value='/resources/ttsystem/assets/js/modernizr.min.js' />"></script>

</head>


<body class="fixed-left">

	<!-- Begin page -->
	<div id="wrapper">

		<!-- Top Bar Start -->
		<div class="topbar">

			<!-- LOGO -->
			<div class="topbar-left">
				<a href="welcome.do" class="logo"><span>TT-<span>System</span></span><i
					class="zmdi zmdi-layers"></i></a>
			</div>

			<!-- Button mobile view to collapse sidebar menu -->
			<div class="navbar navbar-default" role="navigation">
				<div class="container">

					<!-- Page title -->
					<ul class="nav navbar-nav navbar-left">
						<li>
							<button class="button-menu-mobile open-left">
								<i class="zmdi zmdi-menu"></i>
							</button>
						</li>
						<li>
							<h4 id="headname" class="page-title">Dashboard</h4>
						</li>
					</ul>


				</div>
				<!-- end container -->
			</div>
			<!-- end navbar -->
		</div>
		<!-- Top Bar End -->


		<!-- ========== Left Sidebar Start ========== -->
		<div class="left side-menu">
			<div class="sidebar-inner slimscrollleft">
				<!-- User -->
				<div class="user-box">
					<div class="user-img">
						<img
							src="<c:url value='/resources/ttsystem/assets/images/users/avatar-1.jpg' />"
							alt="user-img" title="Mat Helme"
							class="img-circle img-thumbnail img-responsive">

					</div>
					<h5>
						<a id="compnyname" href="welcome.do"><%=sys.getName()%></a>

					</h5>
					<ul class="list-inline">
						<li><a href="#"> <i class="zmdi zmdi-settings"></i></a></li>
						<li><a href="logoutfunct.do" class="text-custom"> <i
								class="zmdi zmdi-power"></i></a></li>
					</ul>
				</div>
				<!-- End User -->





				<!--- Sidemenu -->
				<div id="sidebar-menu">
					<ul id='menu'>


					</ul>
					<div class="clearfix"></div>
				</div>
				<!-- Sidebar -->
				<div class="clearfix"></div>

			</div>
		</div>
		<!-- Left Sidebar End -->



		<!-- ============================================================== -->
		<!-- Start right Content here -->
		<!-- ============================================================== -->
		<div class="content-page">
			<!-- Start content -->
			<div class="content">
				<div class="container" id="viewpagecont"></div>
				<!-- container -->

			</div>
			<!-- content -->

			<footer class="footer text-right"> 2017 © FNH (Pvt) Ltd. </footer>

		</div>


		<!-- ============================================================== -->
		<!-- End Right content here -->
		<!-- ============================================================== -->

	</div>
	<!-- END wrapper -->



	<script>
		var resizefunc = [];
	</script>

	<!-- jQuery  -->
	<script
		src="<c:url value='/resources/ttsystem/assets/js/jquery.min.js' />"></script>
	<script
		src="<c:url value='/resources/ttsystem/assets/js/bootstrap.min.js' />"></script>
	<script src="<c:url value='/resources/ttsystem/assets/js/detect.js' />"></script>
	<script
		src="<c:url value='/resources/ttsystem/assets/js/fastclick.js' />"></script>
	<script
		src="<c:url value='/resources/ttsystem/assets/js/jquery.slimscroll.js' />"></script>
	<script
		src="<c:url value='/resources/ttsystem/assets/js/jquery.blockUI.js' />"></script>
	<script src="<c:url value='/resources/ttsystem/assets/js/waves.js' />"></script>
	<script
		src="<c:url value='/resources/ttsystem/assets/js/jquery.nicescroll.js' />"></script>
	<script
		src="<c:url value='/resources/ttsystem/assets/js/jquery.scrollTo.min.js' />"></script>

	<!-- KNOB JS -->
	<!--[if IE]>
        <script type="text/javascript" src="assets/plugins/jquery-knob/excanvas.js"></script>
        <![endif]-->


	<script>
		var resizefunc = [];
	</script>

	<!-- Datatables-->


	<script
		src="<c:url value='/resources/ttSystemTemplate/dashboard/assets/plugins/datatables/jquery.dataTables.min.js' />"></script>
	<script
		src="<c:url value='/resources/ttSystemTemplate/dashboard/assets/plugins/datatables/dataTables.bootstrap.js' />"></script>
	<script
		src="<c:url value='/resources/ttSystemTemplate/dashboard/assets/plugins/datatables/dataTables.buttons.min.js' />"></script>
	<script
		src="<c:url value='/resources/ttSystemTemplate/dashboard/assets/plugins/datatables/buttons.bootstrap.min.js' />"></script>
	<script
		src="<c:url value='/resources/ttSystemTemplate/dashboard/assets/plugins/datatables/jszip.min.js' />"></script>
	<script
		src="<c:url value='/resources/ttSystemTemplate/dashboard/assets/plugins/datatables/pdfmake.min.js' />"></script>
	<script
		src="<c:url value='/resources/ttSystemTemplate/dashboard/assets/plugins/datatables/vfs_fonts.js' />"></script>
	<script
		src="<c:url value='/resources/ttSystemTemplate/dashboard/assets/plugins/datatables/buttons.html5.min.js' />"></script>
	<script
		src="<c:url value='/resources/ttSystemTemplate/dashboard/assets/plugins/datatables/buttons.print.min.js' />"></script>
	<script
		src="<c:url value='/resources/ttSystemTemplate/dashboard/assets/plugins/datatables/dataTables.fixedHeader.min.js' />"></script>
	<script
		src="<c:url value='/resources/ttSystemTemplate/dashboard/assets/plugins/datatables/dataTables.keyTable.min.js' />"></script>
	<script
		src="<c:url value='/resources/ttSystemTemplate/dashboard/assets/plugins/datatables/dataTables.responsive.min.js' />"></script>
	<script
		src="<c:url value='/resources/ttSystemTemplate/dashboard/assets/plugins/datatables/responsive.bootstrap.min.js' />"></script>
	<script
		src="<c:url value='/resources/ttSystemTemplate/dashboard/assets/plugins/datatables/dataTables.scroller.min.js' />"></script>


	<!-- Datatable init js -->
	<script
		src="<c:url value='/resources/ttSystemTemplate/dashboard/assets/pages/datatables.init.js' />"></script>

	<!-- Sweet Alert js -->
	<script
		src="<c:url value='/resources/ttsystem/assets/plugins/bootstrap-sweetalert/sweet-alert.min.js' />"></script>
	<script
		src="<c:url value='/resources/ttsystem/assets/pages/jquery.sweet-alert.init.js' />"></script>



	<script
		src="<c:url value='/resources/ttsystem/assets/plugins/jquery-knob/jquery.knob.js' />"></script>

	<!-- Toastr js -->
	<script
		src="<c:url value='/resources/ttSystemTemplate/dashboard/assets/plugins/toastr/toastr.min.js' />"></script>



	<!-- App js -->
	<script
		src="<c:url value='/resources/ttsystem/assets/js/jquery.core.js' />"></script>
	<script
		src="<c:url value='/resources/ttsystem/assets/js/jquery.app.js' />"></script>

	<script type="text/javascript">
		$(document).ready(function() {

			window.open('showDboard.do');
			$.ajax({
				type : "get",
				url : "showSideBar.do",
				success : function(msg) {

					loadmenu(msg);

					//compnyname
					//$('#menu').html(msg);
				}
			});

			$.ajax({
				type : "get",
				url : "showAlarmTable.do",
				success : function(msg) {
					$('#viewpagecont').html(msg);
				}
			});

		});

		function test(id) {
			var value = $('#' + id).attr('value');
			var name = $('#' + id).attr('name');
			$.ajax({

				type : "get",
				url : value,
				success : function(msg) {
					$('#viewpagecont').html(msg);
					$('#headname').html(name);
				}
			});
		}
		function dboard(id) {
			window.open('showDboard.do');
		}
		function logout() {
		}

		function loadmenu(msg) {

			var stringhtml = "";
			var json = JSON.parse(msg)
			stringhtml = "<li href='welcome.do' id='pagename' class='text-muted menu-title'>Navigation</li>"
			stringhtml += "<li><a onclick='javascript:dboard(1);' class='waves-effect active'><i class='zmdi zmdi-view-dashboard'></i> <span> Main Dashboard</span> </a></li>";

			for (i = 0; i < json.length; i++) {
				stringhtml += "<li><a onclick='javascript:test("
						+ json[i]["actid"]
						+ ");' id='"
						+ json[i]["actid"]
						+ "' name='"
						+ json[i]["actname"]
						+ "' value='"
						+ json[i]["url"]
						+ "' class='waves-effect'><i class='zmdi zmdi-view-dashboard'></i> <span>"
						+ json[i]["actname"] + "</span> </a></li>";
			}

			$('#menu').html(stringhtml);

		}
		//notification msg
		toastr.options = {
			"closeButton" : false,
			"debug" : false,
			"newestOnTop" : false,
			"progressBar" : false,
			"positionClass" : "toast-top-right",
			"preventDuplicates" : false,
			"onclick" : null,
			"showDuration" : "300",
			"hideDuration" : "1000",
			"timeOut" : "5000",
			"extendedTimeOut" : "1000",
			"hideEasing" : "linear",
			"showMethod" : "fadeIn",
			"hideMethod" : "fadeOut"
		}
	</script>
</body>
</html>
