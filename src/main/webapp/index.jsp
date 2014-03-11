<!DOCTYPE html>
<html>
<head>
 	<link rel="stylesheet" type="text/css" href="jquery.datetimepicker.css"/>
</head>
<body>
    <form id="mydateform" action="jaxrs/base_Datas" title="" method="post">
        <div>
      		<input id="datetimepicker" type="text" >
        </div>
        
        <div>
            <input type="submit" id="submitButton"  name="submitButton" value="Submit">
        </div>
 	</form>
 

<script src="jquery-1.11.0.js"></script>
<script src="jquery.datetimepicker.js"></script>
<script type='text/javascript'>$('#datetimepicker').datetimepicker();</script>
</body>
</html>