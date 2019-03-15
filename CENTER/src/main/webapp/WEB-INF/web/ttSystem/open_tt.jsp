<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<%@ page import="com.ngs.service.SysUser"%>

<%@ page import="java.io.*,java.util.*"%>
<%@ page import="javax.servlet.*,java.text.*"%>


<div class="row">
	<div class="col-sm-12">
		<div class="card-box">
			<h4 class="header-title m-t-0 m-b-30">Open Trouble Ticket</h4>
			<div class="row">
				<div class="col-lg-10">
					<form:form action="saveTticket.do" role="form" method="post"
						modelAttribute="ticketsave" class="form-horizontal">

						<form:hidden path="id" id="id" />


						<div class="form-group">
							<label class="col-md-2 control-label">Comment</label>
							<div class="col-md-10">
								<form:textarea path="comment" class="form-control" rows="5"
									placeholder="comment"></form:textarea>
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">Owner</label>
							<div class="col-sm-10">
								<form:select path="User.id" id="user" items="${userList}"
									class="form-control"></form:select>
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
							<label class="col-sm-2 control-label">System</label>
							<div class="col-sm-10">
								<form:select path="systems.id" id="system" items="${systemList}"
									class="form-control"></form:select>
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">Company</label>
							<div class="col-sm-10">
								<form:select path="company.id" id="type" items="${companyList}"
									class="form-control"></form:select>
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">Department</label>
							<div class="col-sm-10">
								<form:select path="department.id" id="type"
									items="${departmentList}" class="form-control"></form:select>
							</div>
						</div>



						<div class="form-group text-right m-b-0">
							<button class="btn btn-primary waves-effect waves-light"
								type="submit">Submit</button>
							<button type="reset"
								class="btn btn-default waves-effect waves-light m-l-5">
								Cancel</button>
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



