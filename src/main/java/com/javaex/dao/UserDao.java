package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {
	//필드
	@Autowired
	private SqlSession sqlSession;
	
	//로그인
	public UserVo userSelectByIdPw(UserVo userVo) {
		System.out.println("UserDao.userSelectByIdPw");
		//System.out.println(userVo);
		
		UserVo authUser = sqlSession.selectOne("user.selectByIdPw", userVo);
		//System.out.println(authUser);
		
		return authUser;
	}
	//회원가입
	public void userJoin(UserVo userVo) {
		System.out.println("UserDao.userJoin");
		
		sqlSession.insert("user.insert", userVo);
	}
	
	//회원정보 수정폼
	public UserVo userMform(int no) {
		System.out.println("UserDao.userMform");
		
		UserVo userVo = sqlSession.selectOne("user.selectByNo", no);
		
		return userVo;
	}
	
	//회원정보 수정
	public void userModify(UserVo userVo) {
		System.out.println("UserDao.userModify");
		
		sqlSession.update("user.update", userVo);
	}
}
