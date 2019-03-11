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
<script type="text/javascript" src="<c:url value='/resources/ttSystemTemplate/js/jquery.js' />"></script>
<script src="<c:url value='/resources/ttSystemTemplate/js/jquery.min.js' />" type="text/javascript"></script>
<script type="text/javascript">
function saveupdate(){
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
					      
					      
			//	self.close();     
		     //  document.forms['updateForm'].submit();
		     //  window.close();
		     // document.close();
		  // w.close();
		}
		
		/* function closeMe(){
			window.close();
		} */

</script>	
</head>
<body>
	<!-- START PAGE CONTAINER -->
	

		<!-- START PAGE SIDEBAR -->
		
		<!-- END PAGE SIDEBAR -->

		<!-- PAGE CONTENT -->
		

			<!-- START X-NAVIGATION VERTICAL -->
			
			<!-- END X-NAVIGATION VERTICAL -->

			<!-- PAGE CONTENT WRAPPER -->
			

				<div class="row">
					<div class="col-md-12">
					<!-- <div class="panel panel-default"> -->
							<form:form id="updateForm" method="post" modelAttribute="UpdateTt" action="saveUpTt.do" class="form-horizontal">
								<form:hidden path="id" id="id" />
								<form:hidden path="comment" id="comment"/>
								<form:hidden path="systems.id" id="system"/>
								<form:hidden path="company.id" id="company"/>
								<form:hidden path="department.id" id="department"/>
								<div class="form-group">
									<label class="col-md-3 col-xs-12 control-label">Comment:</label>
									<div class="col-md-6 col-xs-12">
										<div class="input-group">
											<span class="input-group-addon"></span>
											<textarea id="commrnt" class="form-control" rows="5" placeholder="Comment"></textarea>
										</div>
									</div>
								</div>

								<div class="form-group">
                                        <label class="col-md-3 col-xs-12 control-label">Owner :</label>
                                        <div class="col-md-6 col-xs-12">                                            
                                             <div class="input-group">
                                                <span class="input-group-addon"></span>
													<form:select path="user.id" id="user" items="${userList}" class="form-control"></form:select>                                           	
                                            </div>    
                                            
                                           
                                        </div>
                                    </div>


								<div class="form-group">
									<label class="col-md-3 col-xs-12 control-label">Status:</label>
									<div class="col-md-6 col-xs-12">
										<div class="input-group">
                                           <span class="input-group-addon"></span>
											<form:select path="status" id="status" class="form-control select">
											<form:option value="1" label="Initiate" />
											<form:option value="2" label="Trasfered" />
											<form:option value="3" label="pending" />
											<form:option value="4" label="can't clear" />
											<form:option value="5" label="Done" />
										</form:select>
									</div>
								</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 col-xs-12 control-label">Info</label>
									<div class="col-md-6 col-xs-12">
										<div class="input-group">
											<span class="input-group-addon"></span>
											<textarea id ="info" class="form-control" rows="5"	placeholder="Info"></textarea>
										</div>
									</div>

								</div>
								<div class="form-group">

									
										<!-- <button class="btn btn-default">Clear Form</button> -->
										<button type="button" name="button" class="btn btn-primary pull-right" onclick="saveupdate();">Submit</button>
									

								</div>
							</form:form>
						
					</div>
				</div>

			
		

	
	<!-- END PAGE CONTENT WRAPPER -->
	
	<!-- </div>END PAGE CONTENT -->
	
	<!-- END PAGE CONTAINER -->

	<!-- MESSAGE BOX-->
	
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






