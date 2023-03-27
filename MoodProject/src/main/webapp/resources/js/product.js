/**
 * 상품 관련 자바스크립트
 */
 
 function getCheckboxValue(){
 	
 	
 	let color = $("#color").val();
 	// 선택된 목록 가져오기
	  const query = 'input[name="type"]:checked';
	  const selectedEls = 
	  document.querySelectorAll(query);
 
	// 선택된 목록에서 value 찾기
	  let type = '';
	  selectedEls.forEach((el) => {
	    type += el.value + ',';
	  });
 	
 	//alert(type);
 	
 	location.href="/product/productList?product_color="+color+"&product_type="+type+"&page=1";
 
 
}