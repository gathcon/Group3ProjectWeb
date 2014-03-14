<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EventID and Cause Code by IMSI</title>
<script>

			function getJSON(url) {

				try{
				var JSONRequest = new XMLHttpRequest();
			
				JSONRequest.open("GET", url, false);
				JSONRequest.send(null);
			
				//document.write("JSON Response Text =" + JSONRequest.responseText);
				var txt = JSONRequest.responseText;
				var obj = eval("(" + txt + ")");
				var size = obj.length;





				var myTable = "<table style=\'width: 500px\' border=\"1\">";
				myTable += "<tr><td><u>Event ID</u></td><td><u>Cause Code</u></td></tr>";

				for (var i = 0; i < size; i++) {

					var eventId = obj[0].id.eventId;
					var causeCode = obj[0].id.causeCode;

					myTable += "<tr><td>" + eventId + "</td><td>" + causeCode + "</td></tr>";
				}
				myTable += "</table>";

				document.getElementById("tablespace").innerHTML = myTable;

				}

				catch(err){

					document.getElementById("tablespace").innerHTML = "No record found for Imsi submitted ";

					}




				


				
			/* 
				document.getElementById("objects").innerHTML = obj;
				document.getElementById("allText").innerHTML = txt;	
				document.getElementById("size").innerHTML = size; 
				document.getElementById("eventId").innerHTML = eventId;
				document.getElementById("causeCode").innerHTML = causeCode;*/
				
			}

</script>
</head>
<body>

	<!-- Old Form Method 

<form method="get" onsubmit="location.href='jaxrs/base_Datas/eventID_CauseCodeByImsi/' + document.getElementById('myInput').value; return false;">
  <input type="text" id="myInput" />
  <input type="submit" />
</form>

-->



	<!-- Eoins button and text box Method -->

	<h> <b>Get EventId and Cause Codes associated with the given
		Imsi.</b></h>
	<hr>

	<div>
		<input id="imsiInput" name="imsi" type="text"> <label
			for="imsi">Imsi</label>

	</div>

	<button type="button"
		onclick="getJSON('jaxrs/base_Datas/eventID_CauseCodeByImsi/' + document.getElementById('imsiInput').value)">Submit
		Query</button>

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
	<!-- <label for="objects">objects:</label><p id="objects">Objects</p>
	<label for="allText">allText:</label><p id="allText">allText</p>
	<label for="size">size:</label><p id="size">Size</p>  -->

	<label for="eventId">Event Id:</label>
	<p id="eventId"></p>
	<label for="causeCode">Cause Code:</label>
	<p id="causeCode"></p>
	<div id="tablespace"></div>


</body>
</html>