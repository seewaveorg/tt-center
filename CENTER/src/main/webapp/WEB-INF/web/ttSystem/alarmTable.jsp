
<div class="row">
	<div class="col-sm-12">
		<div class="card-box table-responsive" id="innerContent">
			<table id="datatable-buttons"
				class="table table-striped table-bordered">
				<thead>
					<tr>
						<th>No</th>
						<th>Event</th>
						<th>Type</th>
						<th>Originated Time</th>
						<th>Cleared Time</th>
						<th>Severity</th>
						<th>System</th>
						<th id="status">Status</th>
						<th>O</th>
					</tr>
				</thead>
				<tbody id="tblebody">${table}
				</tbody>
			</table>


			<div class="form-group text-right m-b-0">
				<button class="btn btn-primary waves-effect waves-light"
					onclick="loadMe();" type="submit">Submit</button>
			</div>
		</div>
	</div>
</div>


<script type="text/javascript">
	$(document).ready(function() {
		tmptble();
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
		$("#status").click();
		$("#status").click();

	}
</script>

<script type="text/javascript">
	function loadMe() {
		//$("#btnGetAll").click(function(){

		var alarm = [];

		$.each($("input[name='alarmcheck']:checked"), function() {

			alarm.push($(this).val());
		});

		$.ajax({

			type : "get",
			url : "getArray.do",
			data : "value=" + alarm,
			success : function(msg) {
				$('#innerContent').html(msg);
			}
		});
	}

	function loadOnClick(param) {
		$("#msg").val(" New page loading ");
		$.ajax({

			type : "get",
			url : "GetAlarmsWithPagination.do", //lode pages
			data : "size=15&param=" + param,
			success : function(msg) {
				$('#table').html(msg);
			}
		});

	}
</script>

