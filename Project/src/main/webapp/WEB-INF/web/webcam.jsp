<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ page session="true"%>
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
 <html>
 <head>
  <csrf disabled="true"/>
  
     <meta charset="UTF-8">
     <title>Capturing Images with HTML5</title>
     
     <script type="text/javascript">
 
 var video = document.getElementById('videoID');
 var canvas = document.getElementById('canvasID');
 var context = canvas.getContext('2d');
 
 window.URL = window.URL || window.webkitURL;
 navigator.getUserMedia = navigator.getUserMedia || navigator.webkitGetUserMedia || 
                          navigator.mozGetUserMedia || navigator.msGetUserMedia;


 navigator.getUserMedia({
	 video : true
	 }, function(stream) {
	 video.src = window.URL.createObjectURL(stream);
	 }, function(e) { 
		 
		 
	 });

	
 
 	function capture() 
	 {
	 context.drawImage(video, 0, 0, canvas.width, canvas.height);
	 };


	 
	 function send()
	 {
	 var imageData =  canvas.toDataURL();
	 var personid = <%=person %>;
	 var xmlhttp = new XMLHttpRequest();
	 xmlhttp.open("POST", "saveImage.do?personid="+personid, true);
	 xmlhttp.send(imageData);
	 alert("Image upload Successfully Completed ");
	 
	 };


	
	
 </script>
	<!-- 
	/** 
	*xmlhttp.open("GET", "saveImage.do?image="+imageData+" & personid=<%=person %>", true);  */
	xmlhttp.open("POST", "saveImage.do", true);
	 -->
     
        
 </head>
  
 <body> 
 <input type="hiden" id="personID" value="${personID}" />
 <h4> Person ID is: <%= person %></h4>
 <div><video id="videoID" autoplay style="border: 1px solid black;"></video></div>
 <div><canvas id="canvasID" style="border: 1px solid black;"></canvas></div>
 <div>
 <input type="button" value="Take photo" onclick="capture()" 
 style="width: 200px; height: 30px;"/>
     <input type="button" value="Send" onclick="send()" 
          style="width: 200px; height: 30px;"/>
 </div>
   

</body>

</html>
