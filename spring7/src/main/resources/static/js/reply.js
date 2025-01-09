/**
 * 
 */
$(function () {
    //첨부파일이 있을 경우 삭제
    $('.deleteFile').on('click', function () {
        let boardSeq = $(this).attr('data-seq');
        let searchItem = $(this).attr('data-searchItem');
        let searchWord = $(this).attr('data-searchWord');

        let answer = confirm("정말로 삭제하시겠습니까?");
        if (answer) {
            location.href = "/board/deleteFile?boardSeq=" + boardSeq + "&searchItem=" + searchItem + "&searchWord=" + searchWord;
            //location.href=`/board/deleteFile?boardSeq=boardSeq=${boardSeq},searchItem=${searchItem}, searchWord=${searchWord}`;
        } else {
            alert('삭제작업을 취소합니다.');
        }
    });

    init(); //전체 댓글 읽어서 화면에 출력

    //댓글 입력
    $('#replyBtn').on('click', replyInsert);
});

//댓글 전체 조회
function init() {
    let boardSeq = $('#boardSeq').val();
    $.ajax({
        url: '/reply/replyAll',
        method: 'GET',
        data: { "boardSeq": boardSeq },
        success: function (resp) {  //resp - [{}, {}, {}]; 배열로 보임
            console.log(resp);
        }
    })
}

//댓글 입력
function replyInsert() {
    let replyContent = $('#replyContent').val();
    let boardSeq = $('#boardSeq').val();

    if (replyContent.trim().length == 0) {
        alert("댓글을 입력하세요.");
        return;
    }

    let sendData = { "boardSeq": boardSeq, "replyContent": replyContent };

    //ajax로 댓글 송신
    $.ajax({
        url: '/reply/replyInsert',
        method: 'POST',
        data: sendData,
        success: function (resp) {
            console.log(resp);
        }
    })
}