package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	
	//필드
	@Autowired
	private UserService userService;
	//메소드
	
	//로그인폼
	@RequestMapping(value="/user/loginform" , method= {RequestMethod.GET, RequestMethod.POST })
	public String loginform() {
		System.out.println("UserController.loginform");
		
		return "user/loginForm";
	}
	
	//로그인
	@RequestMapping(value="/user/login" , method= {RequestMethod.GET, RequestMethod.POST })
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {//no,name받고 세션에 넣어주기
		System.out.println("UserController.login");
		//System.out.println(userVo);
		
		UserVo authUser = userService.exeLogin(userVo);
		session.setAttribute("authUser", authUser);

		return"redirect:/main";
	}
	//로그아웃
	@RequestMapping(value="/user/logout", method= {RequestMethod.GET, RequestMethod.POST})
	public String logout(HttpSession session) {//세션지우기
		System.out.println("UserController.logout");
		
		session.invalidate();
		
		return "redirect:/main";
	}
	//회원가입 폼
	@RequestMapping(value="/user/joinform", method= {RequestMethod.GET, RequestMethod.POST})
	public String joinform() {
		System.out.println("UserController.joinform");
		
		return "user/joinForm";
	}
	//회원가입
	@RequestMapping(value="/user/join", method= {RequestMethod.GET, RequestMethod.POST})
	public String join(@ModelAttribute UserVo userVo) {//id,pw,name,gender Vo로 받기
		System.out.println("UserController.join");
		
		userService.exeJoin(userVo);
		
		return "redirect:/main";
	}
	//회원정보 수정폼
	@RequestMapping(value="/user/mform", method= {RequestMethod.GET, RequestMethod.POST})
	public String mform(HttpSession session , Model model) {//세션에서 no받기
		System.out.println("UserController.mform");
		
		UserVo uVo=(UserVo)session.getAttribute("authUser");
		int no= uVo.getNo();
		
		UserVo userVo= userService.exeMform(no);
		
		model.addAttribute("userVo" ,userVo);
		
		return"user/modifyForm";
	}
	//회원정보 수정
	@RequestMapping(value="/user/modify", method= {RequestMethod.GET, RequestMethod.POST})
	public String modify(@ModelAttribute UserVo userVo, HttpSession session) {//pw,name,gender를 Vo로 묶어서 받기, 세션에서 no받기
		System.out.println("UserController.modify");
		
		UserVo uVo =(UserVo)session.getAttribute("authUser");
		int no = uVo.getNo();
		
		userVo.setNo(no);
		
		userService.exeModify(userVo);
		
		//세션에 name넣어주기
		uVo.setName(userVo.getName());
		
		return "redirect:/main";
	}
	
	
}
