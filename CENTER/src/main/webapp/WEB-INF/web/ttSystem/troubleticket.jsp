<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<%@ page import="com.ngs.service.SysUser"%>
<%@ page import="java.io.*,java.util.*"%>
<%@ page import="javax.servlet.*,java.text.*"%>


<script type="text/javascript">
	$(document).ready(function() {
		$("#company").change(function() {
			var id = $(this).val();
			var value = 'id=' + id;
			$.ajax({
				type : "get",
				url : "getValuesForSystemDropdowns.do",
				//contentType : 'application/json',
				data : "value=" + value,
				success : function(msg) {
					//$.each(data, function() { //to print name of employee
					$('#systems').html(msg);
					// });

				}
			});

		});

		$("#company").change(function() {
			var id = $(this).val();
			var value = 'id=' + id;
			$.ajax({
				type : "get",
				url : "getValuesForDepartmentDropdowns.do",
				//contentType : 'application/json',
				data : "value=" + value,
				success : function(msg) {
					alert(msg);
					$('#department').html(msg);
				}
			});

		});

		$("#company").change(function() {
			var id = $(this).val();
			var value = 'id=' + id;
			$.ajax({
				type : "get",
				url : "getValuesForUserDropdowns.do",
				//contentType : 'application/json',
				data : "value=" + value,
				success : function(msg) {
					alert(msg);
					$('#user').html(msg);

				}
			});

		});
	});
</script>

<div class="row">
	<div class="col-sm-12">
		<div class="card-box">

			<div class="row">
				<div class="col-lg-10">
					<form:form action="saveTticket.do" role="form" method="post"
						modelAttribute="ticketsave" class="form-horizontal">

						<form:hidden path="id" id="id" />

						<div class="form-group">
							<label class="col-md-2 control-label">Comment</label>
							<div class="col-md-10">
							<form:textarea path="comment" class="form-control" rows="5" placeholder="Comment" id="comment"></form:textarea>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">Company</label>
							<div class="col-sm-10">
								<form:select path="company.id" id="company" items="${companyList}" class="form-control"></form:select>
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">Owner</label>
							<div class="col-sm-10">
								<form:select path="User.id" id="user"  class="form-control select"></form:select>
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">Status</label>
							<div class="col-sm-10">
								<form:select path="status" class="form-control select">
									<form:option value="1" label="Initiate" />
									<form:option value="2" label="Trasfered" />
									<form:option value="3" label="pending" />
									<form:option value="4" label="can't clear" />
									<form:option value="5" label="Done" />
								</form:select>
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">Department</label>
							<div class="col-sm-10">
								<form:select path="department.id"  id="department" class="form-control select"></form:select>
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">System</label>
							<div class="col-sm-10">
							<form:select path="systems.id" id="systems"	class="form-control select"></form:select>
							</div>
						</div>

						<div class="form-group text-right m-b-0">
							<button class="btn btn-primary waves-effect waves-light" type="submit">Submit</button>
							<button type="reset" class="btn btn-default waves-effect waves-light m-l-5"> Cancel</button>
						</div>

					</form:form>
				</div>
				<!-- end col -->
			</div>
			<!-- end row -->
		</div>
	</div>
	<!-- end col -->
</div>
<!-- end row -->


