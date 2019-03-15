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

			<div class="row">
				<div class="col-lg-10">
					<form:form action="saveAlarm.do" role="form" method="post" modelAttribute="alarm" class="form-horizontal">

						<form:hidden path="id" id="id" />

						<div class="form-group">
							<label class="col-md-2 control-label">Event</label>
							<div class="col-md-10">
							<form:input path="alarm" class="form-control" placeholder="event" />
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-2 control-label">Details</label>
							<div class="col-md-10">
								<form:textarea path="details" class="form-control" rows="5" placeholder="Comment" id="comment"></form:textarea>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">Type</label>
							<div class="col-sm-10">
								<form:select path="types.id" id="type" items="${typelist}"
									class="form-control"></form:select>
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">System</label>
							<div class="col-sm-10">
								<form:select path="systems.id" id="type" items="${systemList}"
									class="form-control"></form:select>
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">Severity</label>
							<div class="col-sm-10">
								<form:select path="severity" class="form-control select">
									<form:option value="Critical">Critical</form:option>
									<form:option value="Emergency">Emergency</form:option>
									<form:option value="Normal">Normal</form:option>
								</form:select>
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


