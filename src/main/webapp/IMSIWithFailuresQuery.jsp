<!DOCTYPE html>
<html>
<head>
<script>

	function getJSON(url) {

		var JSONRequest = new XMLHttpRequest();

		JSONRequest.open("GET", url, false);
		JSONRequest.send(null);

		//document.write("JSON Response Text =" + JSONRequest.responseText);
		var txt = JSONRequest.responseText;
		var obj = eval("(" + txt + ")");
		var size = obj.length;
		
		var imsi = obj[0].imsi;

		document.getElementById("objects").innerHTML = obj;
		document.getElementById("imsi").innerHTML = imsi;
		document.getElementById("allText").innerHTML = txt;	
		document.getElementById("size").innerHTML = size;
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
	document.getElementById('startdatetime').value + '/' + document.getElementById('enddatetime').value)">Do
		Stuff</button>

	<br>
	
	<!-- <div>
	<table id="resultstable" style="width: 500px">
		<tr>
			<td>Failure Date Time</td>
			<td>Failure IMSI</td>
		</tr>
	</table>
	</div> -->
	
	<br>
	<p id="objects">Objects</p>
	<p id="imsi">IMSI:</p>
	<p id="allText">Date Time:</p>
	<p id="size">Size</p>	
	
</body>
</html>