$(function() {
		 "use strict"
		 
	$('#col1').mouseenter(function() {
		$('#col1').before(($('#col2')));
	 });
		
	
	console.log($('#logo')); //id
	console.log($('.sbutton')) //class
	console.log($('#col1 a')); //all links which are children of div with id col1
	console.log($('#col2 ul > li')); // all immediate children list items of unordered lists in #col2
	console.log($('ul:first-child')); //the first child of every unordered list
	console.log($('a[href*="elsys"]')); //all links pointing to elsys's domain
	console.log($('#col2 ul > li:nth-child(odd)')); //only the odd elements of ...
	console.log($('p:first-letter')); //the first letter of every paragraph
	console.log($('ul li[rel="bookmark"]')); //all list items in an unordered list that have a specific property
	console.log($('div > #latest')); //direct children of divs that have the 'latest' class
	console.log($('input:not(input[type="email"])')); // input elements that are not for email
	console.log($('p a:only-child')); //links that are the only child of a paragraph
	console.log($('h2+p')); //select the paragraphs preceded by a h2
	console.log($('*')); //select all elements
	console.log($('ul > li::before')); //just before every list item which is a direct child of an unordered list
	
});