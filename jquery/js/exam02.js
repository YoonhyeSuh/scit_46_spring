$(function() {
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
function appendCustomer() {
    let name = $('#name').val();
    let phone = $('#phone').val();
    let gender = $('input[type=radio]:checked').val();
    let addr = $('#addr').val();

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
function deleteCustomer(event) {
    $(this).parent().parent().remove(); 
    event.stopImmediatePropagation();
}