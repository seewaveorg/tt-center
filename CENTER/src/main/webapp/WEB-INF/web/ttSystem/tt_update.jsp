
<script type="text/javascript">
	function loadMe() {

		$('.table').off("click").on("click", ".table", function() {

		});

		$(".table").on(
				'click',
				'tr',
				function() {

					var tt = $(this).attr('value');

					$.ajax({

						type : "get",
						url : "getTtUpdate.do",
						data : "value=" + tt,
						success : function(msg) {
							//$('#innerContent').html(msg);
							var w = window.open('', '',
									'width=800,height=500, scrollbars=yes');
							w.document.write(msg);

						}

					});
				});
	}

	function loadOnClick(param) {
		$("#msg").val(" New page loading ");
		$.ajax({

			type : "get",
			url : "GetTtWithPagination.do", //lode pages
			data : "size=15&param=" + param,
			success : function(msg) {
				$('#table').html(msg);
			}
		});

	}
</script>

<div class="row">
	<div class="col-sm-12">
		<div class="card-box table-responsive" id="innerContent">
			<table id='datatable-buttons'
				class='table table-striped table-bordered'>
				<thead>
					<tr>
						<th>No</th>
						<th>Comment</th>
						<th>Current Owner</th>
						<th>Date time</th>
					</tr>
				</thead>
				<tbody id="tblebody">${table}</tbody>
			</table>

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
		

	}
</script>



