<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<%@ page import="com.ngs.service.SysUser"%>
<%@ page import="java.io.*,java.util.*"%>
<%@ page import="javax.servlet.*,java.text.*"%>
<html>
<head>

<!-- App title -->
<title>TT System | FNH</title>


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

<script
	src="<c:url value='/resources/ttSystemTemplate/dashboard/assets/js/modernizr.min.js' />"></script>

</head>


<body class="fixed-left">


	<div class="row">
		<div class="col-sm-12">
			<div class="card-box">
				<div class="row">
					<div class="col-lg-10">
						<form:form id="updateForm" action="saveUpTt.do" role="form"
							method="post" modelAttribute="UpdateTt" class="form-horizontal">

							<form:hidden path="id" id="id" />
							<form:hidden path="comment" id="comment" />
							<form:hidden path="systems.id" id="system" />
							<form:hidden path="company.id" id="company" />
							<form:hidden path="department.id" id="department" />

							<div class="form-group">
								<label class="col-md-2 control-label">Comment</label>
								<div class="col-md-10">
									<textarea placeholder="comment" class="form-control" rows="5"></textarea>
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-2 control-label">Owner</label>
								<div class="col-sm-10">
									<form:select path="user.id" id="user" items="${userList}"
										class="form-control"></form:select>
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-2 control-label">Status</label>
								<div class="col-sm-10">
									<form:select path="status" id="status"
										class="form-control select">
										<form:option value="1" label="Initiate" />
										<form:option value="2" label="Trasfered" />
										<form:option value="3" label="pending" />
										<form:option value="4" label="can't clear" />
										<form:option value="5" label="Done" />
									</form:select>
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-2 control-label">Info</label>
								<div class="col-md-10">
									<textarea placeholder="comment" class="form-control" rows="5"></textarea>
								</div>
							</div>

							<div class="form-group text-right m-b-0">
								<button type="button" name="button"
									class="btn btn-primary waves-effect waves-light"
									onclick="saveupdate();">Submit</button>
							</div>

						</form:form>
					</div>
					<!-- end col -->
				</div>
				<!-- end row -->
			</div>
		</div>
		<!-- end col -->
	</div>
	<!-- end row -->

	
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




	<!-- App js -->
	<script
		src="<c:url value='/resources/ttSystemTemplate/dashboard/assets/js/jquery.core.js' />"></script>
	<script
		src="<c:url value='/resources/ttSystemTemplate/dashboard/assets/js/jquery.app.js' />"></script>



	<script type="text/javascript">
				function saveupdate(){
					alert("");
		       		var comment = $('#commrnt').val();
		       		var ttid = $('#id').val();
		       		var status = $('#status').val();
		       		var info=$("#info").val();
		       		var user=$("#user").val;
		       		
					      $.ajax({ 
											url:"ttUpdate.do", 
											type:"GET", 
											contentType: "application/json; charset=utf-8",
										 	data: { "comment":comment,"status":status,"info":info,"ttid":ttid},
										  	success: function(resposeJsonObject){ 
										    alert('currentuser was successfully updated');
										    document.forms['updateForm'].submit();
										    
											},
											error:function(data,status,er) { 
											alert("error: "+data+" status: "+status+" er:"+er);
											}	                
							});
					      

</script>

</body>
</html>
