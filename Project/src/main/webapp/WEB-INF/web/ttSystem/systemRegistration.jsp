<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<%@ page import="com.ngs.service.SysUser"%>

<%@ page import="java.io.*,java.util.*"%>
<%@ page import="javax.servlet.*,java.text.*"%>

<link
	href="<c:url value='/resources/ttsystem/assets/plugins/select2/dist/css/select2.css'/>"
	rel="stylesheet" media="screen">
<link
	href="<c:url value='/resources/ttsystem/assets/plugins/select2/dist/css/select2-bootstrap.css'/>"
	rel="stylesheet" media="screen">




<div class="row">
	<div class="col-sm-12">
		<div class="card-box">

			<div class="row">
				<div class="col-lg-10">
					<form action="" role="form" method="post" class="form-horizontal">

						<input type="text" hidden id="id" value=0 />

						<div class="form-group">
							<label class="col-md-2 control-label">System</label>
							<div class="col-md-10">
								<input type="text" id="name" class="form-control"
									placeholder="system name" />
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-2 control-label">System No</label>
							<div class="col-md-10">
								<input type="text" id="system_no" class="form-control"
									placeholder="system number" />
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">System Brand</label>
							<div class="col-sm-10">
								<form:select path="brandList" id="brand" items="${brandList}"
									class="form-control"></form:select>
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-2 control-label">System Model</label>
							<div class="col-md-10">
								<input type="text" id="model" class="form-control"
									placeholder="system model" />
							</div>
						</div>


						<div class="form-group">
							<label class="col-sm-2 control-label">Configuration Type</label>
							<div class="col-sm-10">
								<select id="configtype" class="form-control">
									<option value="1">DynDNS</option>
									<option value="2">Watchdog</option>
								</select>
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">Company</label>
							<div class="col-sm-10">
								<form:select path="companyList" id="company"
									items="${companyList}" class="form-control">

								</form:select>
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">Branch</label>
							<div class="col-sm-10">
								<select id="branch" class="form-control">

								</select>

							</div>
						</div>



						<div class="form-group">
							<label class="col-md-2 control-label">Location</label>
							<div class="col-md-10">
								<div class="input-group">
									<span class="input-group-btn">
										<button type="button"
											class="btn waves-effect waves-light btn-primary">Longitude</button>
									</span> <input type="text" id="lon" name="example-input2-group2"
										class="form-control" placeholder="ex : 79.893936" /> <span
										class="input-group-btn">
										<button type="button"
											class="btn waves-effect waves-light btn-primary">Latitude</button>
									</span> <input type="text" id="lat" name="example-input2-group2"
										class="form-control" placeholder="ex : 6.961459" />
								</div>
							</div>
						</div>


						<div class="form-group">
							<label class="col-md-2 control-label">Configuration URL</label>
							<div class="col-md-10">
								<input type="text" id="url" class="form-control" placeholder="ex: http://futurenetwork.dyndns.org:8811" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">IP</label>
							<div class="col-md-10">
								<input type="text" id="ip" class="form-control"
									placeholder="ip address" />
							</div>
						</div>


						<div class="form-group">
							<label class="col-md-2 control-label">Details</label>
							<div class="col-md-10">
								<textarea id="details" class="form-control" rows="5"
									placeholder="Details"></textarea>
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-2 control-label">Users</label>
							<div class="col-md-10">
								<select class="select2 select2-multiple" multiple="multiple"
									id="users" multiple data-placeholder="Choose ...">
									<optgroup label="Select Users" id="loaduser">
									</optgroup>
								</select>
							</div>
						</div>
						<div class="form-group text-right m-b-0">
							<input type="button" onclick="systemSave();" class="btn btn-primary waves-effect waves-light" value="Submit" />
							<button id="clear" type="reset" onclick="clearall();" class="btn btn-default waves-effect waves-light m-l-5">Clear</button>

						</div>

					</form>
				</div>
				<!-- end col -->
			</div>
			<!-- end row -->
		</div>
	</div>
	<!-- end col -->
</div>
<!-- end row -->



<div class="row">
	<div class="col-sm-12">
		<div class="card-box table-responsive" id="innerContent">
			<table id="datatable-buttons"
				class="table table-striped table-bordered">
				<thead>
					<tr>
						<th>Com String</th>
						<th>Company</th>
						<th>System</th>
						<th>Shystem Brand</th>
						<th>System Model</th>
						<th>Config Type</th>
						<th>Branch</th>
						<th>Status</th>
						<th>Delete</th>
						<th>Edit</th>
					</tr>
				</thead>
				<tbody id="systemtbl"></tbody>
			</table>


			
		</div>
	</div>
</div>




<script
	src="<c:url value='/resources/ttSystemTemplate/dashboard/assets/plugins/multiselect/js/jquery.multi-select.js' />"></script>
<script
	src="<c:url value='/resources/ttSystemTemplate/dashboard/assets/plugins/jquery-quicksearch/jquery.quicksearch.js' />"></script>
<script
	src="<c:url value='/resources/ttSystemTemplate/dashboard/assets/plugins/select2/dist/js/select2.min.js' />"></script>




<script>
	jQuery(document).ready(function() {
		
		
		// Select2
		$(".select2").select2();

		$(".select2-limiting").select2({
			maximumSelectionLength : 2
		});

		$('#company').on('change', function() {
			
			loadBranch(this.value,0);
			loadusers(this.value,"0");
		})
		loadsystemtbl();
		
		//$("#branch option:selected").text();

	});

	function tmptble() {
		$('#datatable').dataTable();
		$('#datatable-keytable').DataTable({
			keys : true
		});
		$('#datatable-responsive').DataTable();
		$('#datatable-scroller').DataTable({
			ajax : "assets/plugins/datatables/json/scroller-demo.json",
			deferRender : true,
			scrollY : 380,
			scrollCollapse : true,
			scroller : true
		});
		var table = $('#datatable-fixed-header').DataTable({
			fixedHeader : true
		});
		TableManageButtons.init();

	}

	function loadBranch(id,bid) {
		if (id == 0) {
			$('#branch').html(null);
		}

		if (id != 0) {

			$.ajax({
				type : "get",
				url : "getbranch.do",
				data : "companyid=" + id,
				success : function(msg) {

					var stringhtml = "";
					var json = JSON.parse(msg);

					stringhtml += "<option value='0'>Select Branch</option>";

					for (i = 0; i < json.length; i++) {
						//<option value="2">Branch 02</option>
						if(bid.toString() == json[i]["id"]){
							stringhtml += "<option value='"+json[i]["id"]+"' selected=true >"+json[i]["name"]+"</option>";
							
						}else{
							
							stringhtml += "<option value='"+json[i]["id"]+"'>"+json[i]["name"]+"</option>";
						}
						
					}

					$('#branch').html(stringhtml);
				}
			});

		}

	}

	function loadusers(sysid,selusers) {

		
		$.ajax({
			type : "get",
			url : "getusers.do",
			data : "companyid=" + sysid,
			success : function(msg) {

				var stringhtml = "";
				var json = JSON.parse(msg)

				for (i = 0; i < json.length; i++) {

					stringhtml += "<option value='"+json[i]["uid"]+"'>"
							+ json[i]["fname"] + " " + json[i]["lname"]
							+ "</option>";

				}
				
				$('#loaduser').html(stringhtml);
				
				if(selusers!="0"){
					var selectedValues = new Array();
					
					selectedValues = selusers.split(',');
					$.each($("#users"), function(){
			            $(this).select2('val', selectedValues);
			   		 });				

				}
				
			}
		});

	}

	function systemSave() {

		var sysid = $("#id").val();
		var sysname = $("#name").val();
		var sysno = $("#system_no").val();
		var sysbrand = $("#brand").val();
		var sysmodel = $("#model").val();
		var configtype = $("#configtype").val();
		var company = $("#company").val();
		var branch = $("#branch").val();

		var lon = $("#lon").val();
		var lat = $("#lat").val();
		var url = $("#url").val();
		var ip = $("#ip").val();
		var details = $("#details").val();
		
		var users;

		if($("#users").val()!=null){
			var userobj =$("#users").val();
			users = userobj.toString();
			}else{
			users = "null"	
			}
		
		$.ajax({
			type : "get",
			url : "saveSystemwithuser.do", //this is my servlet
			data : {
				"sysid" : sysid,
				"sysname" : sysname,
				"sysno" : sysno,
				"sysbrand" : sysbrand,
				"sysmodel" : sysmodel,
				"configtype" : configtype,
				"company" : company,
				"branch" : branch,
				"lon" : lon,
				"lat" : lat,
				"url" : url,
				"ip" : ip,
				"details" : details,
				"users" : users
			},
			success : function(msg) {
				var str_array = msg.split(':');

				var string = str_array[0];
				var id = str_array[1];
				
				if (string == "save") {
					$('#clear').trigger('click');
					toastr["success"]("System registration successful")
					toastr["info"]("Community String is : "+id)

				}else if(string == "update"){
					$('#clear').trigger('click');
					toastr["success"]("System Configuration update successful")
					toastr["info"]("Community String is : "+id)
				} else {
					toastr["error"] ("System registration error.please try again")
				}
				
			}
		});

	}

	function clearall() {
		document.getElementById("company").value = "0";
		$('#branch').html("");
		$('#users').val('').trigger("change");
	}

	function systemtbl() {
			 loadsystemtbl();
	}
	
	function enabledisable(id,status){
		var sts;
		if(status == 1){
			sts = 3;
		}else if(status == 3){
			sts = 1;
		}
		$.ajax({
			contentType : "application/json",
			type : "get",
			url : "sysenabledisable.do",
			data : "id="+id+"&sts="+sts,
			success : function(msg) {

				if(msg =="0"){

				}else if(msg =="logout"){

				}else{
					var btn = "";
					if(sts == 3){
						btn = "<a  onclick='enabledisable("+id+ "\,"+sts+"\);' class='btn btn-danger buttons-copy buttons-html5 btn-sm' >disable</a>";
					}else if(sts == 1){
						btn = "<a  onclick='enabledisable("+id+ "\,"+sts+"\);' class='btn btn-success buttons-copy buttons-html5 btn-sm' >enable</a>";
					}	
					
					$('#sts'+id).html(btn);
					
					
				}
			}

		});
	}

	function deletesys(id,status){
		$.ajax({
			contentType : "application/json",
			type : "get",
			url : "sysdelete.do",
			data : "id="+id,
			success : function(msg) {

				if(msg =="0"){
					
				}else if(msg =="logout"){
					
				}else{		
					$('#row'+id).html("");
				}
			}
		});	
	}


	function systeminfo(id,status){ 
		// update


		$.ajax({
			contentType : "application/json",
			type : "get",
			url : "selectsysteminfoforup.do",
			data : "id="+id,
			success : function(msg) {

				if(msg =="0"){
					
				}else if(msg =="logout"){
					
				}else{	
					
					
				var json = JSON.parse(msg);
									
				var configvl="";
				if(json[0].configType=="DynDNS"){
					configvl = "1";
				}else{
					configvl = "2";
				}
				    

					
					
					$("#id").val(json[0].id);
					$("#name").val(json[0].sys);
					$("#system_no").val(json[0].sysno);
					document.getElementById('brand').value=json[0].sysbrand;
					$("#model").val(json[0].sysmodel);
					document.getElementById('configtype').value=configvl;
					document.getElementById('company').value=json[0].companyId;
					loadBranch(json[0].companyId,json[0].branch); // load branch and select
					
					$("#lon").val(json[0].lon);
					$("#lat").val(json[0].lat);
					$("#url").val(json[0].url);
					$("#ip").val(json[0].ip);
					$("#details").val(json[0].details);

					loadusers(json[0].companyId,json[0].users);
													
				}
			}

		});	
		
	}

	function selectbranch(id){
		document.getElementById('branch').value=id;
	}
	
function loadsystemtbl(){
	$.ajax({
		contentType : "application/json",
		type : "get",
		url : "loadsystemtbl.do",
		success : function(msg) {

			var json = JSON.parse(msg);
			var tbl = "";
			for (var i = 0; i < json.length; i++) {
				var status ="";
				if(json[i].status == 1){
					status = "<a  onclick='enabledisable("+json[i].id+ "\,"+json[i].status+"\);' class='btn btn-success buttons-copy buttons-html5 btn-sm' >enable</a>";
				}else if(json[i].status==3){
					status = "<a  onclick='enabledisable("+json[i].id+"\,"+json[i].status+"\);' class='btn btn-danger buttons-copy buttons-html5 btn-sm' >disable</a>";
				}

				var del = "<a onclick='deletesys("+json[i].id+ "\,"+json[i].status+ "\);' class='btn btn-danger buttons-copy buttons-html5 btn-sm' >Deletes</a>";

				var info = "<a onclick='systeminfo("+json[i].id+ "\,"+json[i].status+ "\);' class='btn btn-primary buttons-copy buttons-html5 btn-sm' >Edit</a>";
				var no = i+1
					
				tbl = tbl 
				+"<tr id='row"+json[i].id+"'>"
					+"<td>"+json[i].id+"</td>"
					+"<td>"+json[i].com+"</td>"
					+"<td>"+json[i].sys+"</td>"
					+"<td>"+json[i].sysbrand+"</td>"
					+"<td>"+json[i].sysmodel+"</td>"
					+"<td>"+json[i].config+"</td>"
					+"<td>"+json[i].branch+"</td>"
					+"<td id='sts"+json[i].id+"'>"+status+"</td>"
					+"<td>"+del+"</td>"
					+"<td>"+info+"</td>"
				+"</tr>";
			}
			$('#systemtbl').html(tbl);
			tmptble();
		}
	});


	
}


	
</script>
