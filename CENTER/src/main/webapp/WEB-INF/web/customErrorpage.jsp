<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@page session="true"%>
<%@ page import="com.ngs.service.SysUser" %>


<%
// Get the user object from session   class="no-js"
SysUser user =(SysUser)session.getAttribute("user");
String fullName= user.getName();
String loginID =  user.getId() +" ";
int person = user.getUser();
String roll = user.getRolle();
int rollid = user.getRolleid();
           
 %>      


<!DOCTYPE html>
<html >
    
    <head>
        <title>NextGenMed</title>
        <!-- Bootstrap -->
        <link href="<c:url value='/resources/bootstrap/css/bootstrap.min.css' />" rel="stylesheet" media="screen">
        <link href="<c:url value='/resources/bootstrap/css/bootstrap-responsive.min.css' />" rel="stylesheet" media="screen">
        <link href="<c:url value='/resources/vendors/easypiechart/jquery.easy-pie-chart.css' />" rel="stylesheet" media="screen">
        <link href="<c:url value='/resources/assets/styles.css' />" rel="stylesheet" media="screen">
        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
        <script src="<c:url value='/resources/vendors/modernizr-2.6.2-respond-1.1.0.min.js' />"></script>
        
        <!--/.fluid-container-->
        <script src="<c:url value='/resources/js/jquery-1.11.3.min.js' />"></script>
        <script src="<c:url value='/resources/bootstrap/js/bootstrap.min.js' />"></script>
        <script src="<c:url value='/resources/vendors/easypiechart/jquery.easy-pie-chart.js' />"></script>
        <script src="<c:url value='/resources/assets/scripts.js' />"></script>
        <script>
        
        $(function() {
            // Easy pie charts
            $('.chart').easyPieChart({animate: 1000});
        });
        </script>
        
        
        
        <!-- Adding jquery -->
       <script type="text/javascript" src="<c:url value="/resources/js/jquery-ui.min.js" />"></script>
  		<script type="text/javascript" src="<c:url value="/resources/js/jquery.popupWindow.js" />"></script>
  		<script type="text/javascript" src="<c:url value="/resources/js/jquery.validate.js" />"></script>
    	 <script type="text/javascript" src="<c:url value="/resources/js/ajaxLoop.js" />"></script>
    	 
      <script type="text/javascript">
      
      $(document).ready(function() {
    	  
    	  //the min chars for username  
          var min_chars = 4;  
        
          //result texts  
          var characters_error = 'Minimum amount of character is 4';  
          var checking_html = 'Checking...';  
          
          // load the menu
          loadMenu(<%= rollid %>);
    
          
          // load the menu
          $('#mymenu').click(function () {   
   			$.ajax({
             
              type: "get",
              url: "getMenu.do", //this is my servlet
              data: "rollid="+rollid,
              success: function(msg){   
                  $('#generatedmenu').html(msg);
                    	 //$("#loading").hide();

                    }
             });
   			
          });
          
          
          //when button is clicked  
          $('#firstname').change(function(){  
              //run the character number check  
              if($('#firstname').val().length < min_chars){  
                  //if it's bellow the minimum show characters_error text '  
                  $('#serach').html(characters_error);  
              }else{  
                  //else show the cheking_text and run the function to check  
                  $('#search').html(checking_html);  
                  check_availability();  
              }  
          });  
    
    	 
          $('#person').click(function () {
          		   	//$("#loading").show();
          		   	var tem= 1;
                  	$("#msg").val(" Data Request is processing ! ");
                      $.ajax({
                   
                          type: "get",
                          url: "person.do", //this is my servlet
                          data: "userid="+tem,
                          success: function(msg){   
                          	 $('#container').html(msg);
                          	 //$("#loading").hide();
                          	
                          	// $("#decoder").val(" Ready To accept Data Requests ");
                          }
                      });
               
                  });
          
          
          $('#docUpload').click(function () {
    		   	//$("#loading").show();
    		   	var tem= 1;
            	$("#msg").val(" Document uploading Interface ! ");
                $.ajax({
             
                    type: "get",
                    url: "fileUpload.do", //this is my servlet
                    data: "userid="+tem,
                    success: function(msg){   
                    	 $('#container').html(msg);
                    	 //$("#loading").hide();
                    	
                    	// $("#decoder").val(" Ready To accept Data Requests ");
                    }
                });
         
            });
          
          $('#emailUsButton').click(function () {
  		   	//$("#loading").show();
  		   	alert('goint to contact by email');
  		   	var tem= 1;
          	$("#msg").val(" Send a email to us ! ");
              $.ajax({
           
                  type: "get",
                  url: "contactByEmail.do", //this is my servlet
                 // data: "userid="+tem,
                  success: function(msg){   
                  	 $('#container').html(msg);
                  	 //$("#loading").hide();
                  	
                  	// $("#decoder").val(" Ready To accept Data Requests ");
                  }
              });
       
          });
          
          
          
          //bellow function can be used to open a popup window
     		jQuery('.example3demo').popupWindow({ 
    			//height:500, 
    			//width:800, 
    			centerScreen:1  
    		}); 
          
          
     	
         
     	
          // end of document ready
          
      });
      


  	//function to check username availability  
		function check_availability(){  
		  
		        //get the username  
		        var parameter = $('#firstname').val();  
		        var attribute = "firstname";
		        
		        $.ajax({
	                   
                    type: "post",
                    url: "GetPerson.do", //this is my servlet
                    data: "param="+parameter+"&attrib="+attribute,
                    success: function(msg){   
                    	 $('#search').html(msg);
                    	 //$("#loading").hide();
                    	
                    	// $("#decoder").val(" Ready To accept Data Requests ");
                    }
                });
		  
		
		  
		}  
		
		
		
      
      
      
      </script>
    
    
    </head>

 <body>
 
        <div class="navbar navbar-fixed-top">
            <div class="navbar-inner">
                <div class="container-fluid">
                    <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> <span class="icon-bar"></span>
                     <span class="icon-bar"></span>
                     <span class="icon-bar"></span>
                    </a>
                    <a class="brand" href="#">NextGenMed</a>
                    <div class="nav-collapse collapse">
                        <ul class="nav pull-right">
                            <li class="dropdown">
                                <a href="#" role="button" class="dropdown-toggle" data-toggle="dropdown"> <i class="icon-user"></i> <%= fullName %> <i class="caret"></i>

                                </a>
                                <ul class="dropdown-menu">
                                    <li>
                                        <a tabindex="-1" href="#">Profile</a>
                                    </li>
                                    <li class="divider"></li>
                                    <li>
                                        <a tabindex="-1"  href="login.do?logout" >Change Password</a>
                                    </li>
                                    <li class="divider"></li>
                                    <li>
                                        <a tabindex="-1"  href="login.do?logout" >Logout</a>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                        <ul class="nav">
                            <li class="active">
                                <a href="welcome.do">Home</a>
                            </li>

                            <li class="dropdown">
                                <a href="#" role="button" class="dropdown-toggle" data-toggle="dropdown">Contact Us <i class="caret"></i>

                                </a>
                                <ul class="dropdown-menu">
                                    <li>
                                        <a tabindex="-1" id="emailUsButton">Email us</a>
                                    </li>
                                    <li>
                                     <a tabindex="-1" id="mymenu">My Menu</a>
                                    </li>
                                    <li class="divider"></li>
                                    <li>
                                        <a tabindex="-1" href="#">FAQ</a>
                                    </li>
                                </ul>
                            </li>

                        </ul>
                    </div>
                
                    <!--/.nav-collapse -->
                </div>
            </div>
        </div>
        <div class="container-fluid" >
            <div class="row-fluid" >
                <div class="span3" id="sidebar" style=" position: fixed;">
                <div id="generatedmenu">
 
            
                </div>
               <!-- clossing row fluid -->
             </div>
             
                
                <!--/span-->
                <div class="span9" id="content" >
                    <div class="row-fluid">
                        <div class="alert alert-success" id="msg">
							<button type="button" class="close" data-dismiss="alert">&times;</button>
                            <h4> Attention Please</h4>
                        	${errorMessage }
                        	</div>

                    	</div>
                    	
                    <!-- This container will be used to replace when we click on icons to open a interface -->
                    <div id="container" >
                    
                        <div class="row-fluid">
                        <!-- block -->
                        <div class="block">
                            <div class="navbar navbar-inner block-header">
                                <div class="muted pull-left">Gallery</div>
                                <div class="pull-right"><span class="badge badge-info">1,462</span>

                                </div>
                            </div>
                            <div class="block-content collapse in">
                                <div class="row-fluid padd-bottom">
                                  <div class="span3"  >
                                      <a href="#" class="thumbnail" style="border-radius: 25px; padding: 20px; width: 150px; height: 150px;">
                                        <img id="person" alt="Person" style="border-radius: 25px; width: 150px; height: 150px;" src="<c:url value='/resources/images/person.JPG' />" >
                                      </a>
                                  </div>
                                  <div class="span3" >
                                      <div class="thumbnail" style="border-radius: 25px; padding: 20px; width: 150px; height: 150px;">
                                      <a href="person.do" class="example3demo" title="Register a Patient">
                                      <img  data-src="holder.js/260x180" alt="Patient" style="border-radius: 25px; width: 150px; height: 150px;" src="<c:url value='/resources/images/patient.JPG' />"  /> 
                                       </a>
                                      </div>
                                  </div>
                                  <div class="span3"  >
                                   <a href="#" class="thumbnail" style="border-radius: 25px; padding: 20px; width: 150px; height: 150px;">
                                        <img id="docUpload" alt="Report upload" data-src="holder.js/260x180" alt="Lab Reports" style="border-radius: 25px; width: 150px; height: 150px;" src="<c:url value='/resources/images/reports.JPG' />" >
                                         </a>
                                  </div>
                                  <div class="span3">
                                      <a href="#" class="thumbnail" style="border-radius: 25px; padding: 20px; width: 150px; height: 150px;">
                                      <img data-src="holder.js/260x180" alt="Chit" style="border-radius: 25px; width: 150px; height: 150px;" src="<c:url value='/resources/images/chit.JPG' />" >
                                       </a>
                                  </div>
                                </div>
								
                            </div>
                            <!--  Collsing the block -->
                        </div>
               <!-- Clossing row fluid -->
               </div>
                    
            <!-- Clossing Container -->
          </div>     	
                    	
                    	
            
         
                  
                            <div class="navbar">
                            	<div class="navbar-inner">
	                                <ul class="breadcrumb">
	                                    <i class="icon-chevron-left hide-sidebar"><a href='#' title="Hide Sidebar" rel='tooltip'>&nbsp;</a></i>
	                                    <i class="icon-chevron-right show-sidebar" style="display:none;"><a href='#' title="Show Sidebar" rel='tooltip'>&nbsp;</a></i>
	                                    <li>
	                                        <a href="#">Dashboard</a> <span class="divider">/</span>	
	                                    </li>
	                                    <li>
	                                        <a href="#">Settings</a> <span class="divider">/</span>	
	                                    </li>
	                                    <li class="active">Tools</li>
	                                </ul>
                            	</div>
                        	</div>
           
                    
                    <!-- clossing span -->
                </div>
                <!-- Clossing container fluid -->
            </div>
            
            <footer>
                <p>&copy; Next Generation Services Holdings</p>
            </footer>
        </div>
        

    </body>

</html>