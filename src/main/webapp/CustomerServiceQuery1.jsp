<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EventID and Cause Code by IMSI</title>
<script>
	function validate(imsi) {

		if ((!(/^[0-9 -:.]+$/.test(imsi)))) {
			alert("Invalid input characters.");
		} else if (imsi.length != 15) {
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
			myTable += "<tr><td><u>Event ID</u></td><td><u>Cause Code</u></td></tr>";

			for (var i = 0; i < size; i++) {

				var eventId = obj[0].id.eventId;
				var causeCode = obj[0].id.causeCode;

				myTable += "<tr><td>" + eventId + "</td><td>" + causeCode
						+ "</td></tr>";
			}
			myTable += "</table>";

			document.getElementById("tablespace").innerHTML = myTable;

		}

		catch (err) {

			document.getElementById("tablespace").innerHTML = "No record found for Imsi submitted ";

		}
	}
</script>
</head>
<body>

	 <b>Get EventId and Cause Codes associated with the given
		Imsi.</b></h>
	<hr>

	<div>
		<input id="imsiInput" name="imsi" type="text" pattern="[0-9]{15}"
			title="15 digit numbers only input required"> <label
			for="imsi">Imsi</label>

	</div>

	<button type="button"
		onclick="getJSON('jaxrs/base_Datas/eventID_CauseCodeByImsi/' + document.getElementById('imsiInput').value); validate(document.getElementById('imsiInput').value)">Submit
		Query</button>

	<br>

	<br>

	<div id="tablespace"></div>


</body>
</html>