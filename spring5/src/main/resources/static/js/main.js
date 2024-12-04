//https://wormwlrm.github.io/2021/03/01/Async-Defer-Attributes-of-Script-Tag.html
window.onload = function(){
    let sendData = document.getElementById("sendData");		//전역변수
    sendData.addEventListener('click', validate);
}

//이벤트 헨들러
function validate(){
	let fname = document.getElementById("fname").value;    //값(String)		//지역변수
	//let fname = document.getElementById("fname")    //태그 객체
	let phone = document.getElementById("phone").value;    //값(String)
	
	if(fname.length == 0){
		alert("이름을 입력하세요.");
		document.getElementById("fname").focus();   //포커스를
		return;
	}

	if(isNaN(phone) || phone.length != 11){
		alert("전화번호를 잘 입력하세요.");
		document.getElementById("phone").select();
		return;
	}

    let sendForm = document.getElementById("sendForm");
	sendForm.submit();
}