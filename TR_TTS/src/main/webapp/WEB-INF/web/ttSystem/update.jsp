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
<title>NextGenMed</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />

<link rel="icon" href="favicon.ico" type="image/x-icon" />
<!-- END META SECTION -->

<!-- CSS INCLUDE -->

<link
	href="<c:url value='/resources/ttSystemTemplate/css/theme-default.css' />"
	rel="stylesheet" media="screen">
<!-- EOF CSS INCLUDE -->
</head>
<body>
	<!-- START PAGE CONTAINER -->
	<div class="page-container">

		<!-- START PAGE SIDEBAR -->
		<div class="page-sidebar">
			<!-- START X-NAVIGATION -->
			<ul class="x-navigation">
				<li class="xn-logo"><a href="index.html">NextGenMed</a> <a
					href="#" class="x-navigation-control"></a></li>
				<li class="xn-title">Navigation</li>
				<li class="active"><a href="dashboard.do"><span
						class="fa fa-desktop"></span> <span class="xn-text">Dashboard</span></a>
				</li>
				<li class="xn-openable">
					<!-- <a href=""><span class="fa fa-pencil"></span> <span class="xn-text">Forms</span></a> -->
					<a href="tticket.do"><span class="fa fa-square-o"></span>Trouble
						Ticket</a> <a href="troubleTicketUpdate.do"><span
						class="fa fa-pencil-square-o"></span>Trouble Ticket Update</a> <a
					href="alarm.do"><span class="fa fa-warning"></span>Alarm</a> <a
					href="showAlarmTable.do"><span class="fa fa-warning"></span>Alarm
						Table</a> <a href="getTt.do"><span class="fa fa-warning"></span>Trouble
						Ticket Table</a>

				</li>





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
			</ul>
			<!-- END X-NAVIGATION VERTICAL -->



			<div class="page-title">
				<h2>
					<span class="fa fa-arrow-circle-o-left"></span> Update Trouble
					Ticket
				</h2>
			</div>

			<!-- PAGE CONTENT WRAPPER -->
			<div class="page-content-wrap">

				<div class="row">
					<div class="col-md-12">

						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">Panel Title</h3>
							</div>
							<form:form method="post" modelAttribute="UpdateTt" action="saveTroubleTicketUpdate.do" class="form-horizontal">
								<form:hidden path="id" id="id" />
								<form:hidden path="tt.id" id="ttid"/>
								<div class="form-group">
									<label class="col-md-3 col-xs-12 control-label">Comment:</label>
									<div class="col-md-6 col-xs-12">
										<div class="input-group">
											<span class="input-group-addon"></span>
											<form:textarea path="comment" class="form-control" rows="5"	placeholder="Comment"></form:textarea>
										</div>
									</div>
								</div>

								<div class="form-group">
                                        <label class="col-md-3 col-xs-12 control-label">Owner :</label>
                                        <div class="col-md-6 col-xs-12">                                            
                                             <div class="input-group">
                                                <span class="input-group-addon"></span>
													<form:select path="User.id" id="user" items="${userList}" class="form-control"></form:select>                                           	
                                            </div>    
                                            
                                           
                                        </div>
                                    </div>


								<div class="form-group">
									<label class="col-md-3 col-xs-12 control-label">Status:</label>
									<div class="col-md-6 col-xs-12">

										<form:select path="status" class="form-control select">
											<option value="1">Initiate</option>
											<option value="2">Transfered</option>
											<option value="3">pending</option>
											<option value="4">can't clear</option>
											<option value="5">Done</option>

										</form:select>
									</div>
								</div>

								<div class="form-group">
									<label class="col-md-3 col-xs-12 control-label">Info</label>
									<div class="col-md-6 col-xs-12">
										<div class="input-group">
											<span class="input-group-addon"></span>
											<form:textarea path="info" class="form-control" rows="5"
												placeholder="Info"></form:textarea>

										</div>
									</div>

								</div>
								<div class="form-group">

									<div class="panel-footer">
										<!-- <button class="btn btn-default">Clear Form</button> -->
										<button type="submit" class="btn btn-primary pull-right">Submit</button>
									</div>

								</div>
							</form:form>
						</div>

						<div class="panel-body"></div>
					</div>
				</div>

			</div>
		</div>

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
						<a href="pages-login.html" class="btn btn-success btn-lg">Yes</a>
						<button class="btn btn-default btn-lg mb-control-close">No</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- END MESSAGE BOX-->

	<!-- START PRELOADS -->
	<audio id="audio-alert" src="audio/alert.mp3" preload="auto"></audio>
	<audio id="audio-fail" src="audio/fail.mp3" preload="auto"></audio>
	<!-- END PRELOADS -->

	<!-- START SCRIPTS -->
	<!-- START PLUGINS -->
		<script
		src="<c:url value='/resources/ttSystemTemplate/js/plugins/jquery/jquery.min.js' />"></script>
	<script
		src="<c:url value='/resources/ttSystemTemplate/js/plugins/jquery/jquery-ui.min.js' />"></script>
	<script
		src="<c:url value='/resources/ttSystemTemplate/js/plugins/bootstrap/bootstrap.min.js' />"></script>

	<!-- END PLUGINS -->

	<!-- THIS PAGE PLUGINS -->
	
	<script src="<c:url value='/resources/ttSystemTemplate/js/plugins/morris/raphael-min.js' />"></script>
   		<script src="<c:url value='/resources/ttSystemTemplate/js/plugins/morris/morris.min.js' />"></script>
   		<script src="<c:url value='/resources/ttSystemTemplate/js/plugins/rickshaw/d3.v3.js' />"></script>
   		<script src="<c:url value='/resources/ttSystemTemplate/js/plugins/rickshaw/rickshaw.min.js' />"></script>           
   		<script src="<c:url value='/resources/ttSystemTemplate/js/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js' />"></script>
   		<script src="<c:url value='/resources/ttSystemTemplate/js/plugins/jvectormap/jquery-jvectormap-world-mill-en.js' />"></script>
   		<script src="<c:url value='/resources/ttSystemTemplate/js/plugins/bootstrap/bootstrap-datepicker.js' />"></script>
		<script src="<c:url value='/resources/ttSystemTemplate/js/plugins/owl/owl.carousel.min.js' />"></script>
		
		<script src="<c:url value='/resources/ttSystemTemplate/js/plugins/moment.min.js' />"></script>
		<script src="<c:url value='/resources/ttSystemTemplate/js/plugins/daterangepicker/daterangepicker.js' />"></script>
		   	
                      
        <script src="<c:url value='/resources/ttSystemTemplate/js/plugins/icheck/icheck.min.js' />"></script>
        <script src="<c:url value='/resources/ttSystemTemplate/js/plugins/jquery/jquery-ui.min.js' />"></script>
        <script src="<c:url value='/resources/ttSystemTemplate/js/plugins/bootstrap/bootstrap.min.js' />"></script>

	<!-- END PAGE PLUGINS -->

	<!-- START TEMPLATE -->
	<script
		src="<c:url value='/resources/ttSystemTemplate/js/plugins.js' />"></script>
	<script
		src="<c:url value='/resources/ttSystemTemplate/js/actions.js' />"></script>
	<!-- END TEMPLATE -->
	<!-- END SCRIPTS -->
</body>
</html>






