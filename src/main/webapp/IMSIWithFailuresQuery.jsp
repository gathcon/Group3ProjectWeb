<!DOCTYPE html>
<html>
<head>
<script>
	function validate(startDate, endDate) {

		if ((!(/^[0-9 -:.]+$/.test(startDate))) || (!(/^[0-9 -:.]+$/.test(endDate)))) {
			alert("Invalid input characters.");
		} 
		else if (startDate.length != 21 || endDate.length != 21) {
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

			var myTable = "<table style=\'width: 300px\' border=\"1\">";
			myTable += "<tr><td><u>IMSIs</u></td></tr>";

			for (var i = 0; i < size; i++) {
				var imsi = obj[i].imsi;
				myTable += "<tr><td>" + imsi + "</td></tr>";
			}
			myTable += "</table>";

			document.getElementById("tablespace").innerHTML = myTable;

		} catch (err) {
			document.getElementById("tablespace").innerHTML = "No record found for inputs submitted.";
		}
	}
</script>
</head>

<body>
	<h> <b>Get IMSIs with failures between date range.</b></h>
	<hr>

	<div>
		<label for="startDate">Start Date</label> <input id="startdatetime"
			name="startDate" type="text" value="YYYY-MM-DD HH:mm:ss.S"> <label
			for="endDate">End Date</label> <input id="enddatetime" name="endDate"
			type="text" value="YYYY-MM-DD HH:mm:ss.S">
	</div>

	<button type="button"
		onclick="validate(document.getElementById('startdatetime').value, document.getElementById('enddatetime').value);
			getJSON('jaxrs/base_Datas/IMSIByDateRange/' + document.getElementById('startdatetime').value 
			+ '/' + document.getElementById('enddatetime').value);">Query</button>

	<br>

	<div id="tablespace"></div>

</body>
</html>