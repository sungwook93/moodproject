/**
 * 
 */
 
 
//리뷰 작성하기
function fn_reviewRegisterForm(order_num, product_code){
 
alert(order_num);
alert(product_code);




 
}
//나의구매현황
function fn_myorderpage() {
	
	let userID = document.getElementById('userID').value;
	let ordernameid= document.getElementById('ordernameid').value;
	alert(userID);
	location.href="/member/myPageForm.do?userID="+ordernameid;
	
	//처음 페이지들어갈시 상품관리메뉴만보이게
	$("#membertitle").css("display", "none");
	$("#membertitle3").css("display", "none");
	
	//처음 페이지들어갈시 상품관리테이블만 보이게
	$("#membertable").css("display", "none");
	$("#membertable3").css("display", "none");
}