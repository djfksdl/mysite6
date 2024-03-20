package com.javaex.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;

@Controller
public class ApiGalleryController {
	//필드
	@Autowired
	private GalleryService galleryService;
	
	//이미지,내용 가져오기
	@ResponseBody
	@RequestMapping(value="/api/gallerys", method=RequestMethod.GET)
	public GalleryVo modalContents(@RequestParam(value="no")int no) {
		System.out.println("ApiGalleryController.modalContents");
		
		System.out.println(no);
		
		//서비스로 보내기
		GalleryVo galleryVo= galleryService.exeSelectOne(no);
		
		System.out.println(galleryVo);
		
		return galleryVo;
	}
	
	//삭제
	@ResponseBody
	@RequestMapping(value="/api/gallerys/{no}", method=RequestMethod.DELETE)
	public int remove(@PathVariable(value="no")int no) {//주소에 붙여서 온거니까 주소에서 받아오기
		System.out.println("ApiGalleryController.remove");
		
		//서비스로 보내기 
		int count =galleryService.exeDelete(no);
		return count;
	}
	
}
