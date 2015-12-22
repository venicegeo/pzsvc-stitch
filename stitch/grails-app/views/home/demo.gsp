<!DOCTYPE html>
<html>
	<title>Stitch - Piazza (Service)</title>
	<head>
		<asset:stylesheet src = "demo.css"/>
	</head>
	<body>
		<h1>Stitch</h1>

		Image URLs: <br>
		<textarea id = "imageUrls" rows = "10" style = "width: 100%">https://googledrive.com/host/0Bz9BVmvDh0packRZcm4tSndGSU0/01.jpg
https://googledrive.com/host/0Bz9BVmvDh0packRZcm4tSndGSU0/02.jpg
https://googledrive.com/host/0Bz9BVmvDh0packRZcm4tSndGSU0/03.jpg
https://googledrive.com/host/0Bz9BVmvDh0packRZcm4tSndGSU0/04.jpg
https://googledrive.com/host/0Bz9BVmvDh0packRZcm4tSndGSU0/05.jpg
https://googledrive.com/host/0Bz9BVmvDh0packRZcm4tSndGSU0/06.jpg
https://googledrive.com/host/0Bz9BVmvDh0packRZcm4tSndGSU0/07.jpg
https://googledrive.com/host/0Bz9BVmvDh0packRZcm4tSndGSU0/08.jpg</textarea>
	
		<br><br>

		Output Format:
		<select id = "outputFormat">
			<g:each in = "${["avi", "gif", "mov", "pdf", "wmv"]}">
				<option value = "${it}">${it.toUpperCase()}</option>
			</g:each>
		</select>
	
		<br><br>
	
		Submit Data Via:
		<div class = "btn-group" data-toggle = "buttons">
			<label class = "btn btn-primary active" id = "imageUrlLabel">
				<input id = "imageUrlRadio" name = "group" type = "radio">Image URLs
			</label>
			<label class = "btn btn-primary" id = "imageDataLabel">
				<input id = "imageDataRadio" name = "group" type = "radio">Image Data (base 64)
			</label>
		</div>
	
		<br><br>
		
		<button class = "btn btn-primary" onclick = stitch()>Create!!!</button>

		<br><br>
		
		Submitted Parameters:
		<div id = "submittedParams"></div>
	
	
		<asset:javascript src = "demo.js"/>
	</body>
</html>
