var views = [];
var datas = [];
var pageReady = false;

function fill(view, data) {
	if (!pageReady) {
		var i = views.length;
		views[i] = view;
		datas[i] = data;
		return;
	}
	var template = $("#" + view + " .template:first");
	for ( var r = 0; r < data.length; r++) {
		for ( var f = 0; f < data[r].length; f++) {
			$(template).insertAfter(data[r][f]);
		}
	}
}

$(document).ready(function() {
	pageReady = true;
	for ( var i = 0; i < views.length; i++) {
		fill(views[i], data[i]);
	}
});
