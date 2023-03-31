//관리자 페이지 메뉴
$(document).ready(function() {
	
	//처음 페이지들어갈시 상품관리메뉴만보이게
	$("#admintitle2").css("display", "none");
	$("#admintitle3").css("display", "none");
	
	//처음 페이지들어갈시 상품관리테이블만 보이게
	$("#admintable2").css("display", "none");
	$("#admintable3").css("display", "none");
	
	
	$("#adminarea").css("text-align", "center");
	
	//메뉴 클릭시 보이는 페이지내용
	$("#titlesection1").click(function() {
		$("#admintitle").css("display", "none");
		$("#admintitle2").css("display", "none");
		$("#admintitle3").css("display", "inline-block");
		$("#admintable").css("display", "none");
		$("#admintable2").css("display", "none");
		$("#admintable3").css("display", "inline-block");
	});	
	$("#titlesection2").click(function() {
		$("#admintitle").css("display", "inline-block");
		$("#admintitle2").css("display", "none");
		$("#admintitle3").css("display", "none");
		$("#admintable").css("display", "inline-block");
		$("#admintable2").css("display", "none");
		$("#admintable3").css("display", "none");
	});	
	$("#titlesection3").click(function() {
		$("#admintitle").css("display", "none");
		$("#admintitle2").css("display", "inline-block");
		$("#admintitle3").css("display", "none");
		$("#admintable").css("display", "none");
		$("#admintable2").css("display", "inline-block");
		$("#admintable3").css("display", "none");
	});
	$("#titlesection4").click(function() {
		$("#admintitle").css("display", "inline-block");
		$("#admintitle2").css("display", "none");
		$("#admintitle3").css("display", "none");
		$("#admintable").css("display", "inline-block");
		$("#admintable2").css("display", "none");
		$("#admintable3").css("display", "none");
	});	
	$("#titlesection5").click(function() {
		$("#admintitle").css("display", "none");
		$("#admintitle2").css("display", "inline-block");
		$("#admintitle3").css("display", "none");
		$("#admintable").css("display", "none");
		$("#admintable2").css("display", "inline-block");
		$("#admintable3").css("display", "none");
	});	
	$("#titlesection6").click(function() {
		$("#admintitle").css("display", "none");
		$("#admintitle2").css("display", "none");
		$("#admintitle3").css("display", "inline-block");
		$("#admintable").css("display", "none");
		$("#admintable2").css("display", "none");
		$("#admintable3").css("display", "inline-block");
	});	
	$("#titlesection7").click(function() {
		$("#admintitle").css("display", "none");
		$("#admintitle2").css("display", "inline-block");
		$("#admintitle3").css("display", "none");
		$("#admintable").css("display", "none");
		$("#admintable2").css("display", "inline-block");
		$("#admintable3").css("display", "none");
	});	
	$("#titlesection8").click(function() {
		$("#admintitle").css("display", "none");
		$("#admintitle2").css("display", "none");
		$("#admintitle3").css("display", "inline-block");
		$("#admintable").css("display", "none");
		$("#admintable2").css("display", "none");
		$("#admintable3").css("display", "inline-block");
	});	
	$("#titlesection9").click(function() {
		$("#admintitle").css("display", "inline-block");
		$("#admintitle2").css("display", "none");
		$("#admintitle3").css("display", "none");
		$("#admintable").css("display", "inline-block");
		$("#admintable2").css("display", "none");
		$("#admintable3").css("display", "none");
	});		
});

function fn_admindeleteuser() {
	alert(1);
	// 삭제를 진행하기 전에 먼저 삭제여부를 확인한다.
	if(!confirm("\n회원정보를 삭제하시겠습니까?")) {
		alert("회원정보 삭제를 취소하셨습니다.");
	}else {
		//ajax로 삭제데이터를 보낸다.
		$.ajax({
			type: "post",
			url: "/member/removeMember",
			processData: false,
			contentType: false,
			data: formData,
			success: function(data) {
			
				alert("선택한 상품을 삭제했습니다.");
				//회원 아이디
				var userID = document.getElementById('cartListUserID').value;
				
				location.href="/order/cartForm?userID=" + userID;
			},
			error: function(data) {
				alert("장바구니 삭제에 실패했습니다.");
			}
		});
	}
}
//선택 삭제 버튼을 눌렀을 때 사용할 함수
function fn_cartDelete() {
	
	// 삭제를 진행하기 전에 먼저 삭제여부를 확인한다.
	if(!confirm("\n선택 상품을 삭제하시겠습니까?\n\n삭제하려면 [확인]버튼을 누르시고, 아니면 [취소]버튼을 누르십시오.")) {
		alert("상품 삭제를 취소하셨습니다.");
	} else {	// [확인]버튼을 눌렀을 경우
	
		//체크된 input
		var checkbox = document.querySelectorAll("input[name='cart']:checked"); //체크된 박스 전부
		
		//배열 형태를 보내주기 위한 formData를 준비한다.
		let formData = new FormData();
		
        //반복문으로 formData을 세팅한다.
		for(let i = 0; i < checkbox.length; i++) {
			formData.append("cartNum", checkbox[i].value);
		}
		
		//ajax로 삭제데이터를 보낸다.
		$.ajax({
			type: "post",
			url: "/order/cartDelete",
			processData: false,
			contentType: false,
			data: formData,
			success: function(data) {
			
				alert("선택한 상품을 삭제했습니다.");
				//회원 아이디
				var userID = document.getElementById('cartListUserID').value;
				
				location.href="/order/cartForm?userID=" + userID;
			},
			error: function(data) {
				alert("장바구니 삭제에 실패했습니다.");
			}
		});
		
	}

}