$(function() {
	'use strict'
	
	//README
	//This variable is used in tasks 13+. Set it according
	//to the jsonplaceholder you are using for running this.
	//I am running one on my box (as instructed).
	var jsonHost = "http://localhost:3000/posts/";


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

	//task 9
	t4lia.click(function() {
		alert('Hello, world!');
	});

	//task 10
	var t10swapped = false; 
	t4lia.click(function() {
		var firstcol = $('#col1');
		var secondcol = $('#col2');
		if(!t10swapped) {
			secondcol.after(firstcol);
			t10swapped = true;
		}
		else {
			firstcol.after(secondcol);
			t10swapped = false;
		}
	});

	//task 11
	
	function appendToList(list, text) {
		var item = $('<li/>');
		item.text(text);
		list.append(item);
	}

	function processPosts(posts) {
		var i = 0;
		$.each(posts, function() {
			appendToList(t8list, this.title);
			if(++i >= 5) {
				return false;
			}
		});
	}

	$.ajax(jsonHost, {
			method: "GET"
	}).then(processPosts);

	// task 12
	//t7btn.click(function() {
	//	if(t6inp.val() == "") {
	//		alert("you must enter text");
	//	}
	//});

	//task 13, 14, 15

	function addPost(post) {
		var btnDelete = $('<button/>');
		btnDelete.text('X');
		btnDelete.click(function() {
			alert('deleting');
		});
		
		var listItem = $('<li/>');
		listItem.text(post.title + '   ');
		listItem.append(btnDelete);
		t8list.append(listItem);	
	}

	t7btn.click(function() {
		if(t6inp.val() == "") {
			alert("you must enter text");
		}
		else {
			$.ajax(jsonHost, {
  				method: 'POST',
  				data: {
    				title: t6inp.val(),
    				body: 'bar',
   					userId: 1
  				}
			}).then(function(data) {
				$.ajax(jsonHost + data.id, {
					method: 'GET'
				}).then(function(post) {
					addPost(post);	
				});
			});
		};
	});
});

