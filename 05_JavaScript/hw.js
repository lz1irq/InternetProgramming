$(function() {
	'use strict'
	
	//task 2
	console.log($('.tu'));
	//alternatively - '#footer > a', etc...
	
	//task 3
	console.log($('#col1 > p').text());

	//task 4
	var t4list = $('#menu-top-level-menu');
	var t4li = $('<li/>');

	var t4lia = $('<a/>');
	t4lia.text('new button');
	t4lia.attr('href', '#');
	
	t4li.append(t4lia);
	t4list.append(t4li);
});