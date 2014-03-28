<!DOCTYPE html>
<html>
<head>
<script>
	function validate(startDate, endDate) {

		if ((!(/^[0-9 -:.]+$/.test(startDate)))
				|| (!(/^[0-9 -:.]+$/.test(endDate)))) {
			alert("Invalid input characters for date input.");
		} else if (startDate.length != 21 || endDate.length != 21) {
			alert("Invalid input length for date input.");
		}
	}

	function getJSON(url) {

		try {
			var JSONRequest = new XMLHttpRequest();

			JSONRequest.open("GET", url, false);
			JSONRequest.send(null);

			var txt = JSONRequest.responseText;
			var obj = eval("(" + txt + ")");

			document.getElementById("msg").innerHTML = "The number of failures for this phone model";
			document.getElementById("count").innerHTML = obj;

		} catch (err) {
			document.getElementById("msg").innerHTML = "No record found for inputs submitted.";
		}
	}
</script>
</head>

<body>
	<b>Get the count of failures for a phone model between date range.</b>
	<br>

	<div>
		<label for="model">Phone Model</label> <input id="model" name="model"
			type="text" value=""> <br> <label for="startDate">Start
			Date</label> <input id="startdatetime" name="startDate" type="text"
			value="YYYY-MM-DD HH:mm:ss"> <br> <label for="endDate">End
			Date</label> <input id="enddatetime" name="endDate" type="text"
			value="YYYY-MM-DD HH:mm:ss">
	</div>

	<button type="button"
		onclick="validate(document.getElementById('startdatetime').value, document.getElementById('enddatetime').value);
			getJSON('jaxrs/base_Datas/FailureCountByModelAndByDateRange/' + document.getElementById('model').value + '/' 
			+ document.getElementById('startdatetime').value + '/' 
			+ document.getElementById('enddatetime').value);">Query</button>
	<br>
	<p id="msg"></p>
	<p id="count"></p>

</body>
</html>