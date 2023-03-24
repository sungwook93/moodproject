/**
 *	
 */
 $(document).ready(function() {
	
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
});

//아이디 중복 검사 함수
function fn_idCheck(){
		
	$.ajax({
		url : 		"/member/idCheck",
		type:		"post",
		dataType:	"json",
		data:		{"userID" : $("#userID").val() },
		success:	function(data){
			if(data ==1){ // 입력한 아이디가 이미 존재하는 경우
			
				$("#idCheck").attr("value", "N"); //중복 확인 초기화
				alert("이미 사용 중인 아이디입니다. \n\n 다른 아이디를 입력하십시오.");
				
			}else if(data == 0){ // 입력한 아이디가 존재하지 않는 경우
			
				// 버튼 id = "idCheck"의 속성인 value 값을 "N"에서 "Y"로 변경한다.
				$("#idCheck").attr("value", "Y");
				alert("사용 가능한 아이디입니다.");
			}
		}
	});
}
//아이디 입력값 변화 후 아이디 중복 확인 알람 창 뜨기
	$( '#userID' ).change( function() {
        $('#idCheck').attr("value", "N");
    } );


