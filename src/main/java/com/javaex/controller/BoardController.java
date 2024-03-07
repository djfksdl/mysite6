package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

import jakarta.servlet.http.HttpSession;

@Controller
public class BoardController {
	//필드
	@Autowired
	private BoardService boardService;
	
	//게시판 메인화면-전체 리스트
	@RequestMapping(value="/board/list" , method= {RequestMethod.GET, RequestMethod.POST})
	public String board(Model model) {
		System.out.println("BoardController.board");
		
		List<BoardVo> bList =boardService.exeList();
		
		model.addAttribute("bList" ,bList);
		
		return "board/list";
	}
	
	//삭제
	@RequestMapping(value="/board/delete" , method= {RequestMethod.GET, RequestMethod.POST})
	public String delete(@RequestParam(value="no")int no) {//게시글 no가져오기
		System.out.println("BoardController.delete");
		
		boardService.exeDelete(no);
		return "redirect:/board/list";
	}
	//글쓰기폼
	@RequestMapping(value="/board/wform" , method= {RequestMethod.GET, RequestMethod.POST})
	public String wform() {
		System.out.println("BoardController.wform");
		
		
		return"board/writeForm";
	}
	//글쓰기
	@RequestMapping(value="/board/write" , method= {RequestMethod.GET, RequestMethod.POST})
	public String write(HttpSession session , @ModelAttribute BoardVo boardVo) {//제목,내용,세션에서 no
		System.out.println("BoardController.write");
		
		UserVo uVo= (UserVo)session.getAttribute("authUser");
		int no = uVo.getNo();
		
		boardVo.setUser_no(no) ;
		
		boardService.exeWrite(boardVo);
		
		return"redirect:/board/list";
	}
	//게시글 클릭
	@RequestMapping(value="/board/read" , method= {RequestMethod.GET, RequestMethod.POST})
	public String read(@RequestParam(value="no")int no, Model model) {//글no받기
		System.out.println("BoardController.read");
		
		BoardVo boardVo =boardService.exeRead(no);
		
		model.addAttribute("boardVo" ,boardVo);
		
		return"board/read";
	}
	//수정폼
	@RequestMapping(value="/board/mform" , method= {RequestMethod.GET, RequestMethod.POST})
	public String mform(@RequestParam(value="no")int no, Model model) {//글no받기
		System.out.println("BoardController.mform");
		
		BoardVo boardVo = boardService.exeRead(no);
		model.addAttribute("boardVo" ,boardVo);
		
		return"board/modifyForm";
	}
	//수정
	@RequestMapping(value="/board/modify" , method= {RequestMethod.GET, RequestMethod.POST})
	public String modify(@ModelAttribute BoardVo boardVo) {//제목, 내용, 글no
		System.out.println("BoardController.modify");
		
		boardService.exeUpdate(boardVo);
		
		return"redirect:/board/list";
		
	}
	
}
