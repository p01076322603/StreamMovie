var element_wrap = document.getElementById('wrap');
function foldDaumPostcode() {
    element_wrap.style.display = 'none';
}

function sample3_execDaumPostcode() {
    var currentScroll = Math.max(document.body.scrollTop, document.documentElement.scrollTop);
    new daum.Postcode({
        oncomplete: function(data) {
            var addr = '';
            var extraAddr = '';

            if (data.userSelectedType === 'R') { 
                addr = data.roadAddress;
            } else { 
                addr = data.jibunAddress;
            }

            if(data.userSelectedType === 'R'){
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                document.getElementById("sample3_extraAddress").value = extraAddr;
            
            } else {
                document.getElementById("sample3_extraAddress").value = '';
            }

            document.getElementById('sample3_postcode').value = data.zonecode;
            document.getElementById("sample3_address").value = addr;
            document.getElementById("sample3_detailAddress").focus();

            element_wrap.style.display = 'none';

            document.body.scrollTop = currentScroll;
        },
        onresize : function(size) {
            element_wrap.style.height = size.height + 'px';
        },
        width : '100%',
        height : '100%'
    }).embed(element_wrap);

    element_wrap.style.display = 'block';
}

function displayNewProfile() {
	$(".old_profimg").hide();
	$(".new_profimg").show();
	$(".profimg_reset").show();
}
function displayOldProfile() {
	$(".old_profimg").show();
	$(".new_profimg").hide();
	$(".profimg_reset").hide();
}

displayOldProfile();
function readURL(input) {
	if (input.files && input.files[0]) {
		var reader = new FileReader();
		reader.onload = function(e) {
			$(".new_profimg").attr("src", e.target.result);
			displayNewProfile();
		} 
	} else {
			displayOldProfile();
	}
	reader.readAsDataURL(input.files[0]);
}

$(".reset, .profimg_reset").click(function() {
	document.getElementById("new_profimg_input").value = '';
	displayOldProfile();
});

$(".mdf_nickname").blur(function() {
	$(".v_nickname").text("");
});
$(".mdf_email").blur(function() {
	$(".v_email").text("");
});
$("#sample3_detailAddress, #sample3_extraAddress").blur(function() {
	$(".v_address").text("");
});

$(document).on("blur", ".phoneNumber", function() {
	$(this).val( $(this).val().replace(/[^0-9]/g, "").replace(/(^02|^0505|^1[0-9]{3}|^0[0-9]{2})([0-9]+)?([0-9]{4})$/,"$1-$2-$3").replace("--", "-") );
});

function memberModifyValidate() {
	
	const getEmail = RegExp(/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i);
	const getPhone = RegExp(/^(01[016789]{1})-\d{3,4}-\d{4}$/);
	
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
	
	if ($(".mdf_nickname").val() == "") {
		alert("닉네임을 입력해주세요.");
		$(".mdf_nickname").focus();
		return false;
	}
	if ($(".mdf_email").val() == "") {
		alert("이메일을 입력해주세요.");
		$(".mdf_email").focus();
		return false;
	}	
	
	if ($(".mdf_nickname").val().length < 4) {
		$(".v_nickname").text("4 ~ 15자 이내로 입력해주세요");
		$(".mdf_nickname").focus();
		$(".v_nickname").shake();
		return false;
	}
	if (!getEmail.test($(".mdf_email").val())) {
		$(".v_email").text("올바르지 않은 이메일입니다");
		$(".mdf_email").focus();
		$(".v_email").shake();
		return false;
	}
	if ( !($("#sample3_postcode").val() == "" 
		&& $("#sample3_address").val() == "" 
		&& $("#sample3_detailAddress").val() == "" 
		&& $("#sample3_extraAddress").val() == "")) {
		
		if ($("#sample3_postcode").val() == "" || $("#sample3_address").val() == "") {
			$(".v_address").text("우편번호 찾기를 눌러주세요");
			$(".v_address").shake();
			return false;
		}
		if ($("#sample3_detailAddress").val() == "") {
			$(".v_address").text("상세 주소를 입력해주세요");
			$("#sample3_detailAddress").focus();
			$(".v_address").shake();
			return false;
		}
	}	
	if ($(".mdf_phone").val() != "") {	
		
		if (!getPhone.test($(".mdf_phone").val())) {
			$(".v_phone").text("올바르지 않은 전화번호입니다");
			$(".mdf_phone").focus();
			$(".v_phone").shake();
			return false;
		}
	}
}