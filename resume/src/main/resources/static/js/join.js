/**
 * 회원가입시 필요한 검증 작업
 */

let idCheck = false;
let pwdCheck = false;	//비번, 비번확인

$(function () {
    $('#userId').on('keyup', confirmId);
    $('#userPwd').on('focus', function () {
        $('#userPwdCheck').val('');
    })
    $('#submitBtn').on('click', join);
});

// 1) 아이디 검증 (길이 + 중복확인)
function confirmId() {
    let userId = $('#userId').val();

    //길이 체크
    if (userId.trim().length < 3 || userId.trim().length > 8) {

        alert('아이디는 3~8자 사이로 입력해주세요.');

        return false;
    }

    // $('#confirmId').html(' '); //없어도 됨
    // $.ajax({
    //     url: '/user/idCheck',
    //     method: 'POST',
    //     data: { "userId": userId },
    //     success: function (resp) {	//resp가 true이면 회원가입 가능
    //         if (resp) {
    //             $('#confirmId').css('color', 'blue');
    //             $('#confirmId').html('가입 가능한 아이디입니다.');
    //             idCheck = true;
    //         } else {
    //             $('#confirmId').css('color', 'red');
    //             $('#confirmId').html('사용 불가능한 아이디입니다.');
    //             idCheck = false;
    //         }
    //     }
    // })
}

// 2) 회원가입을 위한 나머지 검증작업
function join() {
    let userPwd = $('#userPwd').val();
    //비밀번호 길이 체크
    if (userPwd.trim().length < 3 || userPwd.trim().length > 8) {
        alert("비밀번호는 3~8자 사이로 입력해주세요")
        pwdCheck = false;
        return false;
    }

    let userPwdCheck = $('#userPwdCheck').val();
    if (userPwdCheck.trim() < 1) {
        alert("확인 비밀번호를 입력해 주세요");

        return false;
    }

    //비밀번호와 체크값을 확인
    if (userPwd.trim() != userPwdCheck.trim()) {
        alert("동일한 비밀번호를 입력해 주세요");
        pwdCheck = false;
        return false;
    }

    // 이름 입력 체크
    let userName = $('#userName').val();
    if (userName.trim() < 1) {
        alert("이름을 입력해 주세요");

        return false;
    }

    return true;
}