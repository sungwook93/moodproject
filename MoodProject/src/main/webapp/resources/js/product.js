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


//이미지 속성을 이용한 메인이미지 바꾸기
$(function(){

	//1번째 사진
  $("#img1").hover(function(){
  //서브이미지 사진 속성값을 가져온다.
   let img1 = $("#img1").attr("src");
   //메인이미지에 서브이미지 속성값을 넣는다.
   $("#mainImg").attr("src",img1);
  });
  //2번째 사진
  $("#img2").hover(function(){
   let img2 = $("#img2").attr("src");
   $("#mainImg").attr("src",img2);
  });
  //3번째 사진
  $("#img3").hover(function(){
   let img3 = $("#img3").attr("src");
   $("#mainImg").attr("src",img3);
  });
  //4번째 사진
  $("#img4").hover(function(){
   let img4 = $("#img4").attr("src");
   $("#mainImg").attr("src",img4);
  });
  
});


//상품 등록
function fn_productRegister(){

	// 각 항목의 값을 가져온다.
	let product_name  = document.getElementById("product_name").value;
	let product_size  = document.getElementById("product_size").value;
	let product_price = document.getElementById("product_price").value;
	let product_color = document.querySelector('input[name="color"]:checked').value;
	let product_type = document.getElementById("product_type").value;
	let file = document.getElementById("file");
	
	//값이 들어오는지 확인한다.
	//alert("product_name" + product_name +  "product_size" +  product_size + "product_price"+
	//product_price + "product_color" + product_color +  "product_type" + product_type
	//+ "file" + file);
	
	//상품 이름 입력을 확인한다.
	if(product_name.length == 0) { //이름이 없으면
		alert("상품 이름은 필수 등록 항목입니다. 상품 이름을 입력해주세요.");
		product_name.focus();
		return false;
	}	
	
	//상품 가격 입력을 확인한다.
	if(product_price.length == 0) { //가격이 없으면
		alert("상품 가격은 필수 등록 항목입니다. 상품 가격을 입력해주세요.");
		product_price.focus();
		return false;
	}
	
	//상품 규격 입력을 확인한다.
	if(product_size.length == 0) { //가격이 없으면
		alert("상품 규격은 필수 등록 항목입니다. 상품 규격을 입력해주세요.");
		product_size.focus();
		return false;
	}
	
	//상품 정보를 등록한다 그후 그거에 대한 상품코드를 받아와 그 코드로 이미지를 등록한다.
	if(confirm("상품을 등록하시겠습니까??")){
	
		$.ajax({
			type: "post",
			url: "/product/productRegister",
			data: {product_name:product_name, product_size:product_size, product_price:product_price,
			product_color:product_color, product_type:product_type},
			success: function(data){
			alert(data);
			
			if(data != "0"){
				//이미지 등록을 위해 ajax는 form태그를 안쓰기때문에 formdata()를 준비한다.
				let formData = new FormData();
				
				//업로드 된 이미지만큼 반복문으로 formdata에 넣어준다.
				//set은 리셋되면서 추가하는거지만 append는 리셋하지않고 계속 추가한다.
				alert(file.files.length);
				for(let i = 0; i<file.files.length; i++){
					formData.append("files", file.files[i]);
				}
				
				//alert("formData:" + formData.get("files").name);
				//formData에 상품코드를 넣어준다.
				formData.append("product_code", data);
				
				//데이터가 들어갔는지 확인
				formData.forEach((value, key)=>{
				console.log(`key: ${key}    /    value: ${value}`);
				});
				
				//ajax로 사진업로드
				$.ajax({
					type: "post",
					url: "/image/uploadImage",
					processData: false,
					contentType: false,
					data: formData,
					dataType: "json",
					success: function(data){
						alert("상품이 등록되었습니다. " + data.product_code);
						location.href="/product/productDetail?product_code=" + data.product_code;
					},
					error: function(data){
						alert("상품 이미지 등록에 실패하였습니다.");
					}
				}); //end- 이미지 ajax						
			}//end - if			
			}// end- success
		}); //end - 상품등록
	
	} //end - confirm
	
	
}//end - fn_productRegister


function fn_preview(input) {
  if (input.files && input.files[0]) {
    var reader = new FileReader();
    reader.onload = function(e) {
      document.getElementById('preview').src = e.target.result;
    };
    reader.readAsDataURL(input.files[0]);
  } else {
    document.getElementById('preview').src = "";
  }
}// end -function readURL(input)
