jQuery.fn.shake = function(interval, distance, times) {
	interval = typeof interval == "undefined" ? 100 : interval;
	distance = typeof distance == "undefined" ? 10 : distance;
	times = typeof times == "undefined" ? 3 : times;
	var jTarget = $(this);
	jTarget.css('position', 'relative');
	for (var iter = 0; iter < (times + 1); iter++) {
		jTarget.animate({
			left: ((iter % 2 == 0 ? distance : distance * -1))
		}, interval);
	}
	return jTarget.animate({
		left: 0
	}, interval);
}

$(".current_password, .new_password, .new_password_verify").blur(function() {
	$(".validation_pwLeave").text("");
});

function validatePassword() {

	const getPwd = RegExp(/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,30}$/);

	if ($(".current_password").val() == "") {
		alert("현재 비밀번호를 입력해주세요.");
		$(".current_password").focus();
		return false;
	}
	if ($(".new_password").val() == "") {
		alert("새 비밀번호를 입력해주세요.");
		$(".new_password").focus();
		return false;
	}
	if ($(".new_password_verify").val() == "") {
		alert("새 비밀번호를 확인해주세요.");
		$(".new_password_verify").focus();
		return false;
	}
	
	if (!getPwd.test($(".new_password").val())) {
		$(".validation_pwLeave").text("올바르지 않은 비밀번호입니다");
		$(".new_password").val("");
		$(".new_password_verify").val("");
		$(".new_password").focus();
		$(".validation_pwLeave").shake();
		return false;
	}
	if ($(".current_password").val() == $(".new_password").val()) {
		$(".validation_pwLeave").text("기존의 비밀번호와 동일합니다");
		$(".new_password").val("");
		$(".new_password_verify").val("");
		$(".new_password").focus();
		$(".validation_pwLeave").shake();
		return false;
	}
	if ($(".new_password").val() != $(".new_password_verify").val()) {
		$(".validation_pwLeave").text("비밀번호가 일치하지 않습니다");
		$(".new_password_verify").val("");
		$(".new_password_verify").focus();
		$(".validation_pwLeave").shake();
		return false;
	}

}