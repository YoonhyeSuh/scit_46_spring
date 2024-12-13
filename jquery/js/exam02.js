$(function() {
    init();
    $('#regist').on('click', appendCustomer);
    $('#checkAll').on('click', function() {
        if($(this).is(':checked')) {
            $('input[type=checkbox]').prop('checked', true);
        } else {
            $('input[type=checkbox]').prop('checked', false);
        }
    });

    // 상단의 삭제버튼을 클릭하면 체크된 나머지 요소도 삭제
    $('#deleteAllBtn').on('click', function() {
        $('td input[type=checkbox]:checked').parent().parent().remove();
    });
});

function init(){
    let receivedData = [
        {"name":"홍길동", "phone":"010-1111", "gender":"남성", "addr":"강남구 역삼동"},
        {"name":"김동영", "phone":"010-1996", "gender":"남성", "addr":"성동구 성수동"},
        {"name":"이제노", "phone":"010-2000", "gender":"남성", "addr":"강남구 역삼동"},
        {"name":"유지민", "phone":"010-2000", "gender":"여성", "addr":"경기도 수원시"},
        {"name":"옥태영", "phone":"010-2020", "gender":"여성", "addr":"강원도 춘천시"}
    ];    //javascript object json

    //위의 데이터를 화면에 출력하시오
    $.each(receivedData, function(index, item){
        //let name = receivedData[index]["name"];
        let name = item["name"];
        let phone = item["phone"];
        let gender = item["gender"];
        let addr = item["addr"];

        output(name, phone, gender, addr);     
    })
}

function output(name, phone, gender, addr){
    let tag = `<tr>
                <td><input type="checkbox"></td>
                <td>${name}</td>
                <td>${phone}</td>
                <td>${gender}</td>
                <td>${addr}</td>
                <td><input type='button' value="삭제" class="deleteBtn"></td>
                </tr>`;
    $('div#result table').append(tag);
    $('.deleteBtn').on('click', deleteCustomer);
}

//삭제
function deleteCustomer(event) {
    $(this).parent().parent().remove(); 
    event.stopImmediatePropagation();
}

//입력된 데이터 수집 후 출력
function appendCustomer() {
    let name = $('#name').val();
    let phone = $('#phone').val();
    let gender = $('input[type=radio]:checked').val();
    let addr = $('#addr').val();

    output(name, phone, gender, addr);
}