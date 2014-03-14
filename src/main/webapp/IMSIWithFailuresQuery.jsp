<!DOCTYPE html>
<html>
<head>
<script>
	function getJSON(url) {

		try{
		var JSONRequest = new XMLHttpRequest();

		JSONRequest.open("GET", url, false);
		JSONRequest.send(null);

		var txt = JSONRequest.responseText;
		var obj = eval("(" + txt + ")");

		var size = obj.length;

		var myTable = "<table style=\'width: 500px\' border=\"1\">";
		myTable += "<tr><td><u>IMSIs</u></td></tr>";

		for (var i = 0; i < size; i++) {
			var imsi = obj[i].imsi;
			myTable += "<tr><td>" + imsi + "</td></tr>";
		}
		myTable += "</table>";

		document.getElementById("tablespace").innerHTML = myTable;

		}
		catch(err){
			document.getElementById("tablespace").innerHTML = "No record found for date range submitted ";
			}
	}
</script>
</head>

<body>
	<h> <b>Get IMSIs with failures between date range.</b></h>
	<hr>

	<div>
		<input id="startdatetime" name="startDate" type="text"
			value="YYYY-MM-DD HH:mm:ss.S"> <label for="startDate">Start
			Date</label> <input id="enddatetime" name="endDate" type="text"
			value="YYYY-MM-DD HH:mm:ss.S"> <label for="endDate">End
			Date</label>
	</div>

	<button type="button"
		onclick="getJSON('jaxrs/base_Datas/IMSIByDateRange/' + 
	document.getElementById('startdatetime').value + '/' + document.getElementById('enddatetime').value)">Query</button>

	<br>

	<div id="tablespace"></div>

</body>
</html>