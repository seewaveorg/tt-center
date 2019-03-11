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
        <title>NGS_TT</title>            
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        
        <link rel="icon" href="favicon.ico" type="image/x-icon" />
        <!-- END META SECTION -->
        
        <!-- CSS INCLUDE -->        
        
        <link href="<c:url value='/resources/ttSystemTemplate/css/theme-default.css' />" rel="stylesheet" media="screen">
        <!-- EOF CSS INCLUDE -->
        <script type="text/javascript" src="<c:url value='/resources/ttSystemTemplate/js/jquery.js' />"></script>
		<script src="<c:url value='/resources/ttSystemTemplate/js/jquery.min.js' />" type="text/javascript"></script>

		<script type="text/javascript">
        /* function sendMail(){
        	alert("inside email");
        	//var id = $('#comment').val();
        	var id="${ticketsave}";
        	alert("trouble ticket id "+id);
       		//var user=$("#user").val;
       		
			      $.ajax({ 
			    	  				type:"get",
									url:"sendEmail.do",
									//contentType: "application/json; charset=utf-8",
								 	//data: {"value="+id},
								  	success: function(resposeJsonObject){ 
								    alert('mail sended');
									}
											                
					});
        } */
        </script>
    </head>
    <body>
        <!-- START PAGE CONTAINER
        <div class="page-container">
         -->
        
            
            <!-- START PAGE SIDEBAR -->
            <!--  -->
            
            <!-- END PAGE SIDEBAR -->
            
            <!-- PAGE CONTENT 
            <div class="page-content">
            -->
            
                
                <!-- START X-NAVIGATION VERTICAL -->
                
                <!-- END X-NAVIGATION VERTICAL -->                     
                
                                
                
                <div class="page-title">                    
                    <h2><span class="fa fa-arrow-circle-o-left"></span> Trouble Ticket</h2>
                </div>                   
                
                 <!-- PAGE CONTENT WRAPPER -->
                
                
                    <div class="row">
                        <div class="col-md-12">
                            
                       <form:form action="saveTticket.do" method="post" modelAttribute="ticketsave" class="form-horizontal" >
						
						<form:hidden path="id" id="id" />
         
         
                            <div class="panel panel-default">
                                <div class="panel-body"> 
                                     <div class="form-group">
                                        <label class="col-md-3 col-xs-12 control-label">Comment:</label>
                                        <div class="col-md-6 col-xs-12">                                            
                                             <div class="input-group">
                                                <span class="input-group-addon"></span>
                                            	<form:textarea path="comment" class="form-control" rows="5" placeholder="Comment" id="comment"></form:textarea>
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
                                          <div class="input-group">
                                                <span class="input-group-addon"></span>                                                                                      
                                            <form:select path="status" class="form-control select">
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
                                        <label class="col-md-3 col-xs-12 control-label">Company</label>
                                        <div class="col-md-6 col-xs-12">  
                                        <div class="input-group">
                                                <span class="input-group-addon"></span>
                                        <form:select path="company.id" id="type" items="${companyList}" class="form-control"></form:select>                                                                                          
                                        </div>
                                    </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-3 col-xs-12 control-label">Department</label>
                                        <div class="col-md-6 col-xs-12">  
                                        <div class="input-group">
                                                <span class="input-group-addon"></span>
                                        <form:select path="department.id" id="type" items="${departmentList}"  class="form-control"></form:select>                                                                                          
                                                                                       
                                           
                                        </div>
                                    </div>
                                    </div>
                                     <div class="form-group">
                                        <label class="col-md-3 col-xs-12 control-label">System</label>
                                        <div class="col-md-6 col-xs-12">  
                                        <div class="input-group">
                                                <span class="input-group-addon"></span>
                                        <form:select path="systems.id" id="type" items="${systemList}" class="form-control"></form:select>                                                                                          
                                                                                       
                                           
                                        </div>
                                    </div>
                                    </div>
                                    
									<!--
									<div class="form-group">
                                        <label class="col-md-3 col-xs-12 control-label">Status:</label>
                                        <div class="col-md-6 col-xs-12">                                                                                            
                                            <select class="form-control select">
                                                <option>Option 1</option>
                                                <option>Option 2</option>
                                                <option>Option 3</option>
                                                <option>Option 4</option>
                                                <option>Option 5</option>
                                            </select>
                                           
                                        </div>
                                    </div>

                                     <div class="form-group">
                                        <label class="col-md-3 col-xs-12 control-label">Status:</label>
                                        <div class="col-md-6 col-xs-12">                                                                                            
                                            <select class="form-control select">
                                                <option>Option 1</option>
                                                <option>Option 2</option>
                                                <option>Option 3</option>
                                                <option>Option 4</option>
                                                <option>Option 5</option>
                                            </select>
                                           
                                        </div>
                                    </div>

                                     <div class="form-group">
                                        <label class="col-md-3 col-xs-12 control-label">Status:</label>
                                        <div class="col-md-6 col-xs-12">                                                                                            
                                            <select class="form-control select">
                                                <option>Option 1</option>
                                                <option>Option 2</option>
                                                <option>Option 3</option>
                                                <option>Option 4</option>
                                                <option>Option 5</option>
                                            </select>
                                           
                                        </div>
                                    </div>  -->
                                </div>
                                <div class="panel-footer">
                                    <button type="submit" class="btn btn-primary pull-right" onclick="sendMail()">Submit</button>
                                </div>
                            </div>
                            </form:form>
                            
                        </div>
                    </div>                    
                    
                
                <!-- END PAGE CONTENT WRAPPER -->                   
                        
            <!-- END PAGE CONTENT </div>-->
        
        <!-- END PAGE CONTAINER </div>-->

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
        
        <script src="<c:url value='/resources/ttSystemTemplate/js/plugins/jquery/jquery.min.js' />"></script>
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






