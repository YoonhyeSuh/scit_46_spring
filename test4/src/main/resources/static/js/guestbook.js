$(function () {
    init();
    $('#save').on('click', regist);
});

//초시과 함수
function init() {
    $.ajax({
        url: '/guestbook/selectAll',
        method: 'GET',
        success: output
    });
}

//출력함수
function output(resp) {
    let tag = `
		<table>
			<tr>
				<th>번호</th>
				<th>작성자</th>
				<th>방명록 내용</th>
				<th>기록일</th>
				<th></th>					
			</tr>
	`;

    $.each(resp, function (index, item) {
        tag += `
            <tr>
                <td>${index + 1}</td>
                <td>${item['guestName']}</td>
                <td>${item['content']}</td>
                <td>${item['regdate']}</td>
                <td>
                    <input type="button" value="삭제" data-seqno="${item['seqno']}" class="deleteBtn">
                </td>
            </tr>
        `;
    });
    tag += `</table>`;
    $('#outputData').html(tag);
    $('.deleteBtn').on('click', deleteItem);
}

//글 삭제 함수(Ajax)
function deleteItem() {
    let seqno = $(this).attr('data-seqno')  //data-seqno="${item['seqno']} 속성 읽어오기
    let pwd = prompt('비밀번호를 입력하시오');

    let sendData = { "seqno": seqno, "guestPwd": pwd };
    let answer = confirm("정말 지우시겠습니까?");

    if (!answer) {
        return;
    }
    $.ajax({
        url: "/guestbook/delete",
        method: "GET",
        data: sendData,
        success: init
    })

}

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
    let sendData = { "guestName": name, "guestPwd": pwd, "content": content };
    //alert(JSON.stringify(sendData));    //반대는 JSON.parse("{ "guestName": name, "guestPwd": pwd, "content": content }"); json으로 변경이 가능한 문자열..?

    $.ajax({
        url: '/guestbook/guestbookRegist',
        method: 'POST',
        data: sendData,
        success: function (resp) {
            init();
            clearAll();
        }
    });
}

//입력상자 지우는 함수

function clearAll() {
    $('#guestName').val('');
    $('#guestPwd').val('');
    $('#content').val('');
}