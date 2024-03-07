package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.CommentService;
import com.javaex.vo.CommentVo;
import com.javaex.vo.UserVo;

import jakarta.servlet.http.HttpSession;

@Controller
public class CommentController {
	//필드
	@Autowired
	private CommentService commentService; 
	//메소드
	
	//전체 리스트
	@RequestMapping(value="/comment", method= {RequestMethod.POST, RequestMethod.GET})
	public String comment(Model model) {
		System.out.println("CommentController.comment");
		
		List<CommentVo> cList = commentService.exeList();
		
		model.addAttribute("cList", cList);
		
		return "comment/list";
	}
	
	//댓글쓰기폼
	@RequestMapping(value="/comment/wform", method= {RequestMethod.POST, RequestMethod.GET})
	public String wform() {
		System.out.println("CommentController.wform");
		
		//jsp에 group_no, order_no, depth숨겨놓기,세션의 no는 언제든 가져올 수 있으니 여기선 안빼도됨
		
		return"comment/writeForm";
	}
	
	//댓글등록
	@RequestMapping(value="/comment/write", method= {RequestMethod.POST, RequestMethod.GET})
	public String write(@ModelAttribute CommentVo commentVo, HttpSession session) {//group_no,내용,순서,깊이,제목, + 세션에서 no
		System.out.println("CommentController.write");
		
		UserVo uVo =(UserVo)session.getAttribute("authUser");
		int num = uVo.getNo();
		
		commentVo.setUser_no(num) ;
		
		commentService.exeInsert(commentVo);
		
		return"redirect:/comment";
	}
}