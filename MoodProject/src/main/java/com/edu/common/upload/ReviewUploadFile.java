package com.edu.common.upload;

import java.io.File;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.edu.review.dto.ReviewImagesDTO;

public class ReviewUploadFile {

	public static ReviewImagesDTO uploadImage(String uploadPath, MultipartFile[] files, int review_bno) throws Exception {
		
		ReviewImagesDTO reviewImagesDTO = new ReviewImagesDTO();
		
		String review_bno1 = review_bno + "";
		
		//파일 저장경로를 설정한다.
		String savedPath = calculatePath(uploadPath, review_bno1);
		
		//배열로 들어온 파일을 반복문으로 업로드 한다.
		for(int i =0; i < files.length; i++) {
			
			//파일의 확장자를 구한다.
			String originalName = files[i].getOriginalFilename();
			String extentionName = originalName.substring(originalName.lastIndexOf(".") + 1);// "."다음부터 끝까지 잘라낸다
			
			//파일 변수에 필요한 파일 이름
			String saveName = review_bno + "-" + (i+1) + "." + extentionName;
			
			//경로와 이름으로 변수를 만든다.
			File file = new File(savedPath, saveName);
			
			//넘겨받은 이미지 데이터를 바이트 형식으로 준비한다.
			byte[] bytes = files[i].getBytes();
			
			//byte[]를 지정한 File에 복사
			FileCopyUtils.copy(bytes, file);
			
			//저장한 파일 이름을 imagesDTO에 순서대로 넣는다.
			if(i == 0) {
				reviewImagesDTO.setImages01(saveName);				
			}  else {
				reviewImagesDTO.setImages02(saveName);
			}
				
		}
		return reviewImagesDTO;			
	}
	
	//상품타입에 맞는 경로 설정
	public static String calculatePath(String uploadPath, String product_code) {
		System.out.println("UploadFileUtils의 calculatePath() 시작....");
		
		// 경로
		String savedPath = "";
		
		//상품 코드에 따른 경로 분류
		if(product_code.substring(0, 1).equals("c")) {
			savedPath = uploadPath + "\\gift\\bath\\";
		}else if(product_code.substring(0, 1).equals("b")) {
			savedPath = uploadPath+ "\\gift\\bed\\";
		}else if(product_code.substring(0, 1).equals("l")){
			savedPath = uploadPath+ "\\gift\\living\\";
		}
		
		return savedPath;
	}
	
	
}
