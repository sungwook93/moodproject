/**
 * 상품 관련 자바스크립트
 */
 
 // 검색 관련 자바 스크립트
 function getCheckboxValue(){
 	
 	// 선택된 color목록 가져오기
 	  let query1 = 'input[name="color"]:checked';
 	  let selectedcolor = 
	  document.querySelectorAll(query1);
 	
 	// 선택된 type목록 가져오기
	  let query2 = 'input[name="type"]:checked';
	  let selectedtype = 
	  document.querySelectorAll(query2);
	  
	// 선택된 radio 가져오기
	 let array = document.querySelector('input[name="array"]:checked').value;
	 
	// 입력된 keyword 가져오기
	 let keyword = document.getElementById('keyword').value;
	  
	// 선택된 color목록에서 value 찾기
	  let color = '';
	  selectedcolor.forEach((el1) => {
	    color += el1.value + ',';
	  });
 
	// 선택된 type목록에서 value 찾기
	  let type = '';
	  selectedtype.forEach((el2) => {
	    type += el2.value + ',';
	  });
 	
 	//alert(color);
 	//alert(type);
 	//alert(keyword);
 	
 	location.href="/product/productList?product_color="+color+"&product_type="+type+"&page=1"+"&array_type="+array + "&keyword=" + keyword;
 
}

// 검색창 관련 스크립트
function fn_checkbox(){
	//검색창 보이게하고 안보이게 하기
 	if($('#typebox').css('display') == 'none'){
      $('#typebox').show();
    }else{
      $('#typebox').hide();
    }
}

// 수량설정
function count(type){
	// 결과값 변수 설정
	//alert("확인");
	let number = document.getElementById('product_amount').innerText;
	let price = document.getElementById('price').value;
	//alert(price);
	
	price = parseInt(price);
	// 더하기/빼기
	if(type == 'plus'){
		number = parseInt(number) + 1;
	}else if(type == 'minus' && number != '1'){
		number = parseInt(number) - 1;
	}
	
	// 결과값 세팅
	document.getElementById('product_amount').innerText = number;
	totalprice = number*price;
	//alert(totalprice);
	
	//정규식을 이용해서 금액을 돌려준다.
	document.getElementById('totalprice2').innerText = (totalprice.toString().replace(/\B(?=(\d{3})+(?!\d))/g,",") + "원");
}


 $(document).ready(function() {
 	alert("확인");
 	
});