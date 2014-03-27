<!DOCTYPE html>
<html>
<head>
<script>
	function validate(failureId) {

		if ((!(/^[0-9]+$/.test(failureId)))) {
			alert("Invalid input characters.");
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
			myTable += "<tr><td><u>Failure Description</u></td><td><u>Affected IMSIs</u></td></tr>";

			for (var i = 0; i < size; i++) {

				var description = obj[i].failure.description;
				var imsi = obj[i].imsi;
				
				myTable += "<tr><td>" + description + "</td><td>" + imsi
				+ "</td></tr>";
			}
			myTable += "</table>";

			document.getElementById("tablespace").innerHTML = myTable;

		} catch (err) {
			document.getElementById("tablespace").innerHTML = "No record found for failure class submitted.";
		}
	}
</script>
</head>

<body>
	<h><b>Get IMSIs by failure class.</b></h>
	<hr>

	<div>
		<label for="failureId">Failure Class</label> <input id="failureId"
			name="failureClass" type="text">  
	</div>

	<button type="button"
		onclick="validate(document.getElementById('failureId').value);
			getJSON('jaxrs/base_Datas/IMSIByFailureId/' + document.getElementById('failureId').value);">Query
	</button>
	<br>

	<div id="tablespace"></div>

</body>
</html>