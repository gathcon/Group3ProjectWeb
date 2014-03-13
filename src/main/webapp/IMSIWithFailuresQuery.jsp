<!DOCTYPE html>
<html>
<head>
<script language="javascript">

		function getJSON(url) {
			
			var JSONRequest = new XMLHttpRequest();
			
			JSONRequest.open( "GET", url, false );
			JSONRequest.send( null );
			
			var txt = JSONRequest.responseText;
			var obj = eval ("(" + txt + ")");

			var imsi = obj[1].imsi;
			
			document.getElementById("demo").innerHTML=imsi;	
			document.getElementById("datetime").innerHTML=txt;			
		} 
				
</script>	
</head>

<body>
	<h> <b>Get IMSIs with failures between date range.</b></h>
	<hr>

<div>
	<input id="startdatetime" name="startDate" type="text"
	value="YYYY-MM-DD HH:mm:ss.S"> 
	<label for="startDate">Start Date</label>
	
	<input id="enddatetime" name="endDate" type="text"
	value="YYYY-MM-DD HH:mm:ss.S">
	<label for="endDate">End Date</label> 
</div>

<button type="button" onclick="getJSON('jaxrs/base_Datas/IMSIByDateRange/' + 
	document.getElementById('startdatetime').value + '/' + document.getElementById('enddatetime').value)">Do Stuff</button>	

	<p id="demo">A Paragraph.</p>	
	
	<p id="imsi">
		IMSI:
	</p>
	<p id="datetime">
		Date Time:
	</p>

</body>
</html>