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

<div class="page-title">
	<h2>
		<span class="fa fa-arrow-circle-o-left"></span> Trouble Ticket
	</h2>
</div>


	<div class="col-md-12">
		
		<form:form action="saveTticket.do" method="post" modelAttribute="ticketsave" class="form-horizontal">

			<form:hidden path="id" id="id" />


			<div class="panel panel-default">
				<div class="panel-body">
					<div class="form-group">
						<label class="col-md-3 col-xs-12 control-label">Comment:</label>
						<div class="col-md-6 col-xs-12">
							<div class="input-group">
								<span class="input-group-addon"></span>
								<form:textarea path="comment" class="form-control" rows="5"
									placeholder="Comment" id="comment"></form:textarea>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 col-xs-12 control-label">Company</label>
						<div class="col-md-6 col-xs-12">
							<div class="input-group">
								<span class="input-group-addon"></span>
								<form:select path="company.id" id="company"
									items="${companyList}" class="form-control"></form:select>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 col-xs-12 control-label">Owner :</label>
						<div class="col-md-6 col-xs-12">
							<div class="input-group">
								<span class="input-group-addon"></span>
								<form:select path="User.id" id="user"
									class="form-control select">
								</form:select>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 col-xs-12 control-label">Status:</label>
						<div class="col-md-6 col-xs-12">
							<div class="input-group">
								<span class="input-group-addon"></span>
								<form:select path="status" class="form-control select">
									<form:option value="1" label="Initiate" />
									<form:option value="2" label="Trasfered" />
									<form:option value="3" label="pending" />
									<form:option value="4" label="can't clear" />
									<form:option value="5" label="Done" />
								</form:select>
							</div>
						</div>
					</div>

					<div class="form-group">
						<label class="col-md-3 col-xs-12 control-label">Department</label>
						<div class="col-md-6 col-xs-12">
							<div class="input-group">
								<span class="input-group-addon"></span>
								<form:select path="department.id" id="department"
									class="form-control select">
								</form:select>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 col-xs-12 control-label">System</label>
						<div class="col-md-6 col-xs-12">
							<div class="input-group">
								<span class="input-group-addon"></span>
								<form:select path="systems.id" id="systems"
									class="form-control select">

								</form:select>
								
							</div>
						</div>
					</div>
				</div>
				<div class="panel-footer">
					<button type="submit" class="btn btn-primary pull-right"
						onclick="sendMail()">Submit</button>
				</div>
			</div>
		</form:form>
	</div>

