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
	let	product_type	= $("#product_type").val();
	let product_name = $("#product_name").val();
	let file = document.getElementById("file");

	
	//파일수를 제한을 둔다.
	if(file.files.length > 2){
		alert("사진은 2장까지만 등록할수 있습니다.");
		file.focus();
		return false;
	}
	
	
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
	
	if($("#review_star").val() == null) {
		alert("별점은 필수 입력 항목입니다.");
		$("#review_star").focus();
		return false;
	}
	
	
	if($("#product_type").val() == null) {
		alert("상품 타입은 필수 입력 항목입니다.");
		$("#product_type").focus();
		return false;
	}
	
	if($("#product_name").val() == null) {
		alert("상품 이름은 필수 입력 항목입니다.");
		$("#product_name").focus();
		return false;
	}
	
	//alert(review_subject + userID + review_content + review_star + product_type + product_name);
	//alert(file);
	
	$.ajax({
		type:		"POST",
		url:		"/review/reviewRegister",
		data:		{review_subject:review_subject, userID:userID, review_content:review_content, review_star:review_star, product_type:product_type, product_name:product_name},
		success:	function(data) {
			if(data != 0) {
				//alert(file.files.length + "확인1");
				
				if(file.files.length == 0 || file.files.length == null){
					//alert("확인");
					location.href = "/review/reviewList?page=1";
				} else {
				
				//alert("리뷰를 등록하였습니다.");
				//location.href = "/review/reviewList?page=1";
				//alert("등록 리뷰번호 ==" + data);
				
				//이미지 등록을위한 form데이터 객체 생성
				let formData = new FormData();
				
				//업로드 된 이미지만큼 반복문으로 formdata에 넣어준다.
				//set은 리셋되면서 추가하는거지만 append는 리셋하지않고 계속 추가한다.
				//alert(file.files.length);
				for(let i = 0; i<file.files.length; i++){
				formData.append("files", file.files[i]);
				}
				
				formData.append("review_bno", data);
				
				$.ajax({
					type: "post",
					url: "/image/reviewUpload",
					processData: false,
					contentType: false,
					data: formData,
					dataType: "json",
					success: function(data){
					
					 location.href="/review/reviewList?page=1";
					},
					error: function(data){
							alert("상품 이미지 등록에 실패하였습니다.");
					}
				
				}); // end - 리뷰 이미지 ajax 
				
				}
				
			}
		},
		error:		function(data) {
			alert("리뷰를 등록하는데 실패하였습니다!");
		}
	});//end - 글작성ajax
	
} 

// 리뷰 상세페이지 보여주기
function fn_typename(){
 //alert("확인");
 	let typename = document.getElementById("product_type");
 	let produt_type  = typename.options[typename.selectedIndex].value;
 	//alert(produt_type);
 	
 	location.href = "/review/searchname?produt_type=" + produt_type;
 		
 		
}


function productKindChange(e) {
	var living = ["BAGGEBO 바게보 도어수납장", "VIHALS 비할스 다용도수납장", "STOENSE 스토엔세 단모러그", "BESTA 베스토 TV장식장", "IKEA PS 이케아 피에스 수납장", "DEJSA 데이사 탁상스탠드", "MALM 말름 6칸서랍장", "LIXHULT 릭스훌트 수납장", "TIPHEDE 팁헤데 평직러그", "RANNAS 란네스 TV장식장", "BILLY 빌리 책장", "PILSKOTT 필스코트 LED플로어스탠드", "EKET 에케트 수납장", "KALLAX 칼락스 선반유닛", "LANGSTED 랑스테드 단모러그", "BESTA 베스토 TV수납콤비네이션", "EKET 에케트 수납콤비네이션", "GLADOM 글라돔 트레이테이블"];
	var bed = ["FREDVANG 프레드방 수납장", "SKUBB 스쿠브 수납장", "KULLEN 쿨렌 서랍장", "HEMNES 헴네스 서랍장", "ÅBYGDA 오뷔그다 매트리스", "BRIMNES 브림네스 침대해드", "KNARREVIK 크나레비크 탁상", "IDANÄS 이다네스 탁상", "HEMNES 헴네스 수납장", "VARDÖ 바르되 수납장", "IDANÄS 이다네스 침대", "IDANÄS 이다네스 6칸서랍장", "STOCKHOLM 2017 스톡홀름 2017 탁상스탠드", "SLATTUM 슬라툼 쿠션형 침대프레임", "OLDERDALEN 올데르달렌 침대협탁", "STUK 스투크 수납박스", "KOPPARFALL 코파르팔 그림", "TÄLLBYN 텔뷘 플로어스탠드"];
	var bath = ["ENHET 엔헤트 수납콤비네이션1", "ENHET 엔헤트 수납콤비네이션2", "ENHET 엔헤트 수납콤비네이션3","HAVSDUN 하브스둔 LED벽부착등", "VILTO 빌토 수건스탠드", "BASTSJÖN 바스트셴 샤워커튼","GANSJÖN 간셴 욕실용품3종", "VÅGSJÖN 복셴 세면타올 A세트", "EKOLN 에콜른 휴지통","ALMTJÄRN 알름셰른 욕실매트", "LINDBYN 린드뷘 거울 블랙", "VESKEN 베스켄 카트 블랙","BROGRUND 브로그룬드", "ENHET 엔헤트 / TVÄLLEN 트벨렌", "ENHET 엔헤트 양문형 거울수납장 그레이 프레임","TOLFSEN 톨프센 샤워커튼", "4+1 마비스 칫솔 블랙 하드 타입 이탈리아 명품칫솔", "DIMPA 딤파 분리수거가방"];
	var target = document.getElementById("product_name");
			
		if(e.value == "living") var d = living;
		else if(e.value == "bed") var d = bed;
		else if(e.value == "bath") var d = bath;
			
		target.options.length = 0;
			
		for (x in d) {
			 var opt = document.createElement("option");
			 opt.value = d[x];
			 opt.innerHTML = d[x];
			 target.appendChild(opt);
		}   
}


// 리뷰 상세페이지 보여주기2
function fn_typename2(){
 //alert("확인");
 	let typename = document.getElementById("product_type");
 	let produt_type  = typename.options[typename.selectedIndex].value;
 	//alert(produt_type);
 	
 	location.href = "/review/searchname2?produt_type=" + produt_type; 
 		
 		
}

// 리뷰 수정
function fn_reviewUpdate() {
	
	let	review_bno		= $("#review_bno").val();
	let	review_subject	= $("#review_subject").val();
	let	userID	= $("#userID").val();
	let	review_content	= $("#review_content").val();
	let	product_type	= $("#product_type").val();
	let	product_name	= $("#product_name").val();
	let	review_star	= $("#review_star").val();
	let file = document.getElementById("file");
	
	//alert(review_bno + ":" + review_subject + ":" + userID + ":" + review_content + ":" + product_type + ":" + product_name + ":" + review_star);
	
	if(confirm("게시글을 등록하시겠습니까? ")){
	
		$.ajax({
			type:			"POST",
			url:			"/review/reviewUpdate",
			data:			{review_bno:review_bno, review_subject:review_subject, userID:userID, review_content:review_content, review_star:review_star, product_type:product_type, product_name:product_name},
			success:		function(data) {
				if(data != 0) {
					if(file.files.length == 0 || file.files.length == null){ // 이미지 변경 없을 때 
						alert("리뷰 수정이 완료되었습니다.");
						location.href="/review/reviewList?page=1";
					
					} else {
					//이미지 등록을 위한 form데이터 객체 생성
					let formData = new FormData();
					
					// 업로드 된 이미지만큼 반복문으로 FormData에 넣어준다.
					for(let i = 0; i < file.files.length; i++) {
						formData.append("files",file.files[i]);
					}
					
					// formData에 리뷰번호를 넣어준다.
					formData.append("review_bno", data);
					
						// 이미지 수정 ajax
						$.ajax({
							type: "post",
							url: "/image/updateImage1",
							processData: false,
							contentType: false,
							data: formData,
							dataType: "json",
							success: function(data){
								alert("리뷰 수정이 완료되었습니다.");
								location.href="/review/reviewList?page=1";
							
							} // End - 이미지 수정 success 	
							
						}); // End - 이미지 수정 ajax
						
					} // End - if
				
				} // End - success	
			} // End - if
		}); // End - ajax문
	
	} // End - if문

} // End - function fn_reviewUpdate() 



// 리뷰 삭제 
 function fn_reviewDelete(review_bno) {
 	
	if(!confirm("\n리뷰을 삭제하시겠습니까?\n\n삭제하려면 [확인]버튼을 누르시고, 아니면 [취소]버튼을 누르십시오.")) {
		alert("리뷰 삭제를 취소하셨습니다.");
	} else {	// [확인]버튼을 눌렀을 경우
	
		$.ajax({
					type:"post",
					url:"/image/deleteImage1",
					data: {review_bno:review_bno},
					success: function(data){
					alert("review_bno ==>" + data);	
					
					
			$.ajax({
				type:			"POST",
				url:			"/review/reviewDelete",
				data:			{review_bno:review_bno},
				success:		function(data) {
					if(data == "Y") {
						alert("리뷰 삭제가 완료되었습니다.");
						location.href = "/review/reviewList?page=1";
					}else {
						alert("리뷰 삭제 안됨." + data);
					}
				},
				error:			function(data) {
					alert("리뷰 삭제하는데 문제가 발생하였습니다." + data);
	
				},
				done:			function(data) {
					alert("요청 성공");
				},
				fail:			function(data) {
					alert("요청 실패");
				},
				always:			function(data) {
					alert("요청 완료");
				}
			});
		}
		});
	}
 }
 

 // 댓글 등록
 
 function fn_commentRegister(review_bno) {
 
 	let reply_content = $("#reply_content").val();
 	let userID = $("#userID1").val();
 	
 	//alert(reply_content + " : " + userID);
 	
 	if($("#reply_content").val() == "") {
 		alert("댓글 내용을 입력해주세요");
 		$("#reply_content").focus();
 		return false;
 	}
 	
 	$.ajax({
 		type : "POST",
 		url: "/review/reviewCommentRegister",
 		data: {review_bno:review_bno, reply_content:reply_content, userID:userID},
 		success: function(data) {
 			if(data == "Y") {
 			alert("댓글 등록 완료");
 			location.href = "/review/reviewDetail?review_bno=" + review_bno;
 			}
 		},
 		error: function(data) {
 			alert("댓글 등록 실패");
 		}
 	});
 	 	
 }

 // 댓글 삭제 
 
 function fn_deleteComment(status, review_bno, imsi_bno ) {
	
	let reply_bno = status;
	
	//alert("imsi_bno" + imsi_bno + "             " + "reply_bno" + reply_bno + "             " + "review_bno" + review_bno);
	
	
	if(!confirm("\댓글을 삭제하시겠습니까?\n\n삭제하려면 [확인]버튼을 누르시고, 아니면 [취소]버튼을 누르십시오.")) {
		alert("댓글 삭제를 취소하셨습니다.");
	} else {	
	
		$.ajax({
			type: "POST",
			url: "/review/replyDelete",
			data: {review_bno:review_bno, reply_bno:reply_bno, imsi_bno:imsi_bno},
			success: function(data) {
				if(data == "Y") {
					alert("댓글의 삭제가 완료되었습니다.");
					location.href="/review/reviewDetail?review_bno=" + review_bno;
				}
			},
			error: function(data){
				alert("댓글 삭제에 실패했습니다.");
			}
		});
	
	}
}
 
 
// 댓글 수정
function fn_updateComment(status, imsi_bno, review_bno) {
	
	let reply_bno = status;
	let	reply_content = $("#"+status).val();
	let	userID = $("#userID1").val();
	
	//alert(review_bno + ":" + reply_content + ":" + "reply_bno" + ":" + reply_bno + "status" + ":"  + status + "imsi_bno" + imsi_bno);
	
	$.ajax({
		type:			"POST",
		url:			"/review/replyUpdate",
		data:			{review_bno:review_bno, reply_content:reply_content, reply_bno:reply_bno, userID:userID, imsi_bno:imsi_bno},
		success:		function(data) {
			if(data == "Y") {
				alert("댓글 수정이 완료되었습니다.");
				location.href="/review/reviewDetail?review_bno=" + review_bno;
			} else {
				alert("댓글 수정이 되지 않았습니다.\n\n잠시 후에 다시 해주십시오.");
			}
		},
		error:	function(data) {
			alert("실패");
			console.log(data);
		}
	});
	
} 

// 댓글 수정창 

function fn_updateOpen(reply_bno, imsi_bno) {

	let status = reply_bno;

  	//alert($("#" + status).val() + "입니다." + " imsi_bno는 " + imsi_bno + " reply_bno는 " + reply_bno);	
  
	 	$("#" + status).attr("readonly",false);
		$("#" + status).focus();
	 	
	 	document.getElementById("commentUpdateB"+status).style.display = 'inline';
		document.getElementById("commentUpdateA"+status).style.display = 'none';
		
		
	
 }  


 // 검색 

$(document).ready(function() {
			
	var formObj = $("#formList");
	
	// 검색 버튼을 눌렀을 경우
	$("#searchBtn").click(function() {
		var typeStr		= $("#searchType").find(":selected").val();
		var keywordStr	= $("#searchKeyword").val();
		//alert(typeStr + ":" + keywordStr);
		
		// 서버로 전송하기 전에, name 속성에 값을 넣어준다.
		formObj.find("[name='searchType']").val(typeStr);
		formObj.find("[name='keyword']").val(keywordStr);
		formObj.find("[name='page']").val("1");
		formObj.submit();
	});
	
});
