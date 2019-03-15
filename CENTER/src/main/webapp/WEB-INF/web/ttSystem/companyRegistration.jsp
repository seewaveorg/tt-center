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
					<form:form action="saveCompany.do" role="form" method="post" modelAttribute="company" class="form-horizontal">
						<form:hidden path="id" id="id" />
						

						<div class="form-group">
							<label class="col-sm-2 control-label">Name</label>
							<div class="col-sm-10">
								<form:input path="name" class="form-control select"
									placeholder="Company Name" />
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label">Address</label>
							<div class="col-sm-10">
								<form:input path="addressLine1" class="form-control"
									placeholder="Address Line 1" />
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label"></label>
							<div class="col-sm-10">
								<form:input path="addressLine2" class="form-control"
									placeholder="Address Line 2" />
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label"></label>
							<div class="col-sm-10">
								<form:input path="addressLine3" class="form-control"
									placeholder="Address Line 3" />
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">Email</label>
							<div class="col-sm-10">
								<form:input path="email" class="form-control"
									placeholder="Email Address" />
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">Telephone</label>
							<div class="col-sm-10">
								<form:input path="telephone" class="form-control"
									placeholder="Telephone No." />
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">Fax</label>
							<div class="col-sm-10">
								<form:input path="fax" class="form-control select"
									placeholder="Fax No." />
							</div>
						</div>

						
						<div class="form-group text-right m-b-0">
							<button class="btn btn-primary waves-effect waves-light"
								type="submit">Submit</button>
							<button type="reset" class="btn btn-default waves-effect waves-light m-l-5">Cancel</button>
						</div>
					</form:form>
				</div>
			</div>


		</div>
	</div>
</div>
