package com.javaex.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
public class ApiGuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;
	
	@ResponseBody //return의 데이터를 json방식으로 변경해서 응답문서(responseBody)에 붙어서 보내짐.-> return의 자료형도 바꿔줘야함!
	@RequestMapping(value="/api/guestbooks", method= RequestMethod.GET)
	public List<GuestbookVo> list() {
		System.out.println("ApiGuestbookController.list()");
		
		List<GuestbookVo> guestbookList = guestbookService.exeList();
		System.out.println(guestbookList);
		return guestbookList; //원래는 .jsp로 인식하는데 위에 어노테이션 붙여주면 더이상 jsp라고 인식하지 않음. 
	}
}
