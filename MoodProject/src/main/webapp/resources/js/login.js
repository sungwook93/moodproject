/**
 *	
 */
 $(document).ready(function() {
 //----------------------------------------------------------------------------------------------------------
 // 로그인 모달창 띄우긴
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



