package com.edu.common.upload;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.edu.product.dto.ImagesDTO;
import com.edu.review.dao.ReviewDAO;
import com.edu.review.dto.ReviewImagesDTO;

@Controller
@RequestMapping(value ="/image")
public class ReviewImageController {
	
	@Resource(name="uploadPath")
	String uploadPath;  //c:/data/upload
	
	@Autowired
	ReviewDAO reviewDAO;
	
	//파일의 확장자를 이용해 mediaType을 가져오기 위한 변수
	private static Map<String, MediaType> mediaMap; //제공되는 미디어 타입
	static { //초기화 블록
		mediaMap = new HashMap<String, MediaType>();
		
		mediaMap.put("JPG", MediaType.IMAGE_JPEG);
		mediaMap.put("GIF", MediaType.IMAGE_GIF);
		mediaMap.put("PNG", MediaType.IMAGE_PNG);
	}	
	
	
	//리뷰 이미지 업로드한다.
	@ResponseBody
	@RequestMapping(value="/reviewUpload", method=RequestMethod.POST)
	public ResponseEntity<ReviewImagesDTO> reviewUpload(MultipartFile[] files, int review_bno)throws Exception{
		System.out.println("ImageController의 updateImage 처리하기....리뷰 번호: " + review_bno + ", 파일이름: " + files[0].getOriginalFilename());
		
		//이미지를 업로드후 db에 입력하기위해 imagesDTO 타입으로 받는다.
		ReviewImagesDTO reviewImagesDTO = ReviewUploadFile.uploadImage(uploadPath, files, review_bno);
		
		//reviewImagesDTO에 상품 코드를 세팅한다.
		reviewImagesDTO.setReview_bno(review_bno);
		
		//reviewImagesDTO db등록
		int result = reviewDAO.imagesRegister(reviewImagesDTO);
		
		if(result == 1) {
			System.out.println("db에 이미지 등록성공");
		}
		
		return new ResponseEntity<ReviewImagesDTO>(reviewImagesDTO, HttpStatus.OK);
	}

	
	
	
	
	
	
	
	
	
}
