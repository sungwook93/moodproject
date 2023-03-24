/**
 *	
 */
 $(document).ready(function() {
	//datepicker 달력 
	$("#age").datepicker();
	
	$.datepicker.setDefaults({
	    dateFormat: 'yy-mm-dd',
	    prevText: '이전 달',
	    nextText: '다음 달',
	    monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
	    monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
	    dayNames: ['일', '월', '화', '수', '목', '금', '토'],
	    dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
	    dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
	    showMonthAfterYear: true,
	    yearSuffix: '년',
	        yearRange: 'c-100:c', //연도 범위
	        changeMonth: true, // 월을 바꿀수 있는 셀렉트 박스를 표시한다.
	   
	        changeYear: true, // 년을 바꿀 수 있는 셀렉트 박스를 표시한다.
    });
    $(".cancel").on("click", function(){ //취소 버튼을 눌렀을 때
		location.href = "/main.do"; //메인페이지로 이동
	});
	
	//아이디 입력 시 한글입력 안되게 처리
	$("input[name=userID]").keyup(function(event){ 
		if (!(event.keyCode >=37 && event.keyCode<=40)) {
			var inputVal = $(this).val();
			$(this).val(inputVal.replace(/[^a-z0-9]/gi,''));
		}
	});
	// 회원 가입 버튼을 눌렀을 경우(userID = "submit") - memberForm 에서
	$("#memberFormsubmit").on("click", function(){
	
		if($("#userID").val()==""){
			alert("아이디를 입력하셔야 합니다.");
			$("#userID").focus();
			return false;
		}
		if($("#userID").val().length < 4){
			alert("아이디는 최소 4자리 이상을 입력하셔야 합니다.");
			$("#userID").focus();
			return false;
		}
		if($("#idCheck").val() == "N"){
			alert("아이디 중복검사가 필요합니다. 중복확인을 해주세요");
			$("#idCheck").focus();
			return false;
		}
		
		if($("#pwd").val()==""){
			alert("비밀번호를 입력하셔야 합니다.");
			$("#pwd").focus();
			return false;
		}
		if($("#pwd").val().length < 4){
			alert("비밀번호 확인는 최소 4자리 이상을 입력하셔야 합니다.");
			$("#pwd").focus();
			return false;
		}
		
		if($("#repwd").val()==""){
			alert("비밀번호 확인을 입력하셔야 합니다.");
			$("#repwd").focus();
			return false;
		}
		if($("#repwd").val().length < 4){
			alert("비밀번호 확인은 최소 4자리 이상을 입력하셔야 합니다.");
			$("#repwd").focus();
			return false;
		}
		if($("#pwd").val() != $("#repwd").val() ){
			alert("비밀번호와 비밀번호 확인이 맞지 않습니다. 다시입력해주세요");
			$("#pwd").focus();
			return false;	
		}
		
		if($("#name").val()==""){
			alert("이름을 입력하셔야 합니다.");
			$("#name").focus();
			return false;
		}
		
		if($("#address1").val()==""){
			alert("주소를 입력해야합니다.");
			$("#address1").focus();
			return false;
		}
		if($("#address2").val()==""){
			alert("주소를 입력해야합니다.");
			$("#address2").focus();
			return false;
		}
		
		if($("#phone").val()==""){
			alert("핸드폰번호를 입력하셔야 합니다.");
			$("#phone").focus();
			return false;
		}
		
		if($("#age").val()==""){
			alert("나이를 입력하셔야 합니다.");
			$("#age").focus();
			return false;
		}
		
		if($("#email").val()==""){
			alert("이메일을 입력하셔야 합니다.");
			$("#email").focus();
			return false;
		}
		

		
		if($('input[name=register_Yn]:checked').val()=="no"){
			alert("동의버튼을 누르셔야 합니다.");
			$("#register_Yn").focus();
			return false;
		}
		
		var checked = $('#agreements').is(':checked');
		
		if(!checked) {
			alert("개인정보 수집에 동의하셔야 합니다.");
			$("#agreements").focus();
			return false;
		}
		
		
	});
});

//아이디 중복 검사 함수
function fn_idCheck(){

	var confirmMsgID = document.getElementById('confirmMsgID');		//확인 메세지
	var correctColor = "#0000FF";	//맞았을 때 출력되는 색깔.
	var wrongColor ="#ff0000";	//틀렸을 때 출력되는 색깔
	
		
	$.ajax({
		url : 		"/member/idCheck",
		type:		"post",
		dataType:	"json",
		data:		{"userID" : $("#userID").val() },
		success:	function(data){
			if(data ==1){ // 입력한 아이디가 이미 존재하는 경우
			
				$("#idCheck").attr("value", "N"); //중복 확인 초기화
				alert("이미 사용 중인 아이디입니다. \n\n 다른 아이디를 입력하십시오.");
				
				confirmMsgID.style.color = wrongColor;
				confirmMsgID.innerHTML ="이미 사용 중인 아이디입니다. \n\n 다른 아이디를 입력하십시오.";
				$("#idcheckmsg").css("display", "none");
				
			}else if(data == 0){ // 입력한 아이디가 존재하지 않는 경우
			
				// 버튼 id = "idCheck"의 속성인 value 값을 "N"에서 "Y"로 변경한다.
				$("#idCheck").attr("value", "Y");
				alert("사용 가능한 아이디입니다.");
				
				confirmMsgID.style.color = correctColor;/* span 태그의 ID(confirmMsgID) 사용  */
				confirmMsgID.innerHTML ="사용가능한 아이디입니다.";/* innerHTML : HTML 내부에 추가적인 내용을 넣을 때 사용하는 것. */
				$("#idcheckmsg").css("display", "none");
			}
		}
	});
}
//아이디 입력값 변화 후 아이디 중복 확인 알람 창 뜨기
	$( '#userID' ).change( function() {
        $('#idCheck').attr("value", "N");
    } );

//비밀번호  확인 함수
 function passConfirm() {
 
	/* 비밀번호, 비밀번호 확인 입력창에 입력된 값을 비교해서 같다면 비밀번호 일치, 그렇지 않으면 불일치 라는 텍스트 출력.*/
	/* document : 현재 문서를 의미함. 작성되고 있는 문서를 뜻함. */
	/* getElementByID('아이디') : 아이디에 적힌 값을 가진 id의 value를 get을 해서 password 변수 넣기 */
				
	let pwd	= document.getElementById('pwd');					//비밀번호 
	let repwd 	= document.getElementById('repwd');				//비밀번호 확인 값
	let confirmMsg = document.getElementById('confirmMsg');		//확인 메세지
	let correctColor = "#0000FF";	//맞았을 때 출력되는 색깔.
	let wrongColor ="#ff0000";	//틀렸을 때 출력되는 색깔
	
	alert(pwd.value + ":" + repwd.value);
					
	if((pwd.value).equals(repwd.value)){//password 변수의 값과 passwordConfirm 변수의 값과 동일하다.
		alert("1");
		confirmMsg.style.color = correctColor;/* span 태그의 ID(confirmMsg) 사용  */
		confirmMsg.innerHTML ="비밀번호가 일치합니다";/* innerHTML : HTML 내부에 추가적인 내용을 넣을 때 사용하는 것. */
	}else{
		alert("2");
		confirmMsg.style.color = wrongColor;
		confirmMsg.innerHTML ="비밀번호가 일치하지 않습니다.";
	}
}
//휴대폰 번호 자동으로 - 추가 해주는 함수
function inputPhoneNumber() {
	
	var phone = document.getElementById("phone");		   
    var number = phone.value.replace(/[^0-9]/g, "");
    var phone1 = "";
    
    if(number.length < 4) {
        return number;
    } else if(number.length < 7) {
        phone1 += number.substr(0, 3);
        phone1 += "-";
        phone1 += number.substr(3);
    } else if(number.length < 11) {
        phone1 += number.substr(0, 3);
        phone1 += "-";
        phone1 += number.substr(3, 3);
        phone1 += "-";
        phone1 += number.substr(6);
    } else {
        phone1 += number.substr(0, 3);
        phone1 += "-";
        phone1 += number.substr(3, 4);
        phone1 += "-";
        phone1 += number.substr(7);
    }
    phone.value = phone1;
}
//----------------------------------------------------------------------------------------------------------
//다음(카카오) API 호출 함수
//----------------------------------------------------------------------------------------------------------

function daumZipCode() {
				
	new daum.Postcode({
					
		oncomplete: function(data) {
			// 팝업창에서 검색한 결과에서 항목을 클릭하였을 경우에 실행할 코드를 이곳에 작성한다.
						
			// 각 주소의 노출 규칙에 따라서 주소를 조합해야 한다.
			// 내려오는 변수가 값이 없을 경우에는 공백('')값을 가지므로 이름을 참고하여 분기한다.
						
			var fullAddr = '';	// 최종 주소값을 저장할 변수
			var subAddr = '';	// 조합형 주소값을 저장할 변수
						
			// 사용자가 선택한 주소의 타입에 따라서 해당 주소 값을 가져온다.
			if(data.userSelectedType == 'R') {	// 도로명 주소를 선택한 경우	
			
				fullAddr		= data.roadAddress;
						
			}else {	// 지번 주소를 선택한 경우
			
				fullAddr		= data.jibunAddress;
				
			}	
						
			// 사용자가 선택한 주소가 도로명 타입일 때 조합한다.
			if(data.userSelectedType == 'R'){
				// 법정도명이 있을 경우에 추가한다.
				if(data.bname != ''){
					subAddr += data.bname;
				}
				// 건물명이 있을 경우에 추가한다.
				if(data.buildingName != ''){
					subAddr += (subAddr != '' ? ', ' + data.buildingName : data.buildingName);
				}
				// 조합형 주소의 유무에 따라서 양쪽에 괄호를 추가하여 최종 주소를 만든다.
				fullAddr += (subAddr != '' ? '(' + subAddr +' )' : ' ');
			}
						
			// 우편번호와 주소정보를 화면의 해당 필드에 출력시킨다.
			document.getElementById('postnum').value	= data.zonecode;	// 5자리의 새 우편번호
			document.getElementById('address1').value	= fullAddr;
						
			// 커서를 상세주소 입력란으로 이동시킨다.
			document.getElementById('address2').focus();
		}
					
	}).open({
		//open에 이름을 부여하여 우편번호 팝업창이 여러개 뜨는 것을 방지하기 위해서 popupName을 사용한다.
		popupName:	'postcodePopup'
	});		
}
