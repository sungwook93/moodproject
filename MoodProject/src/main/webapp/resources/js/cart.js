/**
 * 
 */
 
// 수량설정
function count(type, count){
	// 결과값 변수 설정
	//alert("type ==" + type + "count == " + count);
	// status를 이용한 아이디값 설정하기
	let id = count;
	//alert(id);
	
	let number = $("#product_amount" + id).text();
	
	
	// 더하기/빼기
	if(type == 'plus'){
		number = parseInt(number) + 1;
	}else if(type == 'minus' && number != '1'){
		number = parseInt(number) - 1;
	}
	// 결과값 세팅
	document.getElementById('product_amount'+ id).innerText = number;
}

//수량 수정하기
function countUpdate(count){
	//alert("확인");
	let id = count;
	//수량
	let product_amount = $("#product_amount" + id).text();
	product_amount = parseInt(product_amount);
	//유저아이디
	let userID = $("#userID").val();
	let product_code = $("#product_code" + id).val();
	//alert(product_code);
	
	//ajax를 이용한 수량조절
	if(confirm("수량을 수정하시겠습니까?")){
		$.ajax({
			type: "post",
			url: "/order/countUpdate",
			data: {product_amount:product_amount, userID:userID, product_code:product_code},
			success: function(data){
				//alert("확인");
				location.reload("/order/cartForm.do?userID=" + data);
				
			}
	
		}); // end - ajax수량조절
	}
}

$(document).ready(function() {
			
			//체크박스 한번에 모든거 체크관리
			$("#cbx_chkAll").click(function() {		
				if($("#cbx_chkAll").is(":checked")) $("input[name=check]").prop("checked", true);
				else $("input[name=check]").prop("checked", false);
				//체크가 끝난뒤에 가격을 표시한다.
				checkbox();		
			});
			
			$("input[name=check]").click(function() {
				var total = $("input[name=check]").length;
				var checked = $("input[name=check]:checked").length;
				
				if(total != checked) $("#cbx_chkAll").prop("checked", false);
				else $("#cbx_chkAll").prop("checked", true); 
			});	
			
});

//가격 합산
function checkbox(){

	//체크한 항목들을 모은다
	let checked = 'input[name="check"]:checked'
	let selectedEls = document.querySelectorAll(checked);
	
	let result = 0;
	parseInt(result);
	// 체크된거를 다더해준다
	selectedEls.forEach((el) => {
    result += parseInt(el.value);
  	});
  	
  	//값을 정규식에 넣어 표시해준다.
  	//alert(result);
  	document.getElementById('totalprice').innerText = (result.toString().replace(/\B(?=(\d{3})+(?!\d))/g,",") + "원");
	
}

function fn_order(count){
	
	let id = count;
	let cart_num = $("#cart_num" + id).val();
	//alert(cart_num);

	location.href="/order/bills.do?cart_num=" + cart_num;

}



 