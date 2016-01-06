//= require jquery
//= require bootstrap


var form;
var imageData = [];
var submittedParams = {};
var urls = [];

function checkImageLoadStatus() {
	var allImagesAreLoaded = true;
	$.each(
		imageData, 
		function(i, x) {
			if (typeof x == "undefined") { allImagesAreLoaded = false; }
		}
	);


	if (allImagesAreLoaded) {
		var input = document.createElement("input");
		input.name = "imageData";
		input.type = "hidden";
		input.value = imageData.join(",");
		form.appendChild(input);

		submittedParams.imageData = imageData;

		form.submit();
		form.remove();

		populateSubmittedParams();
	}
}

function createForm() {
	form = document.createElement("form");
	form.action = "/stitch/home/stitch"
	form.method = "post";
	form.target = "_blank";
	$("body").append(form);

	var input = document.createElement("input");
	input.name = "outputFormat";
	input.type = "hidden";
	input.value = $("#outputFormat").val();
	form.appendChild(input);

	submittedParams.outputFormat = input.value;	


	return form;
}

function getImageData(url, index) {
	var img = document.createElement("img");
	img.onload = function() {
		var canvas = document.createElement("canvas");
		canvas.height = $(this).height();
		canvas.width = $(this).width();
		$("body").append(canvas);
		$("body").append("<br>");

		var context = canvas.getContext("2d");
		context.drawImage(this, 0, 0);
		imageData[index] = canvas.toDataURL().replace(/\S+,/, "");

		this.remove();
		canvas.remove();
		checkImageLoadStatus();
	}
	$("body").append(img);
	$("body").append("<br>");
	img.src = "/stitch/home/proxy?url=" + url;

}

function populateSubmittedParams() { $("#submittedParams").html("<pre>" + JSON.stringify(submittedParams, null, "\t") + "</pre>"); }

function stitch() {
	$("#submittedParams").html("");

	urls = [];
	$.each(
		$("#imageUrls").val().split("\n"),
		function(i, x) {
			if (x != "") { urls.push(x); }
		}
	);

	if ($("#imageUrlLabel").hasClass("active")) { submitUrls(urls); }
	else { submitData(urls); }
}



function submitData(urls) {
	createForm();

	imageData = new Array(urls.length);
	$.each(urls, function(i, x) { getImageData(x, i); });
}

function submitUrls(urls) {
	createForm();

	var input = document.createElement("input");
	input.name = "imageUrls";
	input.type = "hidden";
	input.value = urls.join(",");
	form.appendChild(input);

	submittedParams.imageUrls = urls;

	form.submit();
	form.remove();

	populateSubmittedParams();
}

