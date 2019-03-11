
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

		
		$('.table').off("click").on("click", ".table", function() {

		});
		
		$(".table").on('click','tr',function() {
			
					var tt = $(this).attr('value');

					$.ajax({

						type : "get",
						url : "getTtUpdate.do",
						data : "value=" + tt,
						success : function(msg) {
							//$('#innerContent').html(msg);
							var w = window.open('', '','width=800,height=500, scrollbars=yes');
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

<div class="page-title">
	<h2>
		<span class="fa fa-arrow-circle-o-left"></span> Trouble Tickets
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

			</div>
		</div>
	</div>
</div>