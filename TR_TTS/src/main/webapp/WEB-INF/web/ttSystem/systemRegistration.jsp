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
       		
            
            <!-- START PAGE SIDEBAR -->
           
            <!-- END PAGE SIDEBAR -->
            
            <!-- PAGE CONTENT -->
            
            
            	
                
                <!-- START X-NAVIGATION VERTICAL -->
                
                <!-- END X-NAVIGATION VERTICAL -->                     
                
                                
                
                <div class="page-title">                    
                    <h2><span class="fa fa-arrow-circle-o-left"></span> System Registration</h2>
                </div>                   
                
                <!-- PAGE CONTENT WRAPPER -->
                <div class="page-content-wrap">
                
                    <div class="row">
                        <div class="col-md-12">
                        
                         <form:form method="post" modelAttribute="systems" action="saveSystems.do"  class="form-horizontal">
                            <form:hidden path="id" id="id"/>
                            <div class="panel panel-default">
                                
                               
                                <div class="panel-body">
                                    
                                    <div class="form-group">                                        
                                        <label class="col-md-3 col-xs-12 control-label">System : </label>
                                        <div class="col-md-6 col-xs-12">
                                            <div class="input-group">
                                              <span class="input-group-addon"></span>
                                                <form:input path="name" class="form-control" placeholder="System" />
                                            </div>            
                                            
                                        </div>
                                    </div>

                                     <div class="form-group">
                                        <label class="col-md-3 col-xs-12 control-label">Details</label>
                                        <div class="col-md-6 col-xs-12">  
                                        <div class="input-group">
                                           <span class="input-group-addon"></span>                                          
                                            	<form:textarea path="description" class="form-control" rows="5" placeholder="Details"/>
                                           </div>
                                        </div>
                                    </div>
                                    <!--  -->
                                   <div class="form-group">
                                        <label class="col-md-3 col-xs-12 control-label">Company</label>
                                        <div class="col-md-6 col-xs-12"> 
                                        	<div class="input-group">
                                                <span class="input-group-addon"></span> 
                                        		<form:select path="company.id" id="type" items="${companyList}" class="form-control"></form:select>
                                         	</div>  
                                        </div>
                                    </div>
                                </div>
                                <div class="panel-footer">             
                                    <button type="submit" class="btn btn-primary pull-right">Submit</button>
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






