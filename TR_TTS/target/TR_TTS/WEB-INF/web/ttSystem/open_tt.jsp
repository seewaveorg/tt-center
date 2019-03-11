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
        
        <link href="<c:url value='/resources/ttSystemTemplate/css/theme-default.css' />" rel="stylesheet" media="screen">
        <!-- EOF CSS INCLUDE -->
    </head>
    <body>
        <!-- START PAGE CONTAINER -->
        
            <!-- END PAGE SIDEBAR -->
            
            <!-- PAGE CONTENT -->
            
                
                <!-- START X-NAVIGATION VERTICAL -->
                
                <!-- END X-NAVIGATION VERTICAL -->                     
                
                                
                
                                 
                
                 <!-- PAGE CONTENT WRAPPER -->
                
                
                    <div class="row">
                        <div class="col-md-12">
                            
                       <form:form action="saveTticket.do" method="post" modelAttribute="ticketsave" class="form-horizontal" >
						
						<form:hidden path="id" id="id"/>
         
         
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h3 class="panel-title"><strong>Trouble Ticket</strong></h3>
                                   
                                </div>
                               
                                <div class="panel-body"> 
                                     <div class="form-group">
                                        <label class="col-md-3 col-xs-12 control-label">Comment:</label>
                                        <div class="col-md-6 col-xs-12">                                            
                                             <div class="input-group">
                                                <span class="input-group-addon"></span>
                                            	<form:textarea path="comment" class="form-control" rows="5" placeholder="Comment"></form:textarea>
                                            	
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
                                           <div class="input-group"><span class="input-group-addon"></span>                                                                                         
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
                                        <label class="col-md-3 col-xs-12 control-label">System</label>
                                        <div class="col-md-6 col-xs-12"> 
                                        <div class="input-group"><span class="input-group-addon"></span> 
                                        	<form:select path="systems.id" id="system" items="${systemList}" class="form-control"></form:select>
                                        </div>
                                    </div>
                                     </div>
                                     <div class="form-group">
                                        <label class="col-md-3 col-xs-12 control-label">Company</label>
                                        <div class="col-md-6 col-xs-12">  
                                        <div class="input-group"><span class="input-group-addon"></span>
                                        		<form:select path="company.id" id="type" items="${companyList}" class="form-control"></form:select>
                                        </div>
                                    	</div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-3 col-xs-12 control-label">Department</label>
                                        <div class="col-md-6 col-xs-12">  
                                        <div class="input-group"><span class="input-group-addon"></span>
                                        	<form:select path="department.id" id="type" items="${departmentList}" class="form-control"></form:select>
                                        </div>
                                    </div>
                                    </div>

                                </div>
                                <div class="panel-footer">
                                                                      
                                    <button class="btn btn-primary pull-right">Submit</button>
                                </div>
                            </div>
                            </form:form>
                            
                        </div>
                    </div>                    
                    
              
                <!-- END PAGE CONTENT WRAPPER -->                   
                     
            <!-- END PAGE CONTENT -->
        
        <!-- END PAGE CONTAINER -->

        <!-- MESSAGE BOX-->
        
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






