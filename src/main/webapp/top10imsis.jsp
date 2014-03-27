<!DOCTYPE html>
<html>
<head>
<script>

	function getJSON(url) {

		var JSONRequest = new XMLHttpRequest();

		JSONRequest.open("GET", url, false);
		JSONRequest.send(null);

		document.write("JSON Response Text =" + JSONRequest.responseText);
		//var txt = JSONRequest.responseText;
		//var obj = eval("(" + txt + ")");
		
		
		

		//document.getElementById("msg").innerHTML = "The number of failures for this imsi";
		//document.getElementById("count").innerHTML = obj;
		
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
		onclick="getJSON('jaxrs/base_Datas/top10ImsiFailureCount/'  + document.getElementById('startdatetime').value + '/' + document.getElementById('enddatetime').value)">Query DB</button>

	<br>
	
	
	
	<br>
	<p id="msg"></p>
	<p id="count"></p>
	
	
</body>
</html>