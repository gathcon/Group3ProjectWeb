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
		<form name='inputForm'>
			<fieldset>
			<label for="startdatetime">Start DateTime:</label> 
			<input type="datetime-local" id="startdatetime" name="startDate">
			<label for="enddatetime">End DateTime:</label> 
			<input type="datetime-local" id="enddatetime" name="endDate"> 
			
			<label for="submit"></label> 
			<input type="button" name="submit" onclick="getJSON('jaxrs/base_Datas/IMSIByDateRange/' 
			+ document.getElementById('startdatetime').value 
			+ '/' + document.getElementById('enddatetime').value);" value="Query" size="20">
			</fieldset>
		</form>
	</div>

	<br>

	<div id="tablespace"></div>

</body>
</html>