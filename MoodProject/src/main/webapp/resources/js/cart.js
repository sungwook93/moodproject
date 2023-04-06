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

function totalprice(frm)
{
   let sum = 0;
   let count = frm.chkbox.length;
   for(let i=0; i < count; i++ ){
       if( frm.chkbox[i].checked == true ){
	    sum += parseInt(frm.chkbox[i].value);
       }
   }
   frm.total_sum.value = sum;
}

function itemSum(frm)
{
   var sum = 0;
   var count = frm.chkbox.length;
   for(var i=0; i < count; i++ ){
       if( frm.chkbox[i].checked == true ){
	    sum += parseInt(frm.chkbox[i].value);
	    alert(sum);
       }
   }
   frm.total_sum.value = sum;
}
 

 