/**
 *	
 */
 $(document).ready(function() {
 //----------------------------------------------------------------------------------------------------------
 // 로그인 모달창 띄우기
 //----------------------------------------------------------------------------------------------------------
  	const modal = document.getElementById("modal")
        function modalOn() {
            modal.style.display = "flex"
        }
        function isModalOn() {
            return modal.style.display === "flex"
        }
        function modalOff() {
            modal.style.display = "none"
        }
        
        const closeBtn = modal.querySelector(".close-area")
        closeBtn.addEventListener("click", e => {
            modalOff();
    })
	
	$("#loginbtn").click(function() {
	
		$("#modal.modal-overlay").css ({
			"display" : "flex"
		});
	});

	});

 // 검색 관련 자바 스크립트
 function getCheckboxValue2(){
	// alert(1);
	// 입력된 keyword 가져오기
	 let keyword2 = document.getElementById('keyword2').value;
 	
 	location.href="/product/productList?product_color=black,white,gray&product_type=bed,living,bath&page=1&array_type=r&keyword=" + keyword2;
 
}

