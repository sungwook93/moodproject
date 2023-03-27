/**
 * 상품 관련 자바스크립트
 */
 
 function arrayOption(obj){
 	
 	//alert("확용");
 	
 	let type = $(obj).val();
 	let color = $("#color").val();
 	
 	//alert(type);
 	//alert(color);
 	
 	location.href="/product/productList?product_color="+color+"&product_type="+type+"&page=1";
 
 
}