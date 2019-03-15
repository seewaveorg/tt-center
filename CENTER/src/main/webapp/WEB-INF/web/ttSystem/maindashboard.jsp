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

<link
	href="<c:url value='/resources/ttsystem/assets/images/favicon.ico' />"
	rel="shortcut icon">
<title>TT-center</title>

<!--Morris Chart CSS -->
<link
	href="<c:url value='/resources/ttsystem/assets/plugins/morris/morris.css' />"
	rel="stylesheet" media="screen">

<!-- App css -->
<link
	href="<c:url value='/resources/ttsystem/assets/css/bootstrap.min.css' />"
	rel="stylesheet" media="screen">
<link
	href="<c:url value='/resources/ttsystem/assets/css/core.css' />"
	rel="stylesheet" media="screen">
<link
	href="<c:url value='/resources/ttsystem/assets/css/components.css' />"
	rel="stylesheet" media="screen">
<link
	href="<c:url value='/resources/ttsystem/assets/css/icons.css' />"
	rel="stylesheet" media="screen">
<link
	href="<c:url value='/resources/ttsystem/assets/css/pages.css' />"
	rel="stylesheet" media="screen">
<link
	href="<c:url value='/resources/ttsystem/assets/css/menu.css' />"
	rel="stylesheet" media="screen">
<link
	href="<c:url value='/resources/ttsystem/assets/css/responsive.css' />"
	rel="stylesheet" media="screen">


<!-- Notification css (Toastr) -->
<link
	href="<c:url value='/resources/ttsystem/assets/plugins/toastr/toastr.min.css'/>"
	rel="stylesheet" media="screen">	


<!-- HTML5 Shiv and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->
        
<style>

@keyframes red {
   50% { background-color: #b71c1c ;
   } 
}

.bdred {
  animation-name: red;
  	opacity: 0.84;
    animation-duration: 1.5s;
    animation-timing-function: step-end;
    animation-iteration-count: infinite;
    animation-direction: alternate;
  
}

@keyframes orange {
   50% { background-color: #ff9800;
   } 
}

.bdorange {
  animation-name: orange;
  	opacity: 0.84;
    animation-duration: 1.5s;
    animation-timing-function: step-end;
    animation-iteration-count: infinite;
    animation-direction: alternate;
  
}

@keyframes blue {
   50% { background-color: #425cff;
   } 
}

.bdblue {
  animation-name: blue;
  	opacity: 0.84;
    animation-duration: 1.5s;
    animation-timing-function: step-end;
    animation-iteration-count: infinite;
    animation-direction: alternate;
  
}

.bddesable {
	background: #9E9E9E;
  
}

.blink {
  animation: blinker 1s cubic-bezier(.5, 0, 1, 1) infinite alternate;  
}
@keyframes blinker {  
  from { opacity: 1; }
  to { opacity: 0; }
}

</style>


<script
	src="<c:url value='/resources/ttsystem/assets/js/modernizr.min.js' />"></script>
</head>


<body class="fixed-left">

	<!-- Begin page -->
	<div id="wrapper">
		 <label></label><label hidden id="minutes">00</label><label hidden id="seconds">00</label>


		<!-- ============================================================== -->
		<!-- Start right Content here -->
		<!-- ============================================================== -->
		<div>
			<!-- Start content -->
			<div class="content">
				<div class="container">


					<div class="row" id="com">
						
				
					</div>
					<!-- end row -->



					<div class="row"></div>
					<!-- end row -->



				</div>
				<!-- container -->

			</div>
			<!-- content -->


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
	<script
		src="<c:url value='/resources/ttsystem/assets/js/detect.js' />"></script>
	<script
		src="<c:url value='/resources/ttsystem/assets/js/fastclick.js' />"></script>
	<script
		src="<c:url value='/resources/ttsystem/assets/js/jquery.slimscroll.js' />"></script>
	<script
		src="<c:url value='/resources/ttsystem/assets/js/jquery.blockUI.js' />"></script>
	<script
		src="<c:url value='/resources/ttsystem/assets/js/waves.js' />"></script>
	<script
		src="<c:url value='/resources/ttsystem/assets/js/jquery.nicescroll.js' />"></script>
	<script
		src="<c:url value='/resources/ttsystem/assets/js/jquery.scrollTo.min.js' />"></script>



	<!-- KNOB JS -->
	
	<!--[if IE]>
        <script
		src="<c:url value='/resources/ttsystem/assets/plugins/jquery-knob/excanvas.js' />"></script>
        <![endif]-->
        
    <script
		src="<c:url value='/resources/ttsystem/assets/plugins/jquery-knob/jquery.knob.js' />"></script>    
        
	<!--Morris Chart
	<script
		src="<c:url value='/resources/ttsystem/assets/plugins/morris/morris.min.js' />"></script>
	-->
	
	<script
		src="<c:url value='/resources/ttsystem/assets/plugins/raphael/raphael-min.js' />"></script>

	<!-- Dashboard init
	<script
		src="<c:url value='/resources/ttsystem/assets/pages/jquery.dashboard.js' />"></script>
	 -->
	 
	<!-- App js -->
	<script
		src="<c:url value='/resources/ttsystem/assets/js/jquery.core.js' />"></script>
	<script
		src="<c:url value='/resources/ttsystem/assets/js/jquery.app.js' />"></script>
		
		<!-- Toastr js -->
	<script
		src="<c:url value='/resources/ttSystemTemplate/dashboard/assets/plugins/toastr/toastr.min.js' />"></script>

	


	<script>
		$(document).ready(function() {
		
			loaddashboard();
			
		});

		function loaddashboard() {
			$.ajax({
				contentType : "application/json",
				type : "get",
				url : "loaddashboarddata.do",
				success : function(msg) {

					dashboardGenerator(msg);
					
					dashboardrefresh(); // firts time data load to dashboard
					connectionblink();
					timecounter(50); // time counter thered
				}
			});

		}


		function dashboardGenerator(msg){

				var json = JSON.parse(msg);
				
				var cages = "";
				var status = "Enable";
				for (var i = 0; i < json.length; i++) {


					cages += "<div class='col-lg-2 col-md-4' id='divid" + json[i].sysid + "' value='1'>" 
	                    + "<div id=" + json[i].sysid + " class='card-box'>"
	                    + "<div class='dropdown pull-right'>"
						+ "<a href='#' class='dropdown-toggle card-drop' data-toggle='dropdown' aria-expanded='false'>"
						+ "<i class='zmdi zmdi-more-vert'></i>" 
	                    + "</a>" 
	                    + "<ul class='dropdown-menu' role='menu'>"
						+ "<li><a target='blank' href='" + json[i].sysurl + "'>UPS Login</a></li>"
						+ "<li class='divider'></li>"
						+ "<li><a target='blank' onclick='javascript:syslocation("+json[i].sysid+");'>Location</a></li>"
						+ "<li><a target='blank' onclick='javascript:sysenabledesable("+json[i].sysid+");' >"+status+"</a></li>" 
	                    + "</ul>" 
	                    + "</div>"
						+ "<h4 class='header-title m-t-0 m-b-30'>" + json[i].sysname + ""
					    +"&nbsp;<i id='"+json[i].sysid+"active' class='fa fa-circle text-success'></i>"
					    +"&nbsp;<i id='"+json[i].sysid+"deactive' class='fa fa-circle text-danger blink'></i>"
						+ "</h4>"
						+ "<div onclick='javascript:openalarmwindow("+json[i].sysid+",\""+json[i].sysname+"\");' class='widget-box-2'>"
						+ "<div class='widget-detail-2'>" 
	                    + "<h4 id='" + json[i].sysid+ "clear' class='m-b-0 pull-left'>00 <br>Alarms Cleared</h4>" 
                        +"<div class='btn-group-vertical'>"
                        +   "<button id='" + json[i].sysid + "critical' type='button' class='btn btn-danger waves-effect waves-light btn-xs m-b-5'>00 Critical</button>"
                        +   "<button id='" + json[i].sysid + "major' type='button' class='btn btn-warning waves-effect waves-light btn-xs m-b-5'>00 Major</button>"
                        +   "<button id='" + json[i].sysid + "minor' type='button' class='btn btn-info waves-effect waves-light btn-xs m-b-5'>00 Minor</button>"
                        +"</div>"

                        
	                    + "</div>"

	                    
	                    
	                    + "<br>"
						+"<div class='progress progress-bar-success-alt progress-sm m-b-5'>"
						+ "<div class='progress-bar progress-bar-success '"
						+ "role='progressbar' aria-valuenow='100' aria-valuemin='0' aria-valuemax='100'"
						+ "style=' visibility: visible; animation-name: animationProgress;'>"
						+ "</div>"
						+"</div>"
						+"</div>"
						+"</div>"
						+"</div>";
				}

				$('#com').html(cages);

			}
				
		function timecounter(time){
			 var minutesLabel = document.getElementById("minutes");
		        var secondsLabel = document.getElementById("seconds");
		        var temptotalSeconds = time;
		        var totalSeconds = time;
		        setInterval(setTime, 1000);

		        

		        function setTime()
		        {
		            --totalSeconds;

		            var p = 100-((totalSeconds/time) * 100);

		            
		            $(".progress-bar").width(p+'%');
		            secondsLabel.innerHTML = pad(totalSeconds%60);
		            minutesLabel.innerHTML = pad(parseInt(totalSeconds/60));
		            
		            if(totalSeconds == 0){
		
			       	dashboardrefresh();
			       	connectionblink();  
		            totalSeconds = temptotalSeconds;
		            }
		        }

		        function pad(val)
		        {
		            var valString = val + "";
		            if(valString.length < 2)
		            {
		                return "0" + valString;
		            }
		            else
		            {
		                return valString;
		            }
		        }
			}

	
	
		function dashboardrefresh(){

			$.ajax({
				contentType : "application/json",
				type : "get",
				url : "dashboardrefresh.do",
				success : function(msg) {
					var upscount = 0;

				if(msg =="logout"){
						window.close();	
					}else{
					var str = msg;
					var str_array = str.split(',');
					var temp = 1;
					
					var red="";
					var orng="";
					var blue="";
					var normal ="";
					
					
					$.ajax({
						contentType : "application/json",
						type : "get",
						url : "getenabledesable.do",
						success : function(data) {
						//	data;
						//	alert(enbledesablesys);

							
			
						var json = JSON.parse(data);


					
						upscount = str_array[0];
						for(var i = 0; i < upscount; i++) {
							
							document.getElementById(str_array[temp]+"clear").innerHTML = str_array[temp+1]+"<br>Alarms<br>Cleared";
							document.getElementById(str_array[temp]+"critical").innerHTML = str_array[temp+2]+" Severe";
							document.getElementById(str_array[temp]+"major").innerHTML = str_array[temp+3]+" Warning";
							document.getElementById(str_array[temp]+"minor").innerHTML = str_array[temp+4]+" Info";
							
							

							 
							
							if(json[i].status == 1){
								if(str_array[temp+2]!=0){
									$("#"+str_array[temp]).removeClass("bdred");
									$("#"+str_array[temp]).removeClass("bdorange");
									$("#"+str_array[temp]).removeClass("bdblue");
									$("#"+str_array[temp]).removeClass("bddesable");
															
									$("#"+str_array[temp]).addClass("bdred");

									$("#divid"+str_array[temp]).attr('name', '1');

			
									
									var redData = $("#divid"+str_array[temp]);
									
									red += redData[0].outerHTML;
									
									
								}else if(str_array[temp+3]!=0){
									$("#"+str_array[temp]).removeClass("bdred");
									$("#"+str_array[temp]).removeClass("bdorange");
									$("#"+str_array[temp]).removeClass("bdblue");
									$("#"+str_array[temp]).removeClass("bddesable");
									
									$("#"+str_array[temp]).addClass("bdorange");

									$("#divid"+str_array[temp]).attr('name', '2');

									

									var orngData = $("#divid"+str_array[temp]);
									
									orng += orngData[0].outerHTML;
									
								}else if(str_array[temp+4]!=0){
									$("#"+str_array[temp]).removeClass("bdred");
									$("#"+str_array[temp]).removeClass("bdorange");
									$("#"+str_array[temp]).removeClass("bdblue");
									$("#"+str_array[temp]).removeClass("bddesable");
									
									$("#"+str_array[temp]).addClass("bdblue");

									$("#divid"+str_array[temp]).attr('name', '3');


									var blueData = $("#divid"+str_array[temp]);
									
									blue += blueData[0].outerHTML;
									
									 
								}else{
									$("#"+str_array[temp]).removeClass("bdred");
									$("#"+str_array[temp]).removeClass("bdorange");
									$("#"+str_array[temp]).removeClass("bdblue");
									$("#"+str_array[temp]).removeClass("bddesable");

									$("#divid"+str_array[temp]).attr('name', '4');


									var normalData = $("#divid"+str_array[temp]);
									
									normal += normalData[0].outerHTML;
									
									
								}

							}else if(json[i].status == 3){
								$("#"+str_array[temp]).removeClass("bdred");
								$("#"+str_array[temp]).removeClass("bdorange");
								$("#"+str_array[temp]).removeClass("bdblue");
								
								$("#"+str_array[temp]).addClass("bddesable");
								
							}
							
							
 
							temp = temp+5;
						}

						var parent =$('#com');
						parent.empty().append(red+orng+blue+normal);
						//div accending part
						

						}
					});
							
						
				    }
				}
			});

			}



		function divacc(){

			var parent =$('#records');
			
			var childSelector = "div"
			
			var keySelector ="span.listLeadAuthor";


			}

		function connectionblink(){
			var milsec = 50000;
			
			$.ajax({
				contentType : "application/json",
				type : "get",
				url : "heartbeatblink.do",
				data : "sec=" + milsec,
				success : function(msg) {

					if(msg=="logout"){
						window.close();
					}else{
					
					var upscount= 0;
					var str = msg;
					var str_array = str.split(',');
					var temp = 1;
					
					upscount = str_array[0];
					for(var i = 0; i < upscount; i++) {
						var activeid= "#"+str_array[temp]+"active";
						var deactiveid= "#"+str_array[temp]+"deactive";
						var status= str_array[temp+1];

						if(status==1){
							$(deactiveid).hide();
							$(activeid).show();	
						}else{
							$(activeid).hide();
							$(deactiveid).show();	
						}
					temp =  temp + 2;
					}

				}
				}
			});
						
		}

		
		function openalarmwindow(id,sysname){
			localStorage.setItem("systemid",id);
			localStorage.setItem("sysname",sysname);
		    window.open('alarmwindow.do');
			}

		function sysenabledesable(id){
			toastr["error"]("Try again later..!")
		}
		function syslocation(id){
			toastr["error"]("Try again later..!")
		}
		
	</script>

</body>
</html>