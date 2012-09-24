$(document).ready(function(){
    $(".singleTab").hover(function(){
          $(this).animate({'width': '350px'}, 300);
          $(this).children("#firstTabText").fadeIn(1000);
        },function(){
          $(this).stop(true).animate({'width': '200px'}, 300);
          $(this).children("#firstTabText").fadeOut(1000);
          $(this).children("#firstTabText").stop(true).fadeOut(1000);
    });

    $("#helpDiv *").tooltip({
	track: true,
	delay: 0,
	showURL: false,
	extraClass: "right"
    });
});






