<!DOCTYPE html>
<html>
<head>
<script>

	function getJSON(url) {

		var JSONRequest = new XMLHttpRequest();

		JSONRequest.open("GET", url, false);
		JSONRequest.send(null);

		var txt = JSONRequest.responseText;
		var obj = eval("(" + txt + ")");
		
		document.getElementById("msg").innerHTML = "The number of failures for this imsi";
		document.getElementById("count").innerHTML = obj;
		
	}

	function processData(){

		if (validate( document.getElementById('imsi').value) ){
			alert('You entered invalid content');
		} else {
			getJSON('jaxrs/base_Datas/imsifailure/' + 
					document.getElementById('imsi').value + '/' + document.getElementById('startdatetime').value + '/' + document.getElementById('enddatetime').value);
		}
	}

	function validate(imsi) {
				if(imsi == ' '){
					return true;
				}
				else if(imsi.match(/^[0-9]+$/) == null){
					return true;
				} else {
					return false;
				}
	}	
	
</script>
</head>

<body>
	<b>Get the count of failures for an imsi between date range.</b>
	<br>

	<div>
		<form name='inputForm'>
			<fieldset>
				<label for="imsi">IMSI</label> <input id="imsi" name="imsi"
					type="text" value=""> <br> <label for="startdatetime">Start
					DateTime:</label> <input type="datetime-local" id="startdatetime"
					name="startDate"> <label for="enddatetime">End
					DateTime:</label> <input type="datetime-local" id="enddatetime"
					name="endDate"> <label for="submit"></label> <input
					type="button" name="submit" onclick="processData()" value="Query"
					size="20">
			</fieldset>
		</form>
	</div>

	<br>
	<p id="msg"></p>
	<p id="count"></p>

</body>
</html>