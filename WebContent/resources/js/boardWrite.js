$(function() {
		   if (boardno === '1') {
		$(".board_select").val('1').attr('selected', 'selected');
	} else if (boardno === '2') {
		$(".board_select").val('2').attr('selected', 'selected');
	} else if (boardno === '3') {
		$(".board_select").val('3').attr('selected', 'selected');
	} else if (boardno === '4') {
		$(".board_select").val('4').attr('selected', 'selected');
	} else {
		$(".board_select").val('0').attr('selected', 'selected');
	}
});

$('.board_title').on('keyup', function() {
	if($(this).val().length > 50) {
		alert("제목은 50자 이내로 제한됩니다.");
		$(this).val($(this).val().substring(0, 50));
	}
});

function boardWriteValidate() {
	
	if ($(".board_select").val() == "0") {
		alert("게시판을 선택해주세요.");
		$(".board_select").focus();
		return false;
	}
	if ($.trim($(".board_title").val()) == "") {
		alert("제목을 입력해주세요.");
		$(".board_title").focus();
		return false;
	}
	if (CKEDITOR.instances.editor.getData() == "") {
		alert("내용을 입력해 주세요.");
		CKEDITOR.instances.editor.focus();
		return false;
	}
	
}