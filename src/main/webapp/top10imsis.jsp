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
			

			myTable += "<tr><td>" + imsi + "</td><td>" + failures + "</td></tr>";
		}
		myTable += "</table>";

		document.getElementById("tablespace").innerHTML = myTable;

	} catch (err) {
		document.getElementById("tablespace").innerHTML = "No record found for inputs submitted";
	}
		
	}

	function processData(){

		if ( validate(  document.getElementById('startdatetime').value, document.getElementById('enddatetime').value) ){
			alert('You entered invalid content');
		} else {
			getJSON('jaxrs/base_Datas/top10ImsiFailureCount/'  + document.getElementById('startdatetime').value + '/' + document.getElementById('enddatetime').value);
		}
	}

    // /top10ImsiFailureCount/{startDate}/{endDate}

	function validate(startDate, endDate) {
				if(startDate == ' ' || endDate == ' '){
					return true;
				}
				
				else if ((!(/^[0-9 -:.]+$/.test(startDate))) || (!(/^[0-9 -:.]+$/.test(endDate)))) {
					return true;
				} 

				else if (startDate.length != 19 || endDate.length != 19) {
					return true;

				} else {
					return false;
				}

	}	
</script>
</head>

<body>
	 <b>Get the top ten imsis with failures between date range.</b>
	<br>

	<div>
	
			
			<label for="startDate">Start
			Date</label>
		<input id="startdatetime" name="startDate" type="text"
			value="YYYY-MM-DD HH:mm:ss"> <br> 
			
			<label for="endDate">End
			Date</label>
			<input id="enddatetime" name="endDate" type="text"
			value="YYYY-MM-DD HH:mm:ss"> 
	</div>

	<button type="button"
		onclick="processData()">Query DB</button>

	<br>
	
	
	
	<br>
	<div id="tablespace"></div>
	
	
</body>
</html>