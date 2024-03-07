package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
public class GuestbookController {
	//필드
	@Autowired
	private GuestbookService guestbookService;
	//메소드
	//전체리스트-메인
	@RequestMapping(value="/guest" , method= {RequestMethod.GET, RequestMethod.POST})
	public String guest(Model model) {
		System.out.println("GuestbookController.guest");
		
		List<GuestbookVo> guestList = guestbookService.exeList();
		
		model.addAttribute("guestList" ,guestList);
		
		return "guestbook/addList";
	}
	
	//글 등록
	@RequestMapping(value="/guest/write" , method= {RequestMethod.GET, RequestMethod.POST})
	public String write(@ModelAttribute GuestbookVo guestbookVo) {//이름,pw,내용
		System.out.println("GuestbookController.write");
		
		guestbookService.exeWrite(guestbookVo);
		
		return"redirect:/guest";
	}
	//삭제폼
	@RequestMapping(value="/guest/dform" , method= {RequestMethod.GET, RequestMethod.POST})
	public String dform() {
		System.out.println("GuestbookController.dform");
		
		return"guestbook/deleteForm";
	}
	//삭제
	@RequestMapping(value="/guest/delete" , method= {RequestMethod.GET, RequestMethod.POST})
	public String delete(@ModelAttribute GuestbookVo guestbookVo) {//no,pw
		System.out.println("GuestbookController.delete");
		
		guestbookService.exeDelete(guestbookVo);
		
		return"redirect:/guest";
	}
	
}
