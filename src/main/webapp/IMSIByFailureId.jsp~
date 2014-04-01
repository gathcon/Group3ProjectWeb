<!DOCTYPE html>
<html>
<head>
<script>

	function processData(){

		var selector = document.getElementById("failureSelector");
		var failureElement = selector.options[selector.selectedIndex].text.split(":");	
		var failureClass = failureElement[0];
		
		getJSON('jaxrs/base_Datas/IMSIByFailureId/' 
				+ failureClass);
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
			myTable += "<tr><td><u>Affected IMSIs</u></td></tr>";

			for (var i = 0; i < size; i++) {

				var imsi = obj[i].imsi;
				
				myTable += "<tr><td>" + imsi
				+ "</td></tr>";
			}
			myTable += "</table>";

			document.getElementById("tablespace").innerHTML = myTable;

		} catch (err) {
			document.getElementById("tablespace").innerHTML = "No records found for failure class submitted.";
		}
	}
</script>
</head>

<body>
	<h><b>Get IMSIs by failure class.</b></h>
	<hr>

	<div>
		<form name='inputForm'>
			<fieldset>
			<select id="failureSelector">
			  <option value="0">0:EMERGENCY</option>
			  <option value="1">1:HIGH PRIORITY ACCESS</option>
			  <option value="2">2:MT ACCESS</option>
			  <option value="3">3:MO SIGNALLING</option>
			  <option value="4">4:MO DATA</option>
			</select>	
			
			<label for="submit"></label> 
			<input type="button" name="submit" onclick="processData();" value="Query" size="20">
			</fieldset>
		</form>
	</div>

	<br>

	<div id="tablespace"></div>

</body>
</html>