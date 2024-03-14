package com.javaex.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
public class ApiGuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;
	
	//리스트
	@ResponseBody //return의 데이터를 json방식으로 변경해서 응답문서(responseBody)에 붙어서 보내짐.-> return의 자료형도 바꿔줘야함!
	@RequestMapping(value="/api/guestbooks", method= RequestMethod.GET)
	public List<GuestbookVo> list() {
		System.out.println("ApiGuestbookController.list()");
		
		List<GuestbookVo> guestbookList = guestbookService.exeList();
		System.out.println(guestbookList);
		
		return guestbookList; //원래는 .jsp로 인식하는데 위에 어노테이션 붙여주면 더이상 jsp라고 인식하지 않음. 
	}
	
	//등록
	@ResponseBody
	@RequestMapping(value="/api/guestbooks", method=RequestMethod.POST) //같은 주소인데 method로 구분== 주소창에 안붙고, body에 붙어서 오기때문에 사용자에게 안보임
	public GuestbookVo add(@ModelAttribute GuestbookVo guestbookVo) {//name,pw,content를 받아야함
		System.out.println("ApiGuestbookController.add()");
		
		System.out.println("컨전"+ guestbookVo);
		GuestbookVo gVo= guestbookService.exeAddandGuest(guestbookVo);
		System.out.println("컨후"+gVo);
		
		return gVo;//그냥 "ok"만 쓰면 /WEB-INF/views/ok.jsp로 변경된다! 기본 약속!
		//responeType이 json이니까 json으로 써줘야함-> @ResponseBody붙여주기
		//return자리에 화면 넣지말고 json으로 바꾼걸 body에 넣어라 2:43 바디에 넣으려면 json으로 만들어야한다.
	}
	
	
	//삭제
	@ResponseBody
	@RequestMapping(value="/api/guestbooks/delete", method=RequestMethod.POST)
	public int delete(@ModelAttribute GuestbookVo guestbookVo ) {//no랑 pw받으면 됨
		System.out.println("ApiGuestbookController.delete()");
		
		int no =guestbookService.exeDelete(guestbookVo);
		
		return no;
	}
}
