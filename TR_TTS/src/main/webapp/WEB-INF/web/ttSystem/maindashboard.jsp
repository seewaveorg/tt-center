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
	href="<c:url value='/resources/ttSystemTemplate/dashboard/assets/images/favicon.ico' />"
	rel="shortcut icon">
<title>TT System | FNH</title>

<!--Morris Chart CSS -->
<link
	href="<c:url value='/resources/ttSystemTemplate/dashboard/assets/plugins/morris/morris.css' />"
	rel="stylesheet" media="screen">

<!-- App css -->
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
		 <label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><label id="minutes">00</label>:<label id="seconds">00</label>



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



	<!-- KNOB JS -->
	
	<!--[if IE]>
        <script
		src="<c:url value='/resources/ttSystemTemplate/dashboard/assets/plugins/jquery-knob/excanvas.js' />"></script>
        <![endif]-->
        
    <script
		src="<c:url value='/resources/ttSystemTemplate/dashboard/assets/plugins/jquery-knob/jquery.knob.js' />"></script>    
        
	<!--Morris Chart
	<script
		src="<c:url value='/resources/ttSystemTemplate/dashboard/assets/plugins/morris/morris.min.js' />"></script>
	-->
	
	<script
		src="<c:url value='/resources/ttSystemTemplate/dashboard/assets/plugins/raphael/raphael-min.js' />"></script>

	<!-- Dashboard init
	<script
		src="<c:url value='/resources/ttSystemTemplate/dashboard/assets/pages/jquery.dashboard.js' />"></script>
	 -->
	 
	<!-- App js -->
	<script
		src="<c:url value='/resources/ttSystemTemplate/dashboard/assets/js/jquery.core.js' />"></script>
	<script
		src="<c:url value='/resources/ttSystemTemplate/dashboard/assets/js/jquery.app.js' />"></script>

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
					$('#com').html(msg);
					dashboardrefresh(); // firts time data load to dashboard
					timecounter(30); // time counter thered
				}
			});

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
		            secondsLabel.innerHTML = pad(totalSeconds%60);
		            minutesLabel.innerHTML = pad(parseInt(totalSeconds/60));
		            
		            if(totalSeconds == 0){
		
			       	dashboardrefresh();  
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
					var str = msg;
					var str_array = str.split(',');
					var temp = 1;
					

					   
					  
						upscount = str_array[0];
						for(var i = 0; i < upscount; i++) {
							document.getElementById(str_array[temp]+"clear").innerHTML = str_array[temp+1]+"<br>Alarms Cleared";
							document.getElementById(str_array[temp]+"critical").innerHTML = str_array[temp+2]+" Critical";
							document.getElementById(str_array[temp]+"major").innerHTML = str_array[temp+3]+" Major";
							document.getElementById(str_array[temp]+"minor").innerHTML = str_array[temp+4]+" Minor";
							

							if(str_array[temp+2]!=0){
								document.getElementById(temp).style.border = "solid #E57373";
							}else if(str_array[temp+3]!=0){
								document.getElementById(temp).style.border = "solid #FFA726";
							}else if(str_array[temp+4]!=0){
								document.getElementById(temp).style.border = "solid #BDBDBD";
							}else{
								document.getElementById(temp).style.border = "hidden";
							}
								
							temp = temp+5;
							
						}
						
				}
			});

			}


		function openalarmwindow(id){
			localStorage.setItem("systemid",id);
		    window.open('alarmwindow.do');
			}
		
		
	</script>

</body>
</html>