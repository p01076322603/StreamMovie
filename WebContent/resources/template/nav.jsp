<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<script>
	$(function() {
		var currentPosition = parseInt($(".bubble").css("top"));
		$(window).scroll(function() {
			var position = $(window).scrollTop();
			$(".bubble").stop().animate({
				"top" : position + currentPosition + "px"
			}, 750);
		});
	});

	function scrollUp() {
		$("html, body").animate({
			scrollTop : 0
		}, 1000);
	}
	function scrollDown() {
		$("html, body").animate({
			scrollTop : $(document).height() - $(window).height() - 180
		}, 1000);
	}
</script>
<nav>
  <div class="bubble">
    <a href="javascript:history.back();"><i class="fas fa-arrow-left"></i></a> 
    <i class="fas fa-arrow-up" onclick="scrollUp();" style="cursor: pointer;"></i> 
    <i class="fas fa-arrow-down" onclick="scrollDown();" style="cursor: pointer;"></i>
  </div>
</nav>