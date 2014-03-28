<!DOCTYPE html>
<html>
<head>
<script>
	function validate(startDate, endDate) {

		if ((!(/^[0-9 -:.]+$/.test(startDate)))
				|| (!(/^[0-9 -:.]+$/.test(endDate)))) {
			alert("Invalid input characters.");
		} else if (startDate.length != 21 || endDate.length != 21) {
			alert("Invalid input length.");
		}
	}

	function getJSON(url) {

		try {
			var JSONRequest = new XMLHttpRequest();

			JSONRequest.open("GET", url, false);
			JSONRequest.send(null);

			var txt = JSONRequest.responseText;
			var obj = eval("(" + txt + ")");

			var size = obj.length;

			var myTable = "<table style=\'width: 500px\' border=\"1\">";
			myTable += "<tr><td><u>IMSIs</u></td><td><u>Failures</u></td><td><u>Total Duration</u></td></tr>";

			for (var i = 0; i < size; i++) {

				var objects = obj[i];
				var imsi = objects[0];
				var failures = objects[1];
				var duration = objects[2];

				myTable += "<tr><td>" + imsi + "</td><td>" + failures
						+ "</td><td>" + duration + "</td></tr>";
			}
			myTable += "</table>";

			document.getElementById("tablespace").innerHTML = myTable;

		} catch (err) {
			document.getElementById("tablespace").innerHTML = "No record found for inputs submitted";
		}
	}
</script>
</head>

<body>
	<h> <b>Get IMSIs with the number of call failures and their
		total duration between date range.</b></h>
	<hr>

	<div>
		<form name='inputForm'>
			<fieldset>
				<label for="startdatetime">Start DateTime:</label> <input
					type="datetime-local" id="startdatetime" name="startDate">
				<label for="enddatetime">End DateTime:</label> <input
					type="datetime-local" id="enddatetime" name="endDate"> <label
					for="submit"></label> <input type="button" name="submit"
					onclick="getJSON('jaxrs/base_Datas/TotalFailuresDurationForIMSIs/' + document.getElementById('startdatetime').value 
				+ '/' + document.getElementById('enddatetime').value);"
					value="Query" size="20">
			</fieldset>
		</form>
	</div>

	<br>

	<div id="tablespace"></div>

</body>
</html>
