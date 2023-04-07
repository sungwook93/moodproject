/**
 * 
 */
 //----------------------------------------------------------------------------------------------------------
 // 게시글 등록 하기
 //----------------------------------------------------------------------------------------------------------
 function fn_reviewRegister() {
	
	let	review_subject	= $("#review_subject").val();
	let	userID	= $("#userID").val();
	let	review_content	= $("#review_content").val();
	let review_star = $("#review_star").val();


	
	// 제목 항목에 값이 없으면 입력하도록 한다.
	if($("#review_subject").val() == "") {
		alert("제목은 필수 입력 항목입니다.");
		$("#review_subject").focus();
		return false;
	}
	
	// 아이디 항목에 값이 없으면 입력하도록 한다.
	if($("#userID").val() == "") {
		alert("아이디는 필수 입력 항목입니다.");
		$("#userID").focus();
		return false;
	}
	
	// 내용 항목에 값이 없으면 입력하도록 한다.
	if($("#review_content").val() == "") {
		alert("내용은 필수 입력 항목입니다.");
		$("#review_content").focus();
		return false;
	}
	
	if($("#review_star").val() == "") {
		alert("별점은 필수 입력 항목입니다.");
		$("#review_star").focus();
		return false;
	}

	
	alert(review_subject + userID + review_content + review_star + product_code);
	
	$.ajax({
		type:		"POST",
		url:		"/review/reviewRegister",
		data:		{review_subject:review_subject, userID:userID, review_content:review_content, review_star:review_star},
		success:	function(data) {
			if(data == "Y") {
				alert("리뷰를 등록하였습니다.");
				location.href = "/review/reviewList?page=1";
			}
		},
		error:		function(data) {
			alert("리뷰를 등록하는데 실패하였습니다!");
		}
	});
	
} 


function fn_typename(){
 //alert("확인");
 	let typename = document.getElementById("productKindU");
 	let produt_type  = typename.options[typename.selectedIndex].value;
 	alert(produt_type);
 	
 	location.href = "/review/searchname?produt_type=" + produt_type;
 		
 		
}

 

