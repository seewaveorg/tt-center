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
							<label class="col-sm-2 control-label">Branch</label>
							<div class="col-sm-10">
								<input type="text" id="branch" class="form-control select"
									placeholder="branch name"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">Details</label>
							<div class="col-md-10">
								<textarea id="details" class="form-control" rows="5"
									placeholder="Details"></textarea>
							</div>
						</div>
						
						<div class="form-group text-right m-b-0">
							<input type="button" onclick="saveBranch();" class="btn btn-primary waves-effect waves-light" value="Submit" />
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

function saveBranch(){
	
	var comid = $("#companylist").val();
	var branch = $("#branch").val();
	var details = $("#details").val();

 if(comid !=0){
	 $.ajax({
			contentType : "application/json",
			type : "get",
			url : "saveBranch.do",
			data : {
				"comid" : comid,
				"branch" : branch,
				"details" : details,
			},
			success : function(msg) {

				if (msg == "save") {
					$('#clear').trigger('click');

					toastr["success"]("Branch save successful")

				}else if(msg == "update"){
					$('#clear').trigger('click');
					toastr["success"]("Branch update successful")
				} else {
					toastr["error"] ("Error...! please try again..!")
				}
				
			}
		});
 }else{
	 toastr["warning"] ("warning...! please please select company.!")
 }	
		
}

function clearall() {
	document.getElementById("companylist").value = "0";
}

</script>
