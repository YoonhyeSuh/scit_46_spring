/**
 * 회원가입시 필요한 검증 작업
 */

let idCheck = false;
let pwdCheck = false;	//비번, 비번확인

$(function(){
	$('#userId').on('keyup', confirmId);
});

// 1) 아이디 검증 (길이 + 중복확인)
function confirmId(){
	let userId = $('#userId').val();
	
	//길이 체크
	if(userId.trim().length < 3 || userId.trim().length > 5){
		$('#confirmId').css('color', 'red');
		$('#confirmId').html('아이디는 3~5자 이내');
		
		return ;
	}
	
	$('#confirmId').html(' '); //없어도 됨
	$.ajax({
		url: '/user/idCheck',
		method: 'POST',
		data: {"userId": userId},
		success: function(resp){	//resp가 true이면 회원가입 가능
			if(resp){
				$('#confirmId').css('color', 'blue');
				$('#confirmId').html('가입 가능한 아이디입니다.');
				idCheck = true;
			} else{
				$('#confirmId').css('color', 'blue');
				$('#confirmId').html('사용 불가능한 아이디입니다.');
				idCheck = false;
			}
		}
	})
}