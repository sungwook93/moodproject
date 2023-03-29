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
 // 게시판 에디터 
 //----------------------------------------------------------------------------------------------------------
    $(document).ready(function() {
    	$('#qna_content').summernote({
            height: 300,                
            minHeight: null,             
            maxHeight: null,             
            focus: true,                  
            lang: "ko-KR",					
            placeholder: '내용을 등록합니다.',	
             toolbar: [
	          ['fontname', ['fontname']],
	          ['fontsize', ['fontsize']],
	          ['font', ['bold', 'underline', 'clear']],
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
