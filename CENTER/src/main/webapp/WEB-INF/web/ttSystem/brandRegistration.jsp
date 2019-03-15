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
						<input type="text" hidden id="id" value=0 />
												
					
						<div class="form-group">
							<label class="col-sm-2 control-label">Name</label>
							<div class="col-sm-10">
								<input type="text" id="name" class="form-control select"
									placeholder="brand name"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label">Number</label>
							<div class="col-sm-10">
								<input type="text" id="number" class="form-control select"
									placeholder="brand number"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-md-2 control-label">Comment</label>
							<div class="col-md-10">
							<textarea class="form-control" rows="5" placeholder="comment about brand" id="comment"></textarea>
							</div>
						</div>
						
						
						<div class="form-group text-right m-b-0">
							<input type="button" onclick="saveBrand();" class="btn btn-primary waves-effect waves-light" value="Submit" />
							<button id="clear" type="reset" class="btn btn-default waves-effect waves-light m-l-5">Cancel</button>
						</div>
					</form>
				</div>
			</div>
			
			
		</div>
	</div>
</div>

<script>

function saveBrand(){
	var id = $("#id").val();
	var name = $("#name").val();
	var number = $("#number").val();
	var comment = $("#comment").val();
	$.ajax({
		type : "get",
		url : "saveBrand.do",
		data : {
			"id" : id,
			"name" : name,
			"number" : number,
			"comment" : comment,
		},
		success : function(msg) {

			if (msg == "save") {
				$('#clear').trigger('click');

				toastr["success"]("System save successful")

			}else if(msg == "update"){
				$('#clear').trigger('click');
				toastr["success"]("Brand update successful")
			} else {
				toastr["error"] ("Error...! please try again..!")
			}
			
		}
	});


	
	

	
}

</script>
