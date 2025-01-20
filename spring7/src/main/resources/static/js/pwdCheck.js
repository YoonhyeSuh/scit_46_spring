/**
 * 개인정보 수정 시 필요한 검증 작업
 */

$(function () {
	$('#submitBtn').on('click', validation);
});

// 1) 개인정보 수정을 위한 검증작업
function validation() {

	//비밀번호 길이 체크
	let userPwd = $('#userPwd').val();

	if (userPwd.trim().length < 3 || userPwd.trim().length > 5) {
		$('#confirmPwd').css('color', 'red');
		$('#confirmPwd').html('비밀번호는 3~5자 이내');

		return false;
	}

	return true;
}