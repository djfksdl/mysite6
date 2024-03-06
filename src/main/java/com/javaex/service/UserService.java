package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	//필드
	@Autowired
	private UserDao userDao;
	
	//로그인
	public UserVo exeLogin(UserVo userVo) {
		System.out.println("UserService.exeLogin");
		
		UserVo authUser = userDao.userSelectByIdPw(userVo);
		
		return authUser;
	}
	
	//회원가입
	public void exeJoin(UserVo userVo) {
		System.out.println("UserService.exeJoin");
		
		userDao.userJoin(userVo);
		
		
	}
}
