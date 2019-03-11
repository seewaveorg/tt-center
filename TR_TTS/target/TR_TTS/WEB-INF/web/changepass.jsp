<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>


	<!-- Bootstrap -->
        <link href="<c:url value='/resources/bootstrap/css/bootstrap.min.css' />" rel="stylesheet" media="screen">
        <link href="<c:url value='/resources/bootstrap/css/bootstrap-responsive.min.css' />" rel="stylesheet" media="screen">
		<link href="<c:url value='/resources/assets/styles.css' />" rel="stylesheet" media="screen">
 	 <!-- conflict with date picker - <script src="<c:url value='/resources/js/jquery-1.11.3.min.js' />"></script>  -->



<!-- requirement for data picker field -->

<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" />
<!--  <script src="http://code.jquery.com/jquery-1.9.1.js"></script> -->
<script src="<c:url value='/resources/js/jquery-1.11.js' />"></script>
<!--  <script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script> -->
<script src="<c:url value='/resources/js/jquery-ui.js' />"></script>

<!-- End of date picker requirements  -->


<!--  My new date time picker -->

		<!--  <script type="text/javascript" src="public/javascript/jquery-1.11.1.js"></script> -->
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

        <script type="text/javascript" src="<c:url value='/resources/zebra-datetime-picker/examples/libraries/syntaxhighlighter/public/javascript/XRegExp.js' />"></script>
        <script type="text/javascript" src="<c:url value='/resources/zebra-datetime-picker/examples/libraries/syntaxhighlighter/public/javascript/shCore.js' />"></script>
        <script type="text/javascript" src="<c:url value='/resources/zebra-datetime-picker/examples/libraries/syntaxhighlighter/public/javascript/shLegacy.js' />"></script>
        <script type="text/javascript" src="<c:url value='/resources/zebra-datetime-picker/examples/libraries/syntaxhighlighter/public/javascript/shBrushJScript.js' />"></script>
        <script type="text/javascript" src="<c:url value='/resources/zebra-datetime-picker/examples/libraries/syntaxhighlighter/public/javascript/shBrushXml.js' />"></script>

<style>
	#searchCell {

  	}
  	#searchDiv {
    border-radius: 25px;
    border: 2px solid #8AC007;
    padding: 20px;
 	margin-left: 50px;
    top: 0px;
    left: 5px ;
    width: 500px;
    height: 400px;
	}

    .error { 
        color: red; font-weight: italic; 
    }
</style>



  
   <script type="text/javascript">
      
      $(document).ready(function() {
 
          
          //check the availability of the suer name
          $('#username').keyup(function(){  
              //run the character number check  
              if($('#username').val().length < min_chars){  
                  //if it's bellow the minimum show characters_error text '  
                  $('#msg').html(characters_error);  
              }else{  
                  //else show the cheking_text and run the function to check  
                  $('#searchDiv').html(checking_html);  
                  check_login_availability("username",$('#username').val());  
              }  
          });  
    
         
  		// form submission
		
	
  		
       
       
      });  // clossing jquery document ready
      
      

      

      
      

      
 
      
  	//Get person details 
	function GetPersonFromId(personID){  
  				
     			var result ="";
		        $.ajax({
	                   
                type: "get",
                url: "GetPersonLogin.do", //this is my servlet
                async: false,
                // dataType: 'json',
                data: "personID="+personID,
                success: function(msg){  
                		result = msg;
                },
                error: function (xhr, ajaxOptions, thrownError) {
   		     	 //$('#searchDiv').html(xhr.status + "   " + thrownError);
   		     	 alert(xhr.status + "   " + thrownErro);
   		     	}
            });
		   // alert('result is '+result);
		 return result; 
	}  
  	
  	
  	//Get person details 
	function GetLoginFromId(loginID){  
  				
     			var result ="";
		        $.ajax({
	                   
                type: "get",
                url: "GetLoginStringByID.do", //this is my servlet
                async: false,
                // dataType: 'json',
                data: "loginID="+loginID,
                success: function(msg){  
                		result = msg;
                },
                error: function (xhr, ajaxOptions, thrownError) {
   		     	 //$('#searchDiv').html(xhr.status + "   " + thrownError);
   		     	 alert(xhr.status + "   " + thrownErro);
   		     	}
            });
		   // alert('result is '+result);
		 return result; 
	}  
  	
  	
      
  	//function to check username availability  
		function check_availability(attribute, parameter){  
		  
		        //get the username  
		        //var parameter = $('#firstname').val();  
		        //var attribute = "firstname";
		       // alert(attribute+" and "+parameter);
		        $.ajax({
	                   
                  type: "get",
                  url: "GetPersonTable.do", //this is my servlet
                  // dataType: 'json',
                  data: "attrib="+attribute+"&param="+parameter,
                  success: function(msg){   
                  	 $('#searchDiv').html(msg);
                  	 //$("#loading").hide();
                  	
                  	// $("#decoder").val(" Ready To accept Data Requests ");
                  }
              });
		  
  	}  
  	

	  	//function to check username availability  
		function check_login_availability(attribute, parameter){  
		  
		        //get the username  
		        //var parameter = $('#firstname').val();  
		        //var attribute = "firstname";
		       // alert(attribute+" and "+parameter);
		        $.ajax({
	                   
                  type: "get",
                  url: "CheckUserNameAvailability.do", //this is my servlet
                  // dataType: 'json',
                  data: "attrib="+attribute+"&param="+parameter,
                  success: function(msg){   
                  	 $('#searchDiv').html(msg);
                  	 //$("#loading").hide();
                  	
                  	// $("#decoder").val(" Ready To accept Data Requests ");
                  }
              });
		  
  	}  
  	
  	function saveChangePass(){
  		 
    		var id = $('#id').attr('value');
    		var newp = $('#new').val();
    		var oldpr = $('#old').val();
    		var newpr = $('#newr').val();
    		var msg = "Current password mismatch, Check again !";
    		//alert('id is '+id+' oldpr '+oldpr+"  newp "+newp);
    		//if(oldp == oldpr ){ this is not relevent due to bcrypt encoding
    			if(newp == newpr){
    				$.ajax({
            			url: 'changePassword.do',
            			//data: $('#submitForm').serialize(),
            			contentType: "application/json; charset=utf-8",
            			data: { "id":id, "oldpr":oldpr, "newp":newp},
            			type: 'get',
            			success: function(msg){   
            				//alert('Successfully saved');
                         	$('#container').html(msg);
                         	msg = 'Password Successfully changed';
            			},
            			error: function (xhr, ajaxOptions, thrownError) {
            		     	 //$('#searchDiv').html(xhr.status + "   " + thrownError);
            				$('#msg').val("Error : "+xhr.status + "   " + thrownError);
            		     }
        		});
    			}else{
    				alert('New passwords are not matched ');
    				msg = 'New passwords are not matched ';
    			}
    			
    		
    			 $('#msg').html(msg);
  			     $('#msg').show();
  			     $('#msg').delay(10000).fadeOut('slow');
    	
    			
	
  		
  		
  	}

		

	
   </script>
          

      
      
	<form id="submitForm" >


		<table width="960" border="0" cellspacing="1" cellpadding="1">
  		<caption>
    	<input type="hidden" value="${loginid}" id="id" />
    	<!--  <input type="hidden"  id ="oldpass" value="${password}" />  -->
  		</caption>

	
  <tr>
    <td>&nbsp;</td>
    <td>User Name </td>
    <td><input type="text" value="${username}" size="30"  readonly/> <br> </td>
  
    
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td> Old Pass Word </td>
    <td><input type="password" id="old" size="30" /><br> </td>
    
  </tr>
   <tr>
    <td>&nbsp;</td>
    <td> New PassWord </td>
   <td><input type="password" id="new" size="30" /><br></td>
  </tr>
  
   </tr>
   <tr>
    <td>&nbsp;</td>
    <td> Retype New Password</td>
   <td><input type="password" id="newr" size="30" /><br>
    </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td></td>
    <td><label>
      	<div align="right">
     
        <!--   <input type="submit" name="Submit" value="Save" /> -->
        <input type="button" id="login_save" name="login_save" value="Change Pass Word" onClick="saveChangePass()" />
        </div>
    </label></td>
    <td></td>
  </tr>
</table>

</form>


  