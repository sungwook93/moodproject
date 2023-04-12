package com.edu.common.upload;

import java.io.File;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
	
	@RequestMapping(value="/displayImage1", method=RequestMethod.GET)
	public ResponseEntity<byte[]> displayImage(String name) throws Exception{
	
		System.out.println("ImageController의 displayImage 불러오기.... 이름: " + name);
		
		String path = uploadPath + "\\review\\";
		
		System.out.println("파일에 따른 경로: " + path);
		
		//파일 객체에 저장주소를 담는다.
		File file = new File(path + name);
		
		//이미지 결과값을 담을 참조변수 생성
		ResponseEntity<byte[]> result = null;
		
		try {
			HttpHeaders header = new HttpHeaders();
			header.add("Content-type", Files.probeContentType(file.toPath()));
			
			String extentionName = name.substring(name.lastIndexOf(".") + 1); //확장자
			MediaType mType = mediaMap.get(extentionName.toUpperCase()); //확장자로 미디어타입을 가져온다
			header.setContentType(mType); //가져온 미디어 타입으로 세팅
			
			result = new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}// end
	
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
