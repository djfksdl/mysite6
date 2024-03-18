package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;
import com.javaex.vo.UserVo;

import jakarta.servlet.http.HttpSession;
@Controller
public class GalleryController {
	
	@Autowired
	private GalleryService galleryService;
	
	//리스트 불러오기
	@RequestMapping(value="/gallery/list", method= {RequestMethod.POST, RequestMethod.GET} )
	public String list(Model model) {
		System.out.println("GalleryController.list()");
		
		//디비에 접근해서 정보 불러오기
		List<GalleryVo> galleryList= galleryService.exeList();
		
		model.addAttribute("galleryList", galleryList);
		
		return "gallery/list";
	}
	
	//이미지 등록하기
	@RequestMapping(value="/gallery/upload", method= RequestMethod.POST)
	public String upload(@ModelAttribute GalleryVo galleryVo, HttpSession session, Model model) { //file도 추가해줘서 불러와보자
		System.out.println("GalleryController.upload");
		
		//session에서 no뽑아내기
		UserVo uVo = (UserVo)session.getAttribute("authUser");
		int no = uVo.getNo();
		
//		//서비스로 넘기기 
		galleryVo.setUser_no(no);
		System.out.println(galleryVo);
		String saveName = galleryService.exeUpload(galleryVo);
		
		//모델에 넣어주기
		model.addAttribute("saveName", saveName);
		System.out.println("모델에 넣기 성공");
		
		return "redirect:/gallery/list";
	}
}
