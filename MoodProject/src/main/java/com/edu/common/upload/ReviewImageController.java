package com.edu.common.upload;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edu.review.dao.ReviewDAO;

@Controller
@RequestMapping(value ="/image")
public class ReviewImageController {
	
	@Resource(name="uploadPath")
	String uploadPath;
	
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
	
	
	
	
	
	
	
	
	
	
	
	
	
}
