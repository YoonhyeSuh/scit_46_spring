$(function () {
    $('#save').on('click', regist);
});

function regist() {//글 등록 함수 //비동기(ajax)
    let name = $('#guestName').val();
    let pwd = $('#guestPwd').val(); //3~5
    let content = $('#content').val();

    if (name.trim().length < 1) {   //공백 제거
        alert('이름을 입력하세요');
        $('#guestName').focus();
        return;
    }

    if (pwd.trim().length < 3 || pwd.trim().length > 5) {
        alert('비밀번호를 정확히 3-5자로 입력하세요');
        $('#guestPwd').select();
        return;
    }

    if (content.trim().length < 1) {
        alert('방명록을 입력하세요');
        $('#content').focus();
        return;
    }

    //서버로 보낼 데이터 작성
    let sendData = { "guestName": "name", "guestPwd": "pwd", "content": content };
    alert(JSON.stringify(sendData));    //반대는 JSON.parse("{ "guestName": "name", "guestPwd": "pwd", "content": content }"); json으로 변경이 가능한 문자열..?

}