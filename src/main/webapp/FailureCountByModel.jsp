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
		<form name='inputForm'>
			<fieldset>
				<label for="model">Phone Model</label> 
				<input id="model" name="model" type="text" value=""> <br> 
				<label for="startdatetime">Start DateTime:</label> 
				<input type="datetime-local" id="startdatetime" name="startDate">
				<label for="enddatetime">End DateTime:</label> 
				<input type="datetime-local" id="enddatetime" name="endDate"> 
				<label for="submit"></label> 
				<input type="button" name="submit" onclick="getJSON('jaxrs/base_Datas/FailureCountByModelAndByDateRange/' 
						+ document.getElementById('model').value + '/' 
						+ document.getElementById('startdatetime').value + '/' 
						+ document.getElementById('enddatetime').value);"
					value="Query" size="20">
			</fieldset>
		</form>
	</div>

	<br>
	<p id="msg"></p>
	<p id="count"></p>

</body>
</html>