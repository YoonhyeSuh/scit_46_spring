$(function () {
    init();
})

function init() {
    $.ajax({
        url: 'resumeList',
        method: 'GET',
        success: output
    })
}

function output(resp) {
    let tag = `
        <table>
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>작성일</th>     
            </tr>
    `;

    $.each(resp, function (index, item) {
        tag += `
            <tr>
                <td>${item.resumeNum}</td>
                <td>${item.title}</td>
                <td>${item.regdate}</td>
            </tr>
        `;
    })

    tag += `</table>`;

    $('#result').html(tag);
}