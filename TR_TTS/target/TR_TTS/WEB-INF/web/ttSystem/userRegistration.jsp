<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        
        <link href="<c:url value='/resources/ttSystemTemplate/css/theme-default.css' />" rel="stylesheet" media="screen">
<%--    <link href="<c:url value='/resources/vendors/datepicker.css' />" rel="stylesheet" media="screen"> --%>
		<script type="text/javascript" src="<c:url value='/resources/zebra-datetime-picker/public/javascript/zebra_datepicker.js' />"></script>
       	<script type="text/javascript" src="<c:url value='/resources/zebra-datetime-picker/examples/public/javascript/core.js' />"></script>
		<!-- 
		<link rel="stylesheet" href="<c:url value='/resources/zebra-datetime-picker/examples/public/css/reset.css' />" type="text/css">
        <link rel="stylesheet" href="<c:url value='/resources/zebra-datetime-picker/public/css/bootstrap.css' />" type="text/css">
        <link rel="stylesheet" href="<c:url value='/resources/zebra-datetime-picker/examples/public/css/style.css' />" type="text/css">
        <link rel="stylesheet" href="<c:url value='/resources/zebra-datetime-picker/examples/public/css/reset.css' />" type="text/css">
        <link rel="stylesheet" href="<c:url value='/resources/zebra-datetime-picker/examples/public/css/style.css' />" type="text/css">
        <link type="text/css" rel="stylesheet" href="<c:url value='/resources/zebra-datetime-picker/examples/libraries/syntaxhighlighter/public/css/shCoreDefault.css' />">
		-->
		<link rel="stylesheet" href="<c:url value='/resources/zebra-datetime-picker/public/css/metallic.css' />" type="text/css">

        <script type="text/javascript" src="<c:url value='/resources/zebra-datetime-picker/examples/libraries/syntaxhighlighter/public/javascript/XRegExp.js' />"> </script>
        <script type="text/javascript" src="<c:url value='/resources/zebra-datetime-picker/examples/libraries/syntaxhighlighter/public/javascript/shCore.js' />"></script>
        <script type="text/javascript" src="<c:url value='/resources/zebra-datetime-picker/examples/libraries/syntaxhighlighter/public/javascript/shLegacy.js' />"></script>
        <script type="text/javascript" src="<c:url value='/resources/zebra-datetime-picker/examples/libraries/syntaxhighlighter/public/javascript/shBrushJScript.js' />"></script>
        <script type="text/javascript" src="<c:url value='/resources/zebra-datetime-picker/examples/libraries/syntaxhighlighter/public/javascript/shBrushXml.js' />"></script>

        <!-- EOF CSS INCLUDE -->
        <!-- script for datepickers -->
        <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" />
		<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
		<script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
		
		<script type="text/javascript" src="<c:url value='/resources/ttSystemTemplate/js/jquery.js' />"></script>
		<script src="<c:url value='/resources/ttSystemTemplate/js/jquery.min.js' />" type="text/javascript"></script>
		
		
		
        <script>
		$(function() {
    		$('#bd').datepicker( {
        		changeMonth: true,
        		changeYear: true,
        		changeDate:true,
        		showButtonPanel: true,
        		dateFormat: 'yyyy-mm-dd',
        		onClose: function(dateText, inst) { 
        			var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val();
		            var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
		            var date = $("#ui-datepicker-div .ui-datepicker-date :selected").val();
		            $(this).datepicker('setDate', new Date(year, month, date));
        	}
    		});
		});
		
		$(document).ready(function(){
			$("#company").change(function(){
				var id=$(this).val();
				var value = 'id='+ id;
				$.ajax({
					type: "get",
					url: "getValuesForDepartmentDropdowns.do",
					//contentType : 'application/json',
					data: "value="+value,
					success: function(msg){
						alert(msg);
						$('#department').html(msg);
					}
				});
				
			});
		});
		
		function saveupdate(){
       		var department = document.getElementById("department").value;
       		var company=$('#company').val();
       		alert(department);
       		alert(company);
			      $.ajax({ 
									url:"updateUserHasDepartment.do", 
									type:"GET", 
									contentType: "application/json; charset=utf-8",
								 	data: { "department":department,"company":company},
								  	success: function(resposeJsonObject){ 
								    alert('currentuser was successfully updated');
								    
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
		</script>
    </head>
    <body>
        <!-- START PAGE CONTAINER -->
       		
            
            <!-- START PAGE SIDEBAR -->
           
            <!-- END PAGE SIDEBAR -->
            
            <!-- PAGE CONTENT -->
            
            
            	
                
                <!-- START X-NAVIGATION VERTICAL -->
                
                <!-- END X-NAVIGATION VERTICAL -->                     
                
                                
                
                <div class="page-title">                    
                    <h2><span class="fa fa-arrow-circle-o-left"></span> User Registration</h2>
                </div>                   
                
                <!-- PAGE CONTENT WRAPPER -->
                <div class="page-content-wrap">
                
                    <div class="row">
                        <div class="col-md-12">
                        
                         <form:form method="post" modelAttribute="person" action="saveUser.do"  class="form-horizontal">
                            <form:hidden path="id" id="id"/>
                            <form:hidden path="personType.id" value="6"/>
                            <div class="panel panel-default">
                                
                               
                                <div class="panel-body">

                                	<div class="form-group">
                                        <label class="col-md-3 col-xs-12 control-label">Title:</label>
                                        <div class="col-md-6 col-xs-12"> 
                                          	<div class="input-group">
                                                <span class="input-group-addon"></span>                                                                                      
                                            		<form:select path="title" class="form-control select">
                                            			<form:option value="Mr" label="Mr" />
														<form:option value="Mrs" label="Mrs" />
														<form:option value="Miss" label="Miss" />
                                           			</form:select>
                                        	</div>
                                    	</div>
                                    </div>
                                    
                                    <div class="form-group">                                        
                                        <label class="col-md-3 col-xs-12 control-label">Initials</label>
                                        <div class="col-md-6 col-xs-12">
                                            <div class="input-group">
                                                <span class="input-group-addon"></span>
                                                <form:input path="initials" class="form-control" placeholder="Initials" />
                                            </div>            
                                            
                                        </div>
                                    </div>

                                    <div class="form-group">                                        
                                        <label class="col-md-3 col-xs-12 control-label">First Name</label>
                                        <div class="col-md-6 col-xs-12">
                                            <div class="input-group">
                                                <span class="input-group-addon"></span>
                                                <form:input path="firstname" class="form-control" placeholder="First Name" />
                                            </div>            
                                            
                                        </div>
                                    </div>
                                    <!--  -->
                                   <div class="form-group">                                        
                                        <label class="col-md-3 col-xs-12 control-label">Last Name</label>
                                        <div class="col-md-6 col-xs-12">
                                            <div class="input-group">
                                                <span class="input-group-addon"></span>
                                                <form:input path="lastname" class="form-control" placeholder="Last Name" />
                                            </div>            
                                            
                                    	</div>
                                    </div>
                                    <div class="form-group">                                        
                                        <label class="col-md-3 col-xs-12 control-label">Date of Birth</label>
                                        <div class="col-md-6 col-xs-12">
                                         <div class="input-group">
                                         	<span class="input-group-addon"></span>
        							<!-- <div class="container">
    									<div class="row"> -->
        									<div class='col-sm-6'>
            									<div class="form-group">
                									<div class='input-group date' id='datetimepicker1'>
                    									<form:input type="text" path="birthdate" class="form-control" id="bd" />
                    										<span class="input-group-addon">
                        										<span class="glyphicon glyphicon-calendar"></span>
                    										</span>
                									</div>
                								</div>
            								</div>
        								</div>
    									</div>
									</div>
        									
    								<div class="form-group">
                                        <label class="col-md-3 col-xs-12 control-label">Gender:</label>
                                        <div class="col-md-6 col-xs-12"> 
                                          	<div class="input-group">
                                                <span class="input-group-addon"></span>                                                                                      
                                            		<form:select path="gender" class="form-control select">
                                            			<form:option value="male" label="Male" />
														<form:option value="female" label="Female" />
                                           			</form:select>
                                        	</div>
                                    	</div>
                                    </div>
                                    
                                    <div class="form-group">                                        
                                        <label class="col-md-3 col-xs-12 control-label">Address</label>
                                        <div class="col-md-6 col-xs-12">
                                            <div class="input-group">
                                                <span class="input-group-addon"></span>
                                                <form:input path="addressLine1" class="form-control" placeholder="Address Line 1" />
                                            </div>
                                    	</div>
                                    </div>
                                    <div class="form-group">                                        
                                        <label class="col-md-3 col-xs-12 control-label"></label>
                                        <div class="col-md-6 col-xs-12">
                                            <div class="input-group">
                                                <span class="input-group-addon"></span>
                                                <form:input path="addressLine2" class="form-control" placeholder="Address Line 2" />
                                            </div> 
                                    	</div>
                                    </div>
                                    <div class="form-group">                                        
                                        <label class="col-md-3 col-xs-12 control-label"></label>
                                        <div class="col-md-6 col-xs-12">
                                            <div class="input-group">
                                                <span class="input-group-addon"></span>
                                                <form:input path="addressLine3" class="form-control" placeholder="Address Line 3" />
                                            </div> 
                                    	</div>
                                    </div>
                                    <div class="form-group">                                        
                                        <label class="col-md-3 col-xs-12 control-label">NIC</label>
                                        <div class="col-md-6 col-xs-12">
                                            <div class="input-group">
                                                <span class="input-group-addon"></span>
                                                <form:input path="nic" class="form-control" placeholder="NIC" />
                                            </div>
                                    	</div>
                                    </div>
                                    <div class="form-group">                                        
                                        <label class="col-md-3 col-xs-12 control-label">Mobile</label>
                                        <div class="col-md-6 col-xs-12">
                                            <div class="input-group">
                                                <span class="input-group-addon"></span>
                                                <form:input path="mobile" class="form-control" placeholder="Mobile" />
                                            </div> 
                                    	</div>
                                    </div>
                                    <div class="form-group">                                        
                                        <label class="col-md-3 col-xs-12 control-label">Email</label>
                                        <div class="col-md-6 col-xs-12">
                                            <div class="input-group">
                                                <span class="input-group-addon"></span>
                                                <form:input path="email" class="form-control" placeholder="Email" />
                                            </div>            
                                            
                                    	</div>
                                    	
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-3 col-xs-12 control-label">Company</label>
                                        <div class="col-md-6 col-xs-12">  
                                        	<div class="input-group">
                                            	<span class="input-group-addon"></span>
                                        			<form:select path="company.id" id="company" items="${companyList }"  class="form-control"></form:select>                                                                                          
                                        	</div>
                                    	</div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-3 col-xs-12 control-label">Department</label>
                                        <div class="col-md-6 col-xs-12">  
                                        	<div class="input-group">
                                                <span class="input-group-addon"></span>
                                        			<select  id="department" class="form-control select">           
                                           			</select>
                                            </div>
                                    	</div>
                                    </div>

                                <div class="panel-footer">            
                                    <button type="submit" class="btn btn-primary pull-right" onclick="saveupdate();">Submit</button>
                                </div>
                            </div>
                           </div>
                        </form:form>
                </div>
                </div>
            	</div>
                <!-- END PAGE CONTENT WRAPPER -->                
                        
            <!-- END PAGE CONTENT -->
        
        <!-- END PAGE CONTAINER -->

        <!-- MESSAGE BOX-->
        <div class="message-box animated fadeIn" data-sound="alert" id="mb-signout">
            <div class="mb-container">
                <div class="mb-middle">
                    <div class="mb-title"><span class="fa fa-sign-out"></span> Log <strong>Out</strong> ?</div>
                    <div class="mb-content">
                        <p>Are you sure you want to log out?</p>                    
                        <p>Press No if youwant to continue work. Press Yes to logout current user.</p>
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
        
        <!-- END PLUGINS -->

        <!-- THIS PAGE PLUGINS -->

        <!-- END PAGE PLUGINS -->         

        <!-- START TEMPLATE -->
        <script src="<c:url value='/resources/ttSystemTemplate/js/plugins.js' />"></script>
        <script src="<c:url value='/resources/ttSystemTemplate/js/actions.js' />"></script>
        <!-- END TEMPLATE -->
    <!-- END SCRIPTS -->         
    </body>
</html>






