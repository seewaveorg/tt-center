<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<%@ page import="com.ngs.service.SysUser"%>
<%@ page import="java.io.*,java.util.*"%>
<%@ page import="javax.servlet.*,java.text.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description"
	content="A fully featured admin theme which can be used to build CRM, CMS, etc.">
<meta name="author" content="Coderthemes">

<!-- App Favicon -->
<link
	href="<c:url value='/resources/ttSystemTemplate/dashboard/assets/images/favicon.ico' />"
	rel="shortcut icon">
<!-- App title -->
<title>TT System | FNH</title>

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

<!-- App CSS -->
<link
	href="<c:url value='/resources/ttSystemTemplate/dashboard/assets/css/bootstrap.min.css' />"
	rel="stylesheet" media="screen">
<link
	href="<c:url value='/resources/ttSystemTemplate/dashboard/assets/css/core.css' />"
	rel="stylesheet" media="screen">
<link
	href="<c:url value='/resources/ttSystemTemplate/dashboard/assets/css/components.css' />"
	rel="stylesheet" media="screen">
<link
	href="<c:url value='/resources/ttSystemTemplate/dashboard/assets/css/icons.css' />"
	rel="stylesheet" media="screen">
<link
	href="<c:url value='/resources/ttSystemTemplate/dashboard/assets/css/pages.css' />"
	rel="stylesheet" media="screen">
<link
	href="<c:url value='/resources/ttSystemTemplate/dashboard/assets/css/menu.css' />"
	rel="stylesheet" media="screen">
<link
	href="<c:url value='/resources/ttSystemTemplate/dashboard/assets/css/responsive.css' />"
	rel="stylesheet" media="screen">

<!-- HTML5 Shiv and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->

<script
	src="<c:url value='/resources/ttSystemTemplate/dashboard/assets/js/modernizr.min.js' />"></script>

</head>


<body class="fixed-left">

	<!-- Begin page -->
	<div id="wrapper">

		<!-- ============================================================== -->
		<!-- Start right Content here -->
		<!-- ============================================================== -->
		<!-- Start content -->
		<div class="content">
			<div class="container">
				<br>

				<div class="row">
					<div class="col-sm-12">
						<div class="card-box table-responsive">
							<div class="dropdown pull-right">
								<a href="#" class="dropdown-toggle card-drop"
									data-toggle="dropdown" aria-expanded="false"> <i
									class="zmdi zmdi-more-vert"></i>
								</a>
								<ul class="dropdown-menu" role="menu">
									<li><a href="#">Action</a></li>
									<li><a href="#">Another action</a></li>
									<li><a href="#">Something else here</a></li>
									<li class="divider"></li>
									<li><a href="#">Separated link</a></li>
								</ul>
							</div>

							<h4 id="sysname" class="header-title m-t-0 m-b-30">System Name</h4>

							<table id="datatable-buttons"
								class="table table-striped table-bordered">
								<thead>
									<tr>
										<th>No</th>
										<th>Event</th>
										<th>Type</th>
										<th>Originated Time</th>
										<th>Cleared Time</th>
										<th>Severity</th>
										<th id="status">Status</th>
									</tr>
								</thead>
								<tbody id="tblebody">
									
								</tbody>
							</table>
						</div>
					</div>
					<!-- end col -->
				</div>
				<!-- end row -->
			</div>
			<!-- container -->
		</div>
		<!-- content -->
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
		src="<c:url value='/resources/ttSystemTemplate/dashboard/assets/js/jquery.min.js' />"></script>
	<script
		src="<c:url value='/resources/ttSystemTemplate/dashboard/assets/js/bootstrap.min.js' />"></script>
	<script
		src="<c:url value='/resources/ttSystemTemplate/dashboard/assets/js/detect.js' />"></script>
	<script
		src="<c:url value='/resources/ttSystemTemplate/dashboard/assets/js/fastclick.js' />"></script>
	<script
		src="<c:url value='/resources/ttSystemTemplate/dashboard/assets/js/jquery.slimscroll.js' />"></script>
	<script
		src="<c:url value='/resources/ttSystemTemplate/dashboard/assets/js/jquery.blockUI.js' />"></script>
	<script
		src="<c:url value='/resources/ttSystemTemplate/dashboard/assets/js/waves.js' />"></script>
	<script
		src="<c:url value='/resources/ttSystemTemplate/dashboard/assets/js/jquery.nicescroll.js' />"></script>
	<script
		src="<c:url value='/resources/ttSystemTemplate/dashboard/assets/js/jquery.scrollTo.min.js' />"></script>


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


	<!-- App js -->
	<script
		src="<c:url value='/resources/ttSystemTemplate/dashboard/assets/js/jquery.core.js' />"></script>
	<script
		src="<c:url value='/resources/ttSystemTemplate/dashboard/assets/js/jquery.app.js' />"></script>



	<script type="text/javascript">
		var id;
		$(document).ready(function() {
			$('#sysname').html(localStorage.getItem("sysname"));
			id = localStorage.getItem("systemid");
			


			$.ajax({
				contentType : "application/json",
				type : "get",
				url : "loadalarmforsysid.do",
				data: "id="+id,
				success : function(msg) {
					$('#tblebody').html(msg);
					tmptble();
				}
			});

			
			
		});
		


		function tmptble(){
			$('#datatable').dataTable();
			$('#datatable-keytable').DataTable({
				keys : true
			});
			$('#datatable-responsive').DataTable();
			$('#datatable-scroller').DataTable({
				ajax : "assets/plugins/datatables/json/scroller-demo.json",
				deferRender : true,
				scrollY : 380,
				scrollCollapse : true,
				scroller : true
			});
			var table = $('#datatable-fixed-header').DataTable({
				fixedHeader : true
			});
			TableManageButtons.init();
			$( "#status" ).click();
			$( "#status" ).click();
			
			}
	</script>

</body>
</html>
