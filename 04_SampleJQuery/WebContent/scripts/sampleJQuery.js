$(function() {
	"use strict"
	
	var list = $('#dynamicList');
	var addButton = $('#addButton');
	var delButton = $('#delButton');
	
	addButton.click(function() {
		list.append('<li>Product number ' + Math.trunc(Math.random()*10000) + '</li>');
	});
	
	delButton.click(function() {
		list.children().last().remove();
	});
});