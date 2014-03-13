<!DOCTYPE html>
<html>
<head>
<script>
function myFunction()
{
var name = document.getElementById("userName").value;
document.getElementById("demo").innerHTML=name;
}
</script>
</head>

<body>

<h1>My Web Page</h1>

<p id="demo">A Paragraph.</p>

<div>
				<input id="userName" name="name" type="text"
				value="Name"> <label for="userName">User Name
				</label> 
				<input id="password" name="password" type="text"
				value="password"> <label for="password">password
				</label>
</div>

<button type="button" onclick="myFunction()">Try it</button>

</body>
</html> 
