package com.javaex.controller.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ApiGalleryController {
	//필드
	
	
	//삭제
	@RequestMapping(value="/api/gallerys", method=RequestMethod.GET)
	public void remove() {
		System.out.println("ApiGalleryController.remove");
	}
	
}
