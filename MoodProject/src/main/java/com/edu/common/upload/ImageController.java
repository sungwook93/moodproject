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

import com.edu.product.dao.ProductDAO;


@Controller
@RequestMapping(value="/image")
public class ImageController {

	//serlet-context에 uploadPath관련 bean추가 매번 주소를 쳐야하는것을 간소화
	@Resource(name="uploadPath")
	String uploadPath; //c:/data/upload
	
	@Autowired
	ProductDAO productDAO;

	//파일의 확장자를 이용해 mediaType을 가져오기 위한 변수
	private static Map<String, MediaType> mediaMap; //제공되는 미디어 타입
	static { //초기화 블록
		mediaMap = new HashMap<String, MediaType>();
		
		mediaMap.put("JPG", MediaType.IMAGE_JPEG);
		mediaMap.put("GIF", MediaType.IMAGE_GIF);
		mediaMap.put("PNG", MediaType.IMAGE_PNG);
	}
	
	//상품코드 혹은 이미지 이름에 해당하는 파일 뿌려주기
	//이미지는 바이너리 파일이므로 byte타입을 사용해야한다, 따라서 해당데이터 타입이 이미지임을 알려주기위해 ReqonseEntity를 이용
	@RequestMapping(value="/displayImage", method=RequestMethod.GET)
	public ResponseEntity<byte[]> displayImage(String name) throws Exception{
		
		System.out.println("ImageController의 displayImage 불러오기.... 이름: " + name);
		
		
		//받아온 값을 이미지테이블에서 메인 이미지이름을 받아온다.
		if(name.length() == 4) { //상품코드가 넘어오면 이미지 파일명을 찾으러 간다.
		name = productDAO.ImageName(name);
		}
		
		System.out.println("파일 이름: " + name);
		
		//파일의 저장경로를 설정하기위해 지정해둔다.
		String path = null;
		//앞글자에 따라 파일을 분류해뒀기때문에 그거에따라 경로를 지정한다.
		if(name.substring(0,1).equals("b")) {
			path = uploadPath + "\\gift\\bed\\";
		} else if(name.substring(0,1).equals("c")) {
			path = uploadPath + "\\gift\\bath\\";
		}else if(name.substring(0,1).equals("l")) {
			path = uploadPath + "\\gift\\living\\";
		}
		
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
	}//end - public ResponseEntity<byte[]> displayImage(String name) throws Exception
	
	
	
	
}
