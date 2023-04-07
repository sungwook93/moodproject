<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<%	request.setCharacterEncoding("UTF-8"); %>

<!DOCTYPE html>
	<html>
		<head>
			<meta charset="UTF-8">
			<title>리뷰 작성페이지 </title>
			<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
			<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>	
			<link href="${contextPath}/resources/css/review.css" rel="stylesheet" type="text/css">
			<script src="${contextPath}/resources/js/review.js"></script>
		   
		   <!-- font -->
		   <link rel="preconnect" href="https://fonts.googleapis.com">
		   <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
		   <link href="https://fonts.googleapis.com/css2?family=Sunflower:wght@700&display=swap" rel="stylesheet">
</head>
<body>
			<!-- 상단 메뉴 -->
			<jsp:include page="../common/topMenu.jsp" flush="false"/>
			<hr/>
			<h2 style = "text-align: center; color: white; font-family: Sunflower;">Review RegisterForm</h2>
			<br/>
			<br/>
			
			<div class="container">
				<!-- 게시글 작성 부분 -->
				<form class="form-horizontal">
					<div class="form-group">
						<label class="col-sm-1 control-label">제  목</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" id="review_subject" name="review_subject" maxlength="200" placeholder="제목 입력"/>
						</div>
						<label class="col-sm-1 control-label">리뷰 별점</label>
						<div class="col-sm-3">
							<input type="number" class="form-control" id="review_star" name="review_star" min = "1" max="5" placeholder="리뷰 별점 입력" style = "width:160px;"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-1 control-label">아이디</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" id="userID" name="userID" maxlength="20" value = "${member1.userID }" readonly/>
						</div>
						<!-- 상품 관련 -->
						<label class="col-sm-1 control-label" for="productKindU">상품 목록</label>
						<div class="col-sm-3">	
							<select class="form-control" id="productKindU" name="productKindU" onchange="productKindChange(this)">
								<c:forEach var = "product" items ="${productList}">
								<option disabled selected>상품 Type</option>
								<option value="l" <c:if test="${searchType == 'l'}">selected</c:if>>${product.product_type == living}</option>
								<option value="b" <c:if test="${searchType == 'b'}">selected</c:if>>${product.product_type == bed}</option>
								<option value="c1" <c:if test="${searchType == 'c1'}">selected</c:if>>${product.product_type == bath}</option>
								</c:forEach>
							</select>
						</div>
						<label class="col-sm-1 control-label"  for="productKindD">상품 이름</label>
						<div class="col-sm-3">	
							<select class="form-control" id="productKindD" name="productKindD">
								<option disabled selected>선택해주세요.</option>
								<option value="l" <c:if test="${searchType == 'l'}">selected</c:if>>${product.product_name == living}</option>
								<option value="b" <c:if test="${searchType == 'b'}">selected</c:if>>${product.product_name == bed}</option>
								<option value="c1" <c:if test="${searchType == 'c1'}">selected</c:if>>${product.product_name == bath}</option>
							</select>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-1 control-label">내  용</label>
						<div class="col-sm-10">
							<textarea class="form-control" id="review_content" name="review_content" rows="10" cols="160"  placeholder="내용 입력"></textarea>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-1 control-label">리뷰 사진</label>
						
						<form class = "form-horizontal" action = "${contextPath }/util/upload/uploadForm" method = "post" enctype = "multipart/form-data" target = "iframe1">
							<!-- name = "file"의 변수명과 컨트롤러의 MultipartFile file과 일치해야 한다. -->
							<div class = "form-group">
								<div class = "col-sm-6">
									<input type = "file" class = "btn btn-default" name = "file" style = "width: 600px;"/>
								</div>
								<div class = "col-sm-2">
									<input type = "submit" class = "btn btn-default" value = "파일 올리기"/>
								</div>
							</div>
							
							<!-- iframe에 업로드한 결과를 출력한다. 
							<div class = "form-group">
								<div class = "col-sm-10">
									<iframe name = "iframe1"></iframe>
								</div>
							</div>
							-->
						</form>
						
					</div>
					<br/>
					<div class="form-group">
						<div id="registerFormBtnDiv">
							<button type="button" class="btn btn-light" onclick="location.href='/review/reviewList'" style = "width: 80px;"><strong>Back</strong></button>&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="reset"  class="btn btn-light"><strong>다시 입력</strong></button>&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="btn btn-light" onclick="fn_reviewRegister();"><strong>리뷰 등록</strong></button>
						</div>
					</div>
				</form>
			</div>
			
			 <!-- 하단 메뉴바 -->
			<jsp:include page = "../common/footer.jsp" flush = "false"/>
			
</body>
		<style>
		   * {
		      font-family: 'Arial','Sunflower', 'Arial Black', 'Comic Sans MS', 'Courier New','맑은 고딕','궁서','굴림체','굴림','돋움체','바탕체';
		   }   
		</style>
		<script>
			function productKindChange(e) {
			    var living = ["BAGGEBO 바게보 도어수납장", "VIHALS 비할스 다용도수납장", "STOENSE 스토엔세 단모러그", "BESTA 베스토 TV장식장", "IKEA PS 이케아 피에스 수납장", "DEJSA 데이사 탁상스탠드", "MALM 말름 6칸서랍장", "LIXHULT 릭스훌트 수납장", "TIPHEDE 팁헤데 평직러그", "RANNAS 란네스 TV장식장", "BILLY 빌리 책장", "PILSKOTT 필스코트 LED플로어스탠드", "EKET 에케트 수납장", "KALLAX 칼락스 선반유닛", "LANGSTED 랑스테드 단모러그", "BESTA 베스토 TV수납콤비네이션", "EKET 에케트 수납콤비네이션", "GLADOM 글라돔 트레이테이블"];
			    var bed = ["FREDVANG 프레드방 수납장", "SKUBB 스쿠브 수납장", "KULLEN 쿨렌 서랍장", "HEMNES 헴네스 서랍장", "ÅBYGDA 오뷔그다 매트리스", "BRIMNES 브림네스 침대해드", "KNARREVIK 크나레비크 탁상", "IDANÄS 이다네스 탁상", "HEMNES 헴네스 수납장", "VARDÖ 바르되 수납장", "IDANÄS 이다네스 침대", "IDANÄS 이다네스 6칸서랍장", "STOCKHOLM 2017 스톡홀름 2017 탁상스탠드", "SLATTUM 슬라툼 쿠션형 침대프레임", "OLDERDALEN 올데르달렌 침대협탁", "STUK 스투크 수납박스", "KOPPARFALL 코파르팔 그림", "TÄLLBYN 텔뷘 플로어스탠드"];
			    var bath = ["ENHET 엔헤트 수납콤비네이션1", "ENHET 엔헤트 수납콤비네이션2", "ENHET 엔헤트 수납콤비네이션3","HAVSDUN 하브스둔 LED벽부착등", "VILTO 빌토 수건스탠드", "BASTSJÖN 바스트셴 샤워커튼","GANSJÖN 간셴 욕실용품3종", "VÅGSJÖN 복셴 세면타올 A세트", "EKOLN 에콜른 휴지통","ALMTJÄRN 알름셰른 욕실매트", "LINDBYN 린드뷘 거울 블랙", "VESKEN 베스켄 카트 블랙","BROGRUND 브로그룬드", "ENHET 엔헤트 / TVÄLLEN 트벨렌", "ENHET 엔헤트 양문형 거울수납장 그레이 프레임","TOLFSEN 톨프센 샤워커튼", "4+1 마비스 칫솔 블랙 하드 타입 이탈리아 명품칫솔", "DIMPA 딤파 분리수거가방"];
			    var target = document.getElementById("productKindD");
			
			    if(e.value == "l") var d = living;
			    else if(e.value == "b") var d = bed;
			    else if(e.value == "c1") var d = bath;
			
			    target.options.length = 0;
			
			    for (x in d) {
			        var opt = document.createElement("option");
			        opt.value = d[x];
			        opt.innerHTML = d[x];
			        target.appendChild(opt);
			    }   
			}
			</script>
</html>