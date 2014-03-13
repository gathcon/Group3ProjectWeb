<!DOCTYPE html>
<html>
<head>
<script src="jquery-1.11.0.js"></script>
</head>

<body>
	<h> <b>Get IMSIs with failures between date range.</b></h>
	<hr>

	<form id="myDateRangeForm"
		onsubmit="location.href='jaxrs/base_Datas/IMSIByDateRange/' + 
			document.getElementById('startdatetimepicker').value + '/' + document.getElementById('enddatetimepicker').value; return false;"
		title="" method="get">
		<div>
			<label for="startDate">Start Date</label> 
				<input id="startdatetimepicker" name="startDate" type="text"
				value="YYYY-MM-DD HH:mm:ss.S"> 
				
			<label for="endDate">End Date</label> 
				<input id="enddatetimepicker" name="endDate" type="text"
				value="YYYY-MM-DD HH:mm:ss.S">
		</div>

		<div>
			<input type="submit" id="submitButton" name="submitButton"
				value="Query">
		</div>
	</form>

</body>
</html>