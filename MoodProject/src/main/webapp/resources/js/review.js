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
	
	if($("#product_type").val() == "") {
		alert("상품 타입은 필수 입력 항목입니다.");
		$("#product_type").focus();
		return false;
	}
	
	if($("#product_name").val() == "") {
		alert("상품 이름은 필수 입력 항목입니다.");
		$("#product_name").focus();
		return false;
	}
	
	alert(review_subject + userID + review_content + review_star + product_type + product_name);
	
	$.ajax({
		type:		"POST",
		url:		"/review/reviewRegister",
		data:		{review_subject:review_subject, userID:userID, review_content:review_content, review_star:review_star, product_type:product_type, product_name:product_name},
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

// selectbox
function fn_typename(){
 //alert("확인");
 	let typename = document.getElementById("product_type");
 	let produt_type  = typename.options[typename.selectedIndex].value;
 	alert(produt_type);
 	
 	location.href = "/review/searchname?produt_type=" + produt_type;
 		
 		
}

// selectbox
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




// 리뷰 수정
function fn_reviewUpdate() {
	
	let	review_bno		= $("#review_bno").val();
	let	review_subject	= $("#review_subject").val();
	let	userID	= $("#userID").val();
	let	review_content	= $("#review_content").val();
	let	product_type	= $("#product_type").val();
	let	product_name	= $("#product_name").val();
	let	review_star	= $("#review_star").val();
	
	alert(review_bno + ":" + review_subject + ":" + userID + ":" + review_content + ":" + product_type + ":" + product_name + ":" + review_star);
	
		$.ajax({
			type:			"POST",
			url:			"/review/reviewUpdate",
			data:			{review_bno:review_bno, review_subject:review_subject, userID:userID, review_content:review_content, review_star:review_star, product_type:product_type, product_name:product_name},
			success:		function(data) {
				if(data == "Y") {
					alert("리뷰 수정이 완료되었습니다.");
					location.href="/review/reviewList?page=1";
				} else {
					alert("리뷰 수정이 되지 않았습니다.\n\n잠시 후에 다시 해주십시오." + data);
				}
			},
			error:			function(data) {
				alert("실패" + data);
				console.log(data);
			}
		});

} 



// 리뷰 삭제 
 function fn_reviewDelete(review_bno) {
 	
	if(!confirm("\n리뷰을 삭제하시겠습니까?\n\n삭제하려면 [확인]버튼을 누르시고, 아니면 [취소]버튼을 누르십시오.")) {
		alert("리뷰 삭제를 취소하셨습니다.");
	} else {	// [확인]버튼을 눌렀을 경우
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
 }
 
 
 
 

