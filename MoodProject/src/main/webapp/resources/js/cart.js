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
	
	let price = $("#price" + id).val();
	//alert(number);
	//alert(price);
	price = parseInt(price);
	
	// 더하기/빼기
	if(type == 'plus'){
		number = parseInt(number) + 1;
	}else if(type == 'minus' && number != '1'){
		number = parseInt(number) - 1;
	}
	
	// 결과값 세팅
	document.getElementById('product_amount'+ id).innerText = number;
	totalprice = number*price;
	
	
	//정규식을 이용해서 금액을 돌려준다.
	document.getElementById('check' + id).value = totalprice;
	document.getElementById('product_price' + id).innerText = (totalprice.toString().replace(/\B(?=(\d{3})+(?!\d))/g,",") + "원");
}

function totalprice(){
	
	let checked = 'input[name="check"]:checked'
	let selectedEls = document.querySelectorAll(checked);
	
	let result = 0;
	parseInt(result);
	selectedEls.forEach((el) => {
    result += parseInt(el.value);
  	});
  	
  	//alert(result);
  	document.getElementById('totalprice1').innerText = (result.toString().replace(/\B(?=(\d{3})+(?!\d))/g,",") + "원");
	
}




 

 