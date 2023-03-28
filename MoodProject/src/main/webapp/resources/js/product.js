/**
 * 상품 관련 자바스크립트
 */
 
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