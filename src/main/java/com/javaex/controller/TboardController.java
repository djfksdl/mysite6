package com.javaex.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.TboardService;
import com.javaex.vo.TboardVo;

@Controller
public class TboardController {

	@Autowired
	private TboardService tboardService;
	
	//리스트(검색O,페이징 O)
	@RequestMapping(value="/tboard/list3", method= {RequestMethod.GET, RequestMethod.POST})
	public String list3(@RequestParam(value="crtPage", required=false, defaultValue="1") int crtPage,
						@RequestParam(value="keyword", required=false, defaultValue="") String keyword, //검색을 할 수도 있고 안할 수도 있기때문에 기본값일때를 추가!-> 사실을 항상 검색이 되는거다.
						Model model) {// 파라미터 crtPage가 없으면 1로 처리
		System.out.println("TboardController.list3()");
		System.out.println(keyword);
		
		//boardService를 통해서 리스트를 가져온다
		Map<String , Object> pMap = tboardService.exeList3(crtPage, keyword);
		
		//모델에 리스트를 담는다(포워드)
		model.addAttribute("pMap", pMap); //화면에 포워드하라고 시키는것.
		
		return "tboard/list3";
	}
	
	//리스트(검색X,페이징 O)
	@RequestMapping(value="/tboard/list2", method= {RequestMethod.GET, RequestMethod.POST})
	public String list2(@RequestParam(value="crtPage", required=false, defaultValue="1") int crtPage,Model model) {// 파라미터 crtPage가 없으면 1로 처리
		System.out.println("TboardController.list2()");
		//System.out.println(crtPage);
		
		//boardService를 통해서 리스트를 가져온다
		Map<String , Object> pMap = tboardService.exeList2(crtPage);
		//System.out.println(boardList);
		//boardList를 가져오다가 페이징으으로 인해 맵에 넣어주고 맵 가져온걸 넣는다.
		
		//모델에 리스트를 담는다(포워드)
		//model.addAttribute("boardList", boardList);
		model.addAttribute("pMap", pMap); //화면에 포워드하라고 시키는것.
		
		return "tboard/list2";
	}
	
	//리스트(검색X,페이징 X)
	@RequestMapping(value="/tboard/list", method= {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		System.out.println("TboardController.list()");
		
		//boardService를 통해서 리스트를 가져온다
		List<TboardVo> boardList = tboardService.exeList();
		//모델에 리스트를 담는다(포워드)
		model.addAttribute("boardList", boardList);
		
		return "tboard/list";
	}
	
	
	

	
}
