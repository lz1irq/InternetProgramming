$(function() {
	'use strict'
	
	//globally useful elements
	var footer = $('#footer');

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

	//task 5
	var t5div = $('<div/>').attr('id', 'dynamiccontent');
	footer.prepend(t5div);

	//task 6
	var t6inp = $('<input/>');
	t6inp.attr('id', 'textinput');
	t5div.append(t6inp);

	//task 7
	var t7btn = $('<button/>');
	t7btn.attr('id', 'addbutton').text('Add');
	t5div.append(t7btn);

	//task 8
	var t8list = $('<ul/>');
	t8list.attr('id', 'posts');
	t5div.append(t8list);
});
