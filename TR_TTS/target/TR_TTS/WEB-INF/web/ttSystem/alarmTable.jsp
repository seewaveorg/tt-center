<style type="text/css">
.pagination li {
	display: inline-block;
	margin-right: 3px;
}

.pagination li a {
	border-radius: 4px;
	color: #768399;
	-moz-border-radius: 4px;
	-webkit-border-radius: 4px;
}
</style>

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


<div class="page-title">
	<h2>
		<span class="fa fa-arrow-circle-o-left"></span> Alarms
	</h2>
</div>
<div class="panel panel-default">
	<div class="panel-body">
		<!-- <<<<< Table Goes Hear >>>> -->
		<div id='innerContent'>
			<form id="table">${table}</form>
			<!-- ########################## pagination ######################################### -->
			<lable>${pagination}</lable>
			<div class="panel-footer">

				<button type="button" class="btn btn-primary pull-right"
					id="btnGetAll" onclick="loadMe();">Submit</button>
			</div>

		</div>

	</div>

</div>




