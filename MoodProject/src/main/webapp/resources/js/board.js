/**
 * 
 */
 //----------------------------------------------------------------------------------------------------------
 // 게시글 등록 하기
 //----------------------------------------------------------------------------------------------------------
 function fn_boardRegister() {
	

	// 게시글 등록 화면에서 입력한 값을 가져온다.
	let	qna_subject	= $("#qna_subject").val();
	let	userID	= $("#userID").val();
	let	qna_content	= $("#qna_content").val();
	
	
	// 제목 항목에 값이 없으면 입력하도록 한다.
	if($("#qna_subject").val() == "") {
		alert("제목은 필수 입력 항목입니다.");
		$("#qna_subject").focus();
		return false;
	}
	
	// 아이디 항목에 값이 없으면 입력하도록 한다.
	if($("#userID").val() == "") {
		alert("아이디는 필수 입력 항목입니다.");
		$("#userID").focus();
		return false;
	}
	
	// 내용 항목에 값이 없으면 입력하도록 한다.
	if($("#qna_content").val() == "") {
		alert("내용은 필수 입력 항목입니다.");
		$("#qna_content").focus();
		return false;
	}
	
	$.ajax({
		type:		"POST",
		url:		"/board/boardRegister",
		data:		{qna_subject:qna_subject, userID:userID, qna_content:qna_content},
		success:	function(data) {
		alert(1);
			if(data == "Y") {
				alert("게시글을 등록하였습니다.");
				// 게시글 등록이 완료되면, 게시글 목록 화면으로 이동한다.
				location.href = "/board/boardList?page=1";
			}
		},
		error:		function(data) {
			alert("게시글을 등록하는데 실패하였습니다!");
		}
	});
	
} // End - function fn_boardRegister()

 //----------------------------------------------------------------------------------------------------------
 // 게시글 수정
 //----------------------------------------------------------------------------------------------------------
function fn_boardUpdate() {
	
	let	qna_bno		= $("#qna_bno").val();
	let	qna_subject	= $("#qna_subject").val();
	let	userID	= $("#userID").val();
	let	qna_content	= $("#qna_content").val();
	
	alert(qna_bno + ":" + qna_subject + ":" + userID + ":" + qna_content);
	
	$.ajax({
		type:			"POST",
		url:			"/board/boardUpdate",
		data:			{qna_bno:qna_bno, qna_subject:qna_subject, userID:userID, qna_content:qna_content},
		success:		function(data) {
			if(data == "Y") {
				alert("게시글 수정이 완료되었습니다.");
				location.href="/board/boardList?page=1";
			} else {
				alert("게시글 수정이 되지 않았습니다.\n\n잠시 후에 다시 해주십시오.");
			}
		},
		error:			function(data) {
			alert("실패");
			console.log(data);
		}
	});
	
} // End - function fn_boardUpdate()
 
 //----------------------------------------------------------------------------------------------------------
 // 게시글 삭제
 //---------------------------------------------------------------------------------------------------------- 
 function fn_boardDelete(qna_bno) {
 	
	if(!confirm("\n게시글을 삭제하시겠습니까?\n\n삭제하려면 [확인]버튼을 누르시고, 아니면 [취소]버튼을 누르십시오.")) {
		alert("게시글 삭제를 취소하셨습니다.");
	} else {	// [확인]버튼을 눌렀을 경우
		$.ajax({
			type:			"POST",
			url:			"/board/boardDelete",
			data:			{qna_bno : qna_bno},
			success:		function(data) {
				if(data == "Y") {
					alert("게시글의 삭제가 완료되었습니다.");
					location.href = "/board/boardList?page=1";
				}
			},
			error:			function(data) {
				alert("게시글을 삭제하는데 문제가 발생하였습니다.");
			},
			done:			function(data) {
				alert("요청 성공");
			},
			fail:			function(data) {
				alert("요청 실패");
			},
			always:			function(data) {
				alert("요청 완료");
			}
		});
	}
 }
 
 //----------------------------------------------------------------------------------------------------------
 // 게시판 에디터 
 //----------------------------------------------------------------------------------------------------------
    $(document).ready(function() {
    	$('#qna_content').summernote({
            disableResizeEditor: true,
            height: 300,                
            minHeight: null,             
            maxHeight: null,             
            focus: true,                  
            lang: "ko-KR",					
            placeholder: '내용을 등록합니다.',	
             toolbar: [
	          ['fontname', ['fontname']],
	          ['fontsize', ['fontsize']],
	          ['font', ['bold', 'underline', 'italic', 'clear']],
	          ['color', ['color']],
	          ['para', ['ul', 'ol']],
	          ['insert', ['link', 'picture']],
	          ['view', ['fullscreen', 'codeview', 'help']]
	        ],
            fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New','맑은 고딕','궁서','굴림체','굴림','돋움체','바탕체'],
            fontSizes: ['8','9','10','11','12','14','16','18','20','22','24','28','30','36','50','72'],
            callbacks: {
                onImageUpload: function (files) { //이미지 업로드 처리
                    RealTimeImageUpdate(files, this);
                },
                onChange:function(contents, $editable){ //텍스트 글자수 및 이미지등록개수
                    setContentsLength(contents, 0);
                }
            }
    	});

        //$('#summernote').summernote('code', "글 수정시 데이터");
        //$(".note-group-image-url").remove(); //이미지 추가할 때 Image URL 등록 input 삭제

    	//글자수 체크
        //태그와 줄바꿈, 공백을 제거하고 텍스트 글자수만 가져옵니다.
    	function setContentsLength(str, index) {
    	    var status = false;
    	    var textCnt = 0; //총 글자수
    	    var maxCnt = 100; //최대 글자수
    	    var editorText = f_SkipTags_html(str); //에디터에서 태그를 삭제하고 내용만 가져오기
    	    editorText = editorText.replace(/\s/gi,""); //줄바꿈 제거
    	    editorText = editorText.replace(/&nbsp;/gi, ""); //공백제거

            textCnt = editorText.length;
    	    if(maxCnt > 0) {
            	if(textCnt > maxCnt) {
                    status = true;
            	}
    	    }

    	    if(status) {
            	var msg = "등록오류 : 글자수는 최대 "+maxCnt+"까지 등록이 가능합니다. / 현재 글자수 : "+textCnt+"자";
            	console.log(msg);
    	    }
    	}

    	//이미지 등록처리
        function RealTimeImageUpdate(files, editor) {
            var status = false;
            var reg = /(.*?)\.(gif|jpg|png|jepg)$/; //허용할 확장자
            
            var formData = new FormData();
            var fileArr = Array.prototype.slice.call(files);
            var filename = "";
            var fileCnt = 0;
            fileArr.forEach(function(f){
                filename = f.name;
                if(filename.match(reg)) {
                    formData.append('file[]', f);
                    fileCnt++;
                }
            });
            formData.append('tempFolder', $('#tempFolder').val());

            if(fileCnt <= 0) {
                alert("파일은 gif, png, jpg 파일만 등록해 주세요.");
                return;
            } else {
                $.ajax({
                    url : "이미지 업로드 처리할 주소",
                    data:formData,
                    cache:false,
                    contentType:false,
                    processData:false,
                    enctype:'multipart/formDataData',
                    type:'POST',
                    success:function(result) {
                        var data = JSON.parse(result);
                        for(x = 0; x < data.length; x++) {
                            var img = $('<img>').attr({src:data[x]});
                            $(editor).summernote('pasteHTML', '<img src="'+data[x]+'" />');
                        }
                    }
                });
            }
        }
    });

    //에디터 내용 텍스트 제거
    function f_SkipTags_html(input, allowed) {
    	// 허용할 태그는 다음과 같이 소문자로 넘겨받습니다. (<a><b><c>)
        allowed = (((allowed || "") + "").toLowerCase().match(/<[a-z][a-z0-9]*>/g) || []).join('');
        var tags = /<\/?([a-z][a-z0-9]*)\b[^>]*>/gi,
        commentsAndPhpTags = /<!--[\s\S]*?-->|<\?(?:php)?[\s\S]*?\?>/gi;
        return input.replace(commentsAndPhpTags, '').replace(tags, function ($0, $1) {
            return allowed.indexOf('<' + $1.toLowerCase() + '>') > -1 ? $0 : '';
        });
    }
 
//게시글 테이블에서 제목을 눌렀을 때 밑에 내용이 나오게 하기    
$(document).ready(function() {
    
	$(".boardSubjcet").click(function() {
		
		var subject = $(this);
		
		//누른 요소의 td위 tr의 바로 다음 tr을 보여준다.
		subject.parent().parent().next().fadeToggle(500); 
		
	});
 });   
 
 // 댓글 등록
 
 function fn_commentRegister(qna_bno) {
 
 	let reply_content = $("#reply_content").val();
 	let userID = $("#userID1").val();
 	
 	alert(reply_content + " : " + userID);
 	
 	if($("#reply_content").val() == "") {
 		alert("댓글 내용을 입력해주세요");
 		$("#reply_content").focus();
 		return false;
 	}
 	
 	$.ajax({
 		type : "POST",
 		url: "/board/commentRegister",
 		data: {qna_bno:qna_bno, reply_content:reply_content, userID:userID},
 		success: function(data) {
 			if(data == "Y") {
 			alert("댓글 등록 완료");
 			location.href = "/board/boardDetail?qna_bno=" + qna_bno + "&flag=1";
 			}
 		},
 		error: function(data) {
 			alert("댓글 등록 실패");
 		}
 	});
 	 	
 }
 
 // 댓글 삭제 
 
 function fn_deleteComment(reply_bno, qna_bno) {
	
	if(!confirm("\댓글을 삭제하시겠습니까?\n\n삭제하려면 [확인]버튼을 누르시고, 아니면 [취소]버튼을 누르십시오.")) {
		alert("댓글 삭제를 취소하셨습니다.");
	} else {	
	
		$.ajax({
			type: "POST",
			url: "/board/replyDelete",
			data: {qna_bno:qna_bno, reply_bno:reply_bno},
			success: function(data) {
				if(data == "Y") {
					alert("댓글의 삭제가 완료되었습니다.");
					location.href="/board/boardDetail?qna_bno=" + qna_bno + "&flag=1";
				}
			},
			error: function(data){
				alert("댓글 삭제에 실패했습니다.");
			}
		});
	
	}
}


// 댓글 수정
function fn_updateComment() {
	
	let	qna_bno		= $("#qna_bno").val();
	let	reply_content = $("#reply_content1").val();
	let	reply_bno = $("#reply_bno").val();
	let	userID = $("#userID1").val();
	
	alert(qna_bno + ":" + reply_content + ":" + reply_bno);
	
	$.ajax({
		type:			"POST",
		url:			"/board/replyUpdate",
		data:			{qna_bno:qna_bno, reply_content:reply_content, reply_bno:reply_bno, userID:userID},
		success:		function(data) {
			if(data == "Y") {
				alert("댓글 수정이 완료되었습니다.");
				location.href="/board/boardDetail?qna_bno=" + qna_bno  + "&flag=1";
			} else {
				alert("댓글 수정이 되지 않았습니다.\n\n잠시 후에 다시 해주십시오.");
			}
		},
		error:	function(data) {
			alert("실패");
			console.log(data);
		}
	});
	
}



// 댓글 수정창 

function fn_updateOpen(reply_bno) {
  	
  	let	qna_bno		= $("#qna_bno").val();
	let	reply_content = $("#reply_content").val();
	let	statuscount = $("#statuscount").val();
	let i =0;
	let	userID = $("#userID1").val();
  		
  	alert("수정 버튼입니다 " + reply_bno + " : " + qna_bno + " : " + reply_content + " : " + userID);	
  	
	 for (i = 0; i < 100 ; i++) {
	
	}
	 	$("#reply_content3").attr("readonly",false);
	 	
	 	document.getElementById("commentUpdateB").style.display = 'block';
		document.getElementById("commentUpdateA").style.display = 'none';
		
		$("#reply_content3").focus();
		
	
 }  

 
