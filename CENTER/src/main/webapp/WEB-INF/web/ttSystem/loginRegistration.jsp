<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<%@ page import="com.ngs.service.SysUser"%>

<%@ page import="java.io.*,java.util.*"%>
<%@ page import="javax.servlet.*,java.text.*"%>


<div class="row">
	<div class="col-sm-12">
		<div class="card-box">

			<div class="row">
				<div class="col-lg-10">
					<form method="post" class="form-horizontal">
												

						<div class="form-group">
							<label class="col-sm-2 control-label">Company</label>
							<div class="col-sm-10">
								<select id="companylist" class="form-control">
									
								</select>
							</div>
						</div>
						
						
						<div class="form-group">
							<label class="col-sm-2 control-label">User</label>
							<div class="col-sm-10">
								<select id="userList" class="form-control">
									
								</select>
							</div>
						</div>
						
						
						<div class="form-group">
							<label class="col-sm-2 control-label">Username</label>
							<div class="col-sm-10">
								<input type="text" id="username" class="form-control select"
									placeholder="username"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label">Password</label>
							<div class="col-sm-10">
								<input type="password" id="password" class="form-control select"
									placeholder="password"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label">Confirm Password</label>
							<div class="col-sm-10">
								<input type="password" id="cpassword" class="form-control select"
									placeholder="confirm password"/>
							</div>
						</div>
						
						
						
						<div class="form-group text-right m-b-0">
							<input type="button" onclick="saveLogin();" class="btn btn-primary waves-effect waves-light" value="Submit" />
							<button id="clear" onclick="clearall();" type="reset" class="btn btn-default waves-effect waves-light m-l-5">Cancel</button>
						</div>
					</form>
				</div>
			</div>
			
			
		</div>
	</div>
</div>

<script>
jQuery(document).ready(function() {
	loadCompnylist();	
	
	$('#companylist').on('change', function() {
		loadUserlist(this.value);
	});
	

});


function loadCompnylist() {
		$.ajax({
			contentType : "application/json",
			type : "get",
			url : "getcompnylist.do",
			success : function(msg) {
				var stringhtml = "";
				var json = JSON.parse(msg);
				stringhtml += "<option value='0'>Select Compnay</option>";
						for (var i = 0; i < json.length; i++) {	
						stringhtml += "<option value='"+json[i]["id"]+"'>"+json[i]["name"]+"</option>";
						}
				$('#companylist').html(stringhtml);		
				}

				
		});


}


function loadUserlist(id) {
	if (id == 0) {
		$('#userList').html(null);
	}
	
	if(id != 0){
		$.ajax({
			contentType : "application/json",
			type : "get",
			url : "getUserlistByid.do",
			data : "company=" + id,
			success : function(msg) {

				var stringhtml = "";
				var json = JSON.parse(msg);

				stringhtml += "<option value='0'>Select User</option>";

				for (i = 0; i < json.length; i++) {
					//<option value="2">Branch 02</option>
					
						
						stringhtml += "<option value='"+json[i]["id"]+"'>"+json[i]["name"]+"</option>";
					
				}

				$('#userList').html(stringhtml);
			}
		});
			
		
	}

	
	

}


function saveLogin(){
	
	var uid = $("#userList").val();
	var username = $("#username").val();
	var password = $("#password").val();
	var cpassword = $("#cpassword").val();


	if(password == cpassword){
		$.ajax({
			contentType : "application/json",
			type : "get",
			url : "saveLogin.do",
			data : {
				"uid" : uid,
				"username" : username,
				"password" : password,
			},
			success : function(msg) {

				if (msg == "save") {
					$('#clear').trigger('click');

					toastr["success"]("User Login save successful")

				}else if(msg == "update"){
					$('#clear').trigger('click');
					toastr["success"]("User Login update successful")
				} else {
					toastr["error"] ("Error...! please try again..!")
				}
				
			}
		});
			

		
	}else{
		toastr["error"] ("Error...! please check your password again..!")
	}

}

function clearall() {
	document.getElementById("companylist").value = "0";
	$('#userList').html("");
}

</script>
