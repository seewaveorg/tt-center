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
					<form action="" role="form" method="post" class="form-horizontal">
						
						<div class="form-group">
							<label class="col-sm-2 control-label">Company</label>
							<div class="col-sm-10">
								<form:select path="companyList" id="company" items="${companyList}" class="form-control">

								</form:select>
							</div>
						</div>
						
						
						<div class="form-group">
							<label class="col-sm-2 control-label">Title</label>
							<div class="col-sm-10">
								<select id="title" class="form-control">
									<option value="Mr">Mr</option>
									<option value="Mrs">Mrs</option>
									<option value="Miss">Miss</option>
								</select>
							</div>
						</div>


						<div class="form-group">
							<label class="col-sm-2 control-label">Initials</label>
							<div class="col-sm-10">
								<input type="text" id="initials" class="form-control" placeholder="Initials" />
								
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">First Name</label>
							<div class="col-sm-10">
								<input type="text" id="fname" class="form-control" placeholder="First Name" />
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">Last Name</label>
							<div class="col-sm-10">
								<input type="text" id="lname" class="form-control" placeholder="Last Name" />
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">Date of Birth</label>
							<div class="col-sm-10">
								<input type="text" id="dob" class="form-control" placeholder="format: {MM-dd-yyyy} example: 09-18-1993" />
							</div>
						</div>


						<div class="form-group">
							<label class="col-sm-2 control-label">Gender</label>
							<div class="col-sm-10">
								<select id="gender" class="form-control">
									<option value="male">Male</option>
									<option value="female" >Female</option>
								</select>
							</div>
						</div>


						<div class="form-group">
							<label class="col-sm-2 control-label">Address</label>
							<div class="col-sm-10">
								<input type="text" id="add1" class="form-control" placeholder="Address Line 1" />
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label"></label>
							<div class="col-sm-10">
								<input type="text" id="add2" class="form-control" placeholder="Address Line 2" />
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label"></label>
							<div class="col-sm-10">
								<input type="text" id="add3" class="form-control" placeholder="Address Line 3" />
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">NIC</label>
							<div class="col-sm-10">
								<input type="text" id="nic" class="form-control" placeholder="nic number" />
							</div>
						</div>


						<div class="form-group">
							<label class="col-sm-2 control-label">Mobile</label>
							<div class="col-sm-10">
								<input type="text" id="mobile" class="form-control" placeholder="format{ 94XXXXXXXXX }   ex : 94714481873 " />
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">Email</label>
							<div class="col-sm-10">
								<input type="text" id="email" class="form-control" placeholder="Email Address" />
							</div>
						</div>

						<div class="form-group text-right m-b-0">
							<input type="button" onclick="userSave();" class="btn btn-primary waves-effect waves-light" value="Submit" />
							<button type="reset" id="clear" class="btn btn-default waves-effect waves-light m-l-5"> Cancel</button>
						</div>
					</form>
				</div>
			</div>
			
			
		</div>
	</div>
</div>


<script>





function userSave(){
	var comid = $("#company").val();
	var title = $("#title").val();
	var initials = $("#initials").val();
	var fname  = $("#fname").val();
	var lname = $("#lname").val();
	var dob = $("#dob").val();
	var gender = $("#gender").val();
	var add1 = $("#add1").val();
	var add2 = $("#add2").val();
	var add3 = $("#add3").val();
	var nic = $("#nic").val();
	var mobile = $("#mobile").val();
	var email = $("#email").val();


	if(comid == 0){
		toastr["warning"]("Please Select Company")
	}else{
	
	$.ajax({
		type : "get",
		url : "UserReg.do", //this is my servlet
		data : {
			"comid" : comid,
			"title" : title,
			"initials" : initials,
			"fname" : fname,
			"lname" : lname,
			"dob" : dob,
			"add1" : add1,
			"add2" : add2,
			"add3" : add3,
			"nic" : nic,
			"mobile" : mobile,
			"email" : email
		},
		success : function(msg) {
			if(msg == "save"){
				$('#clear').trigger('click');
				toastr["success"]("User Registration Successful ")
				
			}else if(msg == "not save"){
				toastr["warning"]("Something Wrong..Please try again..!")
			}else if(msg == "error"){
				toastr["error"]("User Registrtion Error.Please try again later..!")
			}
			
		}
	});
	
	}
	
}


</script>