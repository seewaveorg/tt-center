<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<%@ page import="com.ngs.service.SysUser"%>
<%@ page import="java.io.*,java.util.*"%>
<%@ page import="javax.servlet.*,java.text.*"%>


<div class="page-title">
	<h2>
		<span class="fa fa-arrow-circle-o-left"></span> Events
	</h2>
</div>

<!-- PAGE CONTENT WRAPPER -->
<div class="page-content-wrap">

	<div class="col-md-12">

		<form:form method="post" modelAttribute="alarm" action="saveAlarm.do"
			class="form-horizontal">
			<form:hidden path="id" id="id" />
			<div class="panel panel-default">


				<div class="panel-body">

					<div class="form-group">
						<label class="col-md-3 col-xs-12 control-label">Event</label>
						<div class="col-md-6 col-xs-12">
							<div class="input-group">
								<span class="input-group-addon"></span>
								<form:input path="alarm" class="form-control"
									placeholder="Event" />
							</div>
						</div>
					</div>

					<div class="form-group">
						<label class="col-md-3 col-xs-12 control-label">Details</label>
						<div class="col-md-6 col-xs-12">
							<div class="input-group">
								<span class="input-group-addon"></span>
								<form:textarea path="details" class="form-control" rows="5"
									placeholder="Comment" />
							</div>
						</div>
					</div>										
					
					<!--  -->
					<div class="form-group">
						<label class="col-md-3 col-xs-12 control-label">Type</label>
						<div class="col-md-6 col-xs-12">
							<div class="input-group">
								<span class="input-group-addon"></span>
								<form:select path="types.id" id="type" items="${typelist}"
									class="form-control"></form:select>
							</div>
						</div>
					</div>

					<div class="form-group">
						<label class="col-md-3 col-xs-12 control-label">System</label>
						<div class="col-md-6 col-xs-12">
							<div class="input-group">
								<span class="input-group-addon"></span>
								<form:select path="systems.id" id="type" items="${systemList}"
									class="form-control"></form:select>
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-3 col-xs-12 control-label">Severity</label>
						<div class="col-md-6 col-xs-12">
							<div class="input-group">
								<span class="input-group-addon"></span>
								<form:select path="severity" class="form-control select">
									<form:option value="Critical">Critical</form:option>
									<form:option value="Emergency">Emergency</form:option>
									<form:option value="Normal">Normal</form:option>
								</form:select>
							</div>
						</div>
					</div>
					<div class="panel-footer">

						<button type="submit" class="btn btn-primary pull-right">Submit</button>
					</div>
				</div>
		</form:form>
	</div>
</div>