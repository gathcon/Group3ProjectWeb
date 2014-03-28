<!DOCTYPE html>
<html>
<head>
<script>
	function getJSON(url) {

		try {
			var JSONRequest = new XMLHttpRequest();

			JSONRequest.open("GET", url, false);
			JSONRequest.send(null);

			var txt = JSONRequest.responseText;
			var obj = eval("(" + txt + ")");

			var size = obj.length;

			var myTable = "<table style=\'width: 500px\' border=\"1\">";
			myTable += "<tr><td><u>IMSIs</u></td><td><u>Failure Count</u></td></tr>";

			for (var i = 0; i < size; i++) {

				var objects = obj[i];
				var imsi = objects[0];
				var failures = objects[1];

				myTable += "<tr><td>" + imsi + "</td><td>" + failures
						+ "</td></tr>";
			}
			myTable += "</table>";

			document.getElementById("tablespace").innerHTML = myTable;

		} catch (err) {
			document.getElementById("tablespace").innerHTML = "No record found for inputs submitted";
		}
	}

	function processData() {

		if (validate(document.getElementById('startdatetime').value, document
				.getElementById('enddatetime').value)) {
			alert('You entered invalid content');
		} else {
			getJSON('jaxrs/base_Datas/top10ImsiFailureCount/'
					+ document.getElementById('startdatetime').value + '/'
					+ document.getElementById('enddatetime').value);
		}
	}

	function validate(startDate, endDate) {
		if (startDate == ' ' || endDate == ' ') {
			return true;
		} else {
			return false;
		}
	}
	
</script>
</head>

<body>
	<b>Get the top ten IMSIs with failures between date range.</b>
	<br>

	<div>
		<form name='inputForm'>
			<fieldset>				
			<label for="startdatetime">Start DateTime:</label> 
			<input type="datetime-local" id="startdatetime" name="startDate"> 
			<label for="enddatetime">End DateTime:</label> 
			<input type="datetime-local" id="enddatetime" name="endDate">
			
			<label for="submit"></label> 
			<input type="button" name="submit" onclick="processData()" value="Query" size="20">
			</fieldset>
		</form>
	</div>

	<br>
	<div id="tablespace"></div>

</body>
</html>