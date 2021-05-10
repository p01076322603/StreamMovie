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

$(function() {

	$(".reg_id").blur(function() {
		$(".v_id").text("");
		var id = $(".reg_id").val();
		if (id == "") {
			$(".v_id_ajax").text("필수 입력 항목 입니다.").css("color", "tomato");
			$(".v_id_ajax").shake();
		} else {
			$.ajax({
				url: "idCheck",
				type: "POST",
				data: "id=" + id,
				dataType: "json",
				success: function(data) {
					if (data.result == "1") {
						$(".v_id_ajax").html("이미 존재하거나<br>사용이 불가능한 아이디 입니다").css("color", "tomato");
						$(".v_id_ajax").shake();
					} else {
						$(".v_id_ajax").text("");
					}
				},
				fail: function() {
					alert("시스템 에러");
				}
			});
		}
	});

	$(".reg_pwd").blur(function() {
		$(".v_pwd").text("");
	});
	$(".reg_pwd_check").blur(function() {
		$(".v_pwd_check").text("");
	});
	$(".reg_nickname").blur(function() {
		$(".v_nickname").text("");
	});
	$(".reg_email").blur(function() {
		$(".v_email").text("");
	});

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
});

function checkRegister() {

	const getId = RegExp(/^[a-z]+[a-z0-9]{5,19}$/g);
	const getPwd = RegExp(/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,30}$/);
	const getEmail = RegExp(/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i);

	if (!$(".reg_agree_term").is(":checked")) {
		alert("회원 가입 약관에 동의하셔야 가입하실 수 있습니다.");
		$(".reg_agree_term").focus();
		return false;
	}

	if ($(".reg_pwd").val() == "") {
		alert("비밀번호를 입력해주세요.");
		$(".reg_pwd").focus();
		return false;
	}
	if ($(".reg_nickname").val() == "") {
		alert("닉네임을 입력해주세요.");
		$(".reg_nickname").focus();
		return false;
	}
	if ($(".reg_email").val() == "") {
		alert("이메일을 입력해주세요.");
		$(".reg_email").focus();
		return false;
	}

	if (!getId.test($(".reg_id").val())) {
		$(".v_id").text("올바르지 않은 아이디입니다");
		$(".reg_id").focus();
		$(".v_id").shake();
		return false;
	}
	if (!getPwd.test($(".reg_pwd").val())) {
		$(".v_pwd").text("올바르지 않은 비밀번호입니다");
		$(".reg_pwd").val("");
		$(".reg_pwd_check").val("");
		$(".reg_pwd").focus();
		$(".v_pwd").shake();
		return false;
	}
	if ($(".reg_id").val() == $(".reg_pwd").val()) {
		$(".v_pwd").text("아이디와 비밀번호가 같습니다");
		$(".reg_pwd").val("");
		$(".reg_pwd_check").val("");
		$(".reg_pwd").focus();
		$(".v_pwd").shake();
		return false;
	}
	if ($(".reg_pwd").val() != $(".reg_pwd_check").val()) {
		$(".v_pwd_check").text("비밀번호가 일치하지 않습니다");
		$(".reg_pwd_check").val("");
		$(".reg_pwd_check").focus();
		$(".v_pwd_check").shake();
		return false;
	}
	if ($(".reg_nickname").val().length < 4) {
		$(".v_nickname").text("4 ~ 15자 이내로 입력해주세요");
		$(".reg_nickname").focus();
		$(".v_nickname").shake();
		return false;
	}
	if (!getEmail.test($(".reg_email").val())) {
		$(".v_email").text("올바르지 않은 이메일입니다");
		$(".reg_email").focus();
		$(".v_email").shake();
		return false;
	}

}