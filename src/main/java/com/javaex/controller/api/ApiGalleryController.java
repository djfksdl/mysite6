package com.javaex.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.GalleryService;

@Controller
public class ApiGalleryController {
	//필드
	@Autowired
	private GalleryService galleryService;
	
	//삭제
	@RequestMapping(value="/api/gallerys/{no}", method=RequestMethod.DELETE)
	public void remove(@PathVariable(value="no")int no) {
		System.out.println("ApiGalleryController.remove");
		
		//서비스로 보내기 
		galleryService.exeDelete(no);
	}
	
}
