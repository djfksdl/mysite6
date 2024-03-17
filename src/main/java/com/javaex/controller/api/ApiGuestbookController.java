package com.javaex.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
		
		return guestbookList; //원래는 .jsp로 인식하는데 위에 @ResponseBody 붙여주면 더이상 jsp라고 인식하지 않음. json으로 암호화함
		// 주소를 직접치면 json양식으로 나옴.
	}
	
	//등록
	@ResponseBody//응답임
	@RequestMapping(value="/api/guestbooks", method=RequestMethod.POST) //같은 주소인데 method로 구분함. 주소창에 안붙고, body에 붙어서 오기때문에 사용자에게 안보임
	//public GuestbookVo add(@ModelAttribute GuestbookVo guestbookVo) {//name,pw,content를 받아야함 /파라미터에 있는거 담아달라였는데,json으로 보낼때는 요청바디에 json으로 있다고 알려줘야함.
	public GuestbookVo add(@RequestBody GuestbookVo guestbookVo) {
		System.out.println("ApiGuestbookController.add()");
		
		System.out.println("컨전"+ guestbookVo);
		GuestbookVo gVo= guestbookService.exeAddandGuest(guestbookVo);
		System.out.println("컨후"+gVo);
		
		return gVo;//그냥 "ok"만 쓰면 /WEB-INF/views/ok.jsp로 변경된다! 기본 약속!
		//responeType이 json이니까 json으로 써줘야함-> @ResponseBody붙여주기
		//return자리에 화면 넣지말고 json으로 바꾼걸 body에 넣어라 2:43 바디에 넣으려면 json으로 만들어야한다.
		//ok만 보내면 안됨. 데이터만 감. 화면에 뿌리려면 내가 그려야함. 그러기 위해선 한명 데이터를 보내줘야함. no와 reg_data는 등록시 내가 넣어줄 수 없음. db에 저장해서 방금 저장한 애 풀세트를 키값으로 가져와줘야함  
	}
	
	
	//삭제
	@ResponseBody
	@RequestMapping(value="/api/guestbooks/{no}", method=RequestMethod.DELETE) //변하는 값이면{}안에 변수 써줌. 이름자체는 상관없음 
	public int remove(@PathVariable("no") int no,@ModelAttribute GuestbookVo guestbookVo ) {//no랑 pw받으면 됨./ no는 여기선 pathvariable로 안받아도됨. Vo에 들어가있고, no를 꺼내서 쓸게 아니기 때문에!일단은 냅둠
		System.out.println("ApiGuestbookController.remove()");
		System.out.println(no);
		System.out.println(guestbookVo);//여기엔 password만 있어야함.(js참조) 원래는 따로 no넣어줘야했었는데 따로 넣어주지않아도 들어가있음. no를 꺼내써야하면 위에 pathvalriable넣어줘야하는데 굳이 꺼내서 안쓰면 pathvalriable안써도됨.
		int count =guestbookService.exeDelete(guestbookVo);
		return count;
		//int no =guestbookService.exeDelete(guestbookVo);
		//return no;
	}
}
